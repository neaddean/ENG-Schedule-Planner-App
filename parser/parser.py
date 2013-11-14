import urllib.request, re, PyPDF2.pdf

base_url = 'http://www.bu.edu/ece/undergraduate/courses/'

res = urllib.request.urlopen(base_url)
source = res.read().decode('utf-8')

links = re.findall(r'href=[\'"]?([^\'">]+)(?=\">PDF)', source)

print("Found", len(links), ".pdfs. \nStarting download")

#for i in links:
#    print(i)
    
i = 0
for pdf_url in links:
    pdfurl = urllib.request.urlopen(pdf_url)
    f = pdfurl.read()
    writefile = open(str(i)+".pdf", 'wb')
    writefile.write(f)
    writefile.close()
    i += 1

print("Finished")
