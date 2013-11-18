from pdfminer.pdfinterp import PDFResourceManager, PDFPageInterpreter
from pdfminer.converter import TextConverter
from pdfminer.layout import LAParams
from pdfminer.pdfpage import PDFPage
from cStringIO import StringIO
import tempfile
import xml.etree.ElementTree as ET
import urllib2, re

deptList = ["EC", "ME", "BE", "EK", "SE", "MS"]

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

base_url = 'http://www.bu.edu/me/academics/course-syllabi/'
#http://www.bu.edu/ece/undergraduate/courses/

res = urllib2.urlopen(base_url)
source = res.read().decode('utf-8')

links = re.findall(r'href=[\'"]?([^\'">]+)(?=\">pdf)', source)

print "Found", len(links), "pdfs. \nStarting download"
prereqfile = file("prereq.txt", "w")
txtnum = 0
for pdf_url in links:
    course_found = False
    pdfurl = urllib2.urlopen(pdf_url)
    f = pdfurl.read()
    tempwritefile = tempfile.TemporaryFile()
    tempwritefile.write(f)
    f = convert_pdf(tempwritefile).split(" ")
    pdf_url = str(pdf_url).replace("-", "").upper()
    for i in range(len(pdf_url)):
        if pdf_url[i:i+2].upper() in deptList:
            if pdf_url[i+2:i+5].isdigit():
                course = [pdf_url[i:i+2],pdf_url[i+2:i+5]]
                if course[0] == "SE":
                    course[0] = "EC"
                elif course[0] == "MS":
                    course[0] = "ME"
                print course, txtnum
                course_found = True
                break
    else:
        print "ERROR", pdf_url, txtnum
    for string in range(len(f)):
        match = re.match(r"([a-z]+)([0-9]+)", f[string], re.I)
        if match:
            items = match.groups()
            f[string] = items[0]
            f.insert(string + 2, items[1])
            continue
        f[string] = f[string]
    for string in range(len(f)):
        f[string] = f[string].strip()
    prereq_field = False
    for j in range(len(f)):
        if "Prereq" in f[j] and not prereq_field:
            prereq_field = True
            found_field  = False
            for string in range(j,j+50):
                if "Coreq" in f[string] or ":" in f[string]:
                    break
                g = f[:]
                for fix in range(len(g)):
                    g[fix] = g[fix].strip(".")
                    g[fix] = g[fix].strip(",")
                    g[fix] = g[fix].strip("-")
                    g[fix] = g[fix].strip(":")
                if g[string] in deptList:
                    for test in g[string:string+5]:
                        if "/" in test:                                    
                            print "slash on class"
                            found_field = True
                    else:
                        if g[string+1].isdigit():
                            print g[string:string+2]
                            found_field = True
            break
    if prereq_field and not found_field:
        pass
        print "WARNING: did not match prereqs"
    tempwritefile.close()
    txtnum += 1
    if not course_found:
        print "ERROR", pdf_url

prereqfile.close()
print "Finished"
