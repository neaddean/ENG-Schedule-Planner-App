from pdfminer.pdfinterp import PDFResourceManager, PDFPageInterpreter
from pdfminer.converter import TextConverter
from pdfminer.layout import LAParams
from pdfminer.pdfpage import PDFPage
from cStringIO import StringIO
import tempfile
import xml.etree.ElementTree as ET
import urllib2, re

def convert_pdf(path):

    rsrcmgr = PDFResourceManager()
    retstr = StringIO()
    codec = 'utf-8'
    laparams = LAParams()
    device = TextConverter(rsrcmgr, retstr, codec=codec, laparams=laparams)

    #fp = file(str(path), 'rb')
    interpreter = PDFPageInterpreter(rsrcmgr, device)
    for page in PDFPage.get_pages(path, set()):
        interpreter.process_page(page)
    device.close()

    string = retstr.getvalue()
    retstr.close()
    return string

base_url = 'http://www.bu.edu/ece/undergraduate/courses/'

res = urllib2.urlopen(base_url)
source = res.read().decode('utf-8')

links = re.findall(r'href=[\'"]?([^\'">]+)(?=\">PDF)', source)

print "Found", len(links), "pdfs. \nStarting download"
prereqfile = file("prereq.txt", "w")
i = 0
for pdf_url in links:
    pdfurl = urllib2.urlopen(pdf_url)
    f = pdfurl.read()
    tempwritefile = tempfile.TemporaryFile()
    tempwritefile.write(f)
    f = convert_pdf(tempwritefile).split(" ")
    pdf_url = str(pdf_url)
    for i in range(len(pdf_url)):
        if pdf_url[i:i+2] in ["ec", "be", "me", "EC", "ME", "BE", "ek", "EK"]:
            if pdf_url[i+2:i+5].isdigit():
                course = [pdf_url[i:i+2].upper(),pdf_url[i+2:i+5]]
                print course, i
                break
    else:
        print "ERROR", pdf_url
    for string in range(len(f)):
        match = re.match(r"([a-z]+)([0-9]+)", f[string], re.I)
        if match:
            items = match.groups()
            f[string + 1] = items[1]
            f[string    ] = items[0]
            continue
        f[string] = f[string]
    for string in range(len(f)):
        f[string] = f[string].strip()
        f[string] = f[string].strip(".")
        f[string] = f[string].strip(",")
        f[string] = f[string].strip("-")
    for j in range(len(f)):
        if "Prereq" in f[j]:
            for k in range(j+5,len(f)):
                if ":" in f[k]:
                    for string in range(j,k):
                        if f[string] == "CAS" or f[string] == "ENG":
                            for test in f[string:string+5]:
                                if "/" in test:                                    
                                    print "slash on class"
                                    break
                            else:
                                print f[string:string+3]
                    break
    tempwritefile.close()
    i += 1

prereqfile.close()
print "Finished"
