import urllib2, re

links = []
url_list = [#"http://www.bu.edu/academics/eng/courses/electrical-computer-engineering/0/",
            #"http://www.bu.edu/academics/eng/courses/mechanical-engineering/0/",
            #"http://www.bu.edu/academics/eng/courses/biomedical-engineering/0/",
            "http://www.bu.edu/academics/eng/courses/engineering-core/0/"]


for base_url in url_list:
    i = 0
    while 1:
        delta = []
        base_url = base_url.replace(str(i), str(i+1))
        i = i + 1
        print base_url
        try:
            res = urllib2.urlopen(base_url)
        except ValueError:
            print "INVALD URL"
        source = res.read().decode('utf-8').replace("\t","")
        delta += re.findall(r'href=[\'"](.*)[\'"]><strong>(.*) (.*) (.*): (.*)</strong></a><br />\n.*<span>.*:(.*)</span><br />\n(.*) ', source)
        if delta == []:
            break
        links += delta



    # <a href="/academics/eng/courses/eng-ec-410/"><strong>ENG EC 410: Introduction to Electronics</strong></a><br />
    # <span>Undergraduate Prerequisites: ENG EK 307.</span><br />
    # Principles of diode, BJT, and MOSFET circuits. Graphical and analytical means of analysis. Piecewise linear modeling; amplifiers; digital inverters and logic gates. Biasing and small-signal analysis, microelectronic design techniques. Time-domain and frequency domain analysis and design. Includes lab. 4 cr.		</li>
for i in links:
    print i
# if len(links) == 0:
#     print "LINKS EMPTY"
