#!/usr/bin/python2
import urllib2, re
#import xml.etree.ElementTree as ElementTree
from xml.etree import ElementTree
from xml.dom import minidom

def prettify(elem):
    """Return a pretty-printed XML string for the Element.
    """
    rough_string = ElementTree.tostring(elem, 'utf-8')
    reparsed = minidom.parseString(rough_string)
    return reparsed.toprettyxml(indent="  ")

links = []
url_list = ["http://www.bu.edu/academics/eng/courses/electrical-computer-engineering/0/"
            "http://www.bu.edu/academics/eng/courses/mechanical-engineering/0/",
            "http://www.bu.edu/academics/eng/courses/biomedical-engineering/0/",
            "http://www.bu.edu/academics/eng/courses/engineering-core/0/"
            ]
            
for base_url in url_list:
    i = 0
    while 1:
        delta = []
        base_url = base_url.replace(str(i), str(i+1))
        i = i + 1
        #print base_url
        try:
            res = urllib2.urlopen(base_url)
        except ValueError:
            print "INVALD URL"
        source = res.read().decode('utf-8').replace("\t","")
        delta += re.findall(r'href=[\'"](.*)[\'"]><strong>(.*) (.*) (.*): (.*)</strong></a><br />\n.*<span>.*:(.*)</span><br />\n(.*) ', source)
        if delta == []:
            break
        links += delta


root = ElementTree.Element("courses")

for i in links:
    if not ":" in " ".join(i[1:4]):
        course = ElementTree.SubElement(root, "course")
        course.set ("name", i[4])
        school = ElementTree.SubElement(course, "school")
        school.text = i[1]
        dept   = ElementTree.SubElement(course, "dept")
        dept.text   = i[2]
        cid     = ElementTree.SubElement(course, "cid")
        cid.text    = i[3]
        description = ElementTree.SubElement(course, "description")
        description.text = i[6].lstrip()


towrite =  prettify(root)
xmlfile = open("courses.xml", "w")
xmlfile.write(towrite.encode('utf-8'))
xmlfile.close()

print "done"
#print towrite
