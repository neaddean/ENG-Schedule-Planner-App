from pdfminer.pdfinterp import PDFResourceManager, PDFPageInterpreter
from pdfminer.converter import TextConverter
from pdfminer.layout import LAParams
from pdfminer.pdfpage import PDFPage
from cStringIO import StringIO
import tempfile

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

import urllib2, re

base_url = 'http://www.bu.edu/ece/undergraduate/courses/'

res = urllib2.urlopen(base_url)
source = res.read().decode('utf-8')

links = re.findall(r'href=[\'"]?([^\'">]+)(?=\">PDF)', source)

print "Found", len(links), "pdfs. \nStarting download"

i = 0
for pdf_url in links:
    pdfurl = urllib2.urlopen(pdf_url)
    f = pdfurl.read()
    tempwritefile = tempfile.TemporaryFile()
    tempwritefile.write(f)
    writefile = file('txts/' + str(i)+".txt", "wb")
    writefile.write(convert_pdf(tempwritefile))
    tempwritefile.close()
    writefile.close()
    pdfurl.close()
    i += 1

print "Finished"
