package xmlparser;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;

import xmlparser.Course;

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

public class Courses {
	
	ArrayList<Course> courseList;
	
	
	public void printStuff() {
		for (Course c : courseList) {
			 
			System.out.println("name : " + c.name);
			System.out.println("school : " + c.school);
			System.out.println("dept : " + c.dept);
			System.out.println("cid : " + c.cid);
			System.out.println("description : " + c.description);
		}
	}
	
	public Courses(String courseFile) {
		try {
		File fXmlFile = new File(courseFile);
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
	 
		NodeList nList = doc.getElementsByTagName("course");
		
		ArrayList <Course> tempCourseList = new ArrayList<Course> ();
		
		
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
	 
				tempCourseList.add(tempCourse);
	 
			}
	 
		
		}
		
		courseList = tempCourseList;
	} 
	catch (Exception e) {
	    e.printStackTrace();
	    }

	}
}

