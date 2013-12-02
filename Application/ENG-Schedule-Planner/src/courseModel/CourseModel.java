package courseModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;

import android.R;
import android.content.Context;
import courseModel.Course;

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

public class CourseModel {
	
	ArrayList<Course> courseList;
	
	HashMap<String, ArrayList<Course>> semesterLists;
	
	public ArrayList<Course> getCourseList() {
		return courseList;
	}
	
	private static CourseModel instance = null;
	protected CourseModel() {}
	public static CourseModel getInstance() {
		if (instance == null) {
			instance = new CourseModel();
			System.out.println("creating singleton");
		}
		return instance;
	}
		
	public void printStuff() {
		for (Course c : courseList) {
			 
			System.out.println("name : " + c.getFullTitle());
			System.out.println(courseList.indexOf(c));
		}
	}
	
	public void loadCourseFile(Context context) {
		try {
		InputStream inputStream = context.getAssets().open("courses.xml"); 
		//File fXmlFile = new File(courseFile);
		//InputStream inputStream= new FileInputStream(fXmlFile);
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
		
		 HashMap <String, ArrayList<Course>> tempSemesters = new HashMap<String, ArrayList<Course>> ();
		 
		 ArrayList <String> semesterLabels = new ArrayList<String>();
		 semesterLabels.add("1f");
		 semesterLabels.add("1s");
		 semesterLabels.add("1u");
		 semesterLabels.add("2f");
		 semesterLabels.add("2s");
		 semesterLabels.add("2u");
		 semesterLabels.add("3f");
		 semesterLabels.add("3s");
		 semesterLabels.add("3u");
		 semesterLabels.add("4f");
		 semesterLabels.add("4s");
		 semesterLabels.add("4u");
		 
		 int i = 0;
		 
		 ArrayList<Course> tempList = new ArrayList<Course>();

		 for (String mystr : semesterLabels) {
			 tempList.clear();
			 for (int j = 0; j < 5; j++) { 
//				 System.out.println(i);
				 tempList.add(courseList.get(i));
				 i++;
			 }
			 tempSemesters.put(mystr, tempList);
		 }
		 
		 semesterLists = tempSemesters;
	}

	public ArrayList<Course> getClassWithYear(char year, char semester) {
	StringBuilder semesterChoice = new StringBuilder(2).append(year).append(semester);
	return semesterLists.get(semesterChoice.toString());
}

	public void printLists() {
		 for (Map.Entry entry : semesterLists.entrySet()) {
			 ArrayList<Course> iterList = (ArrayList<Course>) entry.getValue();
			 for (Course c : iterList) {
					System.out.println(entry.getKey() + " : " + c.getFullTitle()); 
			 }
		 }
	}

	
}


