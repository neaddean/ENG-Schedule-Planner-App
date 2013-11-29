package xmltest;
 
import javax.xml.parsers.DocumentBuilderFactory;

import xmltest.Course;

import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
 

public class xmlreader {
 
  public static void main(String argv[]) {
 
    try {
 
	File fXmlFile = new File("courses.xml");
	InputStream inputStream= new FileInputStream(fXmlFile);
	Reader reader = new InputStreamReader(inputStream,"UTF-8");
	 
	InputSource is = new InputSource(reader);
	is.setEncoding("UTF-8");
	
	
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(is);
 
	//optional, but recommended
	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	doc.getDocumentElement().normalize();
 
	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
 
	NodeList nList = doc.getElementsByTagName("course");
	
	ArrayList <Course> courseList = new ArrayList<Course>();
	
	for (int temp = 0; temp < nList.getLength(); temp++) {
		 
		Node nNode = nList.item(temp);
		
		Course tempCourse = new Course();
 
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
 
			Element eElement = (Element) nNode;
			
			tempCourse.name = eElement.getAttribute("name");
			tempCourse.school = eElement.getElementsByTagName("school").item(0).getTextContent();
			tempCourse.dept = eElement.getElementsByTagName("dept").item(0).getTextContent();
			tempCourse.cid = eElement.getElementsByTagName("cid").item(0).getTextContent();
			tempCourse.description = eElement.getElementsByTagName("description").item(0).getTextContent();
 
			courseList.add(tempCourse);
 
		}
 
	for (Course c : courseList) {
 
			System.out.println("name : " + c.name);
			System.out.println("school : " + c.school);
			System.out.println("dept : " + c.dept);
			System.out.println("cid : " + c.cid);
			System.out.println("description : " + c.description);
 
	}
	
	}
	
    } catch (Exception e) {
	e.printStackTrace();
    }
  }
 
}