#!/usr/bin/python
import urllib.request, re
#import urllib, re
import xml.etree.ElementTree as ElementTree
from xml.etree import ElementTree as ET
from xml.dom import minidom
import unicodedata

links = []

parser = ET.XMLParser(encoding="utf-8")

def prettify(elem):
    """Return a pretty-printed XML string for the Element.
    """
    rough_string = ElementTree.tostring(elem, 'utf-8')
    reparsed = minidom.parseString(rough_string)
    return reparsed.toprettyxml(indent="  ")

url_list = ["http://www.bu.edu/academics/eng/courses/electrical-computer-engineering/0/",
            "http://www.bu.edu/academics/eng/courses/mechanical-engineering/0/",
            "http://www.bu.edu/academics/eng/courses/biomedical-engineering/0/",
            "http://www.bu.edu/academics/eng/courses/engineering-core/0/",
            "http://www.bu.edu/academics/cas/courses/mathematics-statistics/0",
            "http://www.bu.edu/academics/cas/courses/physics/0/"
            ]

delta = {}
for base_url in url_list:
    i = 0
    while 1:
        string = ""
        delta.clear()
        base_url = base_url.replace(str(i), str(i+1))
        i = i + 1
        try:
            res = urllib.request.urlopen(base_url)
        except ValueError:
            print ("INVALD URL")
        source = res.read().decode("utf-8").replace("\t","").replace("\n","").replace('&', '&amp;')
        string = re.findall('<ul class\=\"course\-feed\">(.*?)</ul>?', source, re.S)
        if string == "":
            break
        string = u"<root>" + string[0] + u"</root>"
        #string = unicodedata.normalize('NFKD', string).encode('ascii','ignore')
        #print(string)
        root = ET.fromstring(string)
        #rootdean = etree.fromstring(string)
        for child in root:
            courseCode = re.findall(r'([A-Z]{3}) ([A-Z]{2}) ([0-9]{3}?):(.*)',child[0][0].text)
            delta["school"] = courseCode[0][0]
            delta["dept"] = courseCode[0][1]
            delta["cid"] = courseCode[0][2]
            delta["course"] = courseCode[0][3]
            if not child.find("span") == None:
                delta["prereqs"] = re.findall(r'([A-Z]{3}) ([A-Z]{2}) ([0-9]{3}?)',
                                          child.find("span").text)
                if len(delta["prereqs"]) > 3:
                    break
            delta["link"] = "http://www.bu.edu" + child[0].attrib['href']
            delta["description"] = child.findall("br")[-1].tail.lstrip()
            if "2cr" in delta["description"].replace(" ",""):
                delta["credits"] = "2"
            else:
                delta["credits"] = "4"
            links.append(delta.copy())
        if not delta:
            break
    
root = ET.Element("courses")

for i in links:
    if int(i["cid"]) < 600:
        course = ET.SubElement(root, "course")
        course.set ("name", i["course"])
        school = ET.SubElement(course, "school")
        school.text = i["school"]
        dept   = ET.SubElement(course, "dept")
        dept.text   = i["dept"]
        cid     = ET.SubElement(course, "cid")
        cid.text    = i["cid"]
        description = ET.SubElement(course, "description")
        description.text = i["description"]
        link = ET.SubElement(course, "link")
        link.text = i["link"]
        credit = ET.SubElement(course, "credits")
        credit.text = i["credits"]
        prereqs = ET.SubElement(course, "prereqs")
        if "prereqs" in i:
            ger = ""
            for req in i["prereqs"]:
                ger += " " + "".join(map(str,req))
            prereqs.text = ger.lstrip()
        else:
            prereqs.text = " "

            
towrite =  prettify(root)
xmlfile = open("courses.xml", "w")
xmlfile.write(towrite)
xmlfile.close()

print("done")
#print towrite
