from pdfminer.pdfinterp import PDFResourceManager, PDFPageInterpreter
from pdfminer.converter import TextConverter
from pdfminer.layout import LAParams
from pdfminer.pdfpage import PDFPage
from cStringIO import StringIO
import tempfile
import xml.etree.ElementTree as ET
import urllib2, re

deptList = ["EC", "ME", "BE", "EK", "SE", "MS", "MA", "PY"]

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

#base_url = 'http://www.bu.edu/me/academics/course-syllabi/'
base_url = 'http://www.bu.edu/ece/undergraduate/courses/'

res = urllib2.urlopen(base_url)
source = res.read().decode('utf-8')

links = re.findall(r'href=[\'"]([^\'">]+)(?=\">PDF)', source)
links = links + re.findall(r'href=[\'"]([^\'">]+)(?=\">pdf)', source)

print "Found", len(links), "pdfs. \nStarting downloads..."
prereqfile = file("prereq.txt", "w")
txtnum = 0
for pdf_url in links:
    course_found = False
    pdfurl = urllib2.urlopen(pdf_url)
    f = pdfurl.read()
    tempwritefile = tempfile.TemporaryFile()
    tempwritefile.write(f)
    f = convert_pdf(tempwritefile).decode("UTF-8").split(" ")
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
        print "ERROR: course name not found", pdf_url, txtnum
    for checkencode in range(len(f)):
        if u"\xa0" in f[checkencode]:
            f = " ".join(f)
            f = f.replace(u"\xa0", " ")
            f = f.split(u" ")
    for string in range(len(f)):
        f[string] = f[string].strip()
    for string in range(len(f)):
        match = re.match(r"([a-z]+)([0-9]+)", f[string], re.I)
        if match:
            items = match.groups()
            f[string] = items[0]
            f.insert(string + 1, items[1])
            continue
        f[string] = f[string]
    prereq_field = False
    for j in range(len(f)):
        if "Prereq" in f[j] and not prereq_field:
            prereq_field = True
            found_field  = False
            if j + 40 > len(f):
                ending = len(f)
            else:
                ending = j + 40
            for string in range(j+1,ending):
                if "Coreq" in f[string]:
                    break
                if ":" in f[string] and string-j>5:
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
                            break
                    else:
                        if g[string+1].isdigit():
                            print "\t", g[string:string+2]
                            found_field = True
            break
    if prereq_field and not found_field:
        print "WARNING: could not match prereqs"
    if course[1] == "413":
        #break
       # print f
        pass
    tempwritefile.close()
    txtnum += 1
prereqfile.close()
print "Finished"
