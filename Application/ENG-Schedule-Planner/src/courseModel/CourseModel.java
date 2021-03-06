package courseModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import android.content.Context;
import courseModel.Course;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.example.eng_schedule_planner.Global.Global;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader; 
import java.io.StringReader;
import java.io.StringWriter;

public class CourseModel implements ModelAccessor{
	
	ArrayList<Course> courseList;
	
	HashMap<String, ArrayList<Course>> semesterLists;

	ArrayList<String> courseTitleList;
	
	public final static char FALL = 'f';
	public final static char SPRING = 's';
	public final static char SUMMER = 'u';
	
	public final static int EXTERNAL_CREDITS = 0;
	public final static int FRESHMAN_YEAR = 1;
	public final static int SOPHOMORE_YEAR = 2;
	public final static int JUNIOR_YEAR = 3;
	public final static int SENIOR_YEAR = 4;
	
//	public static HashMap <String, ArrayList<Course>> PLANNER_CE = CEDefault();
//	public static HashMap <String, ArrayList<Course>> PLANNER_EE = EEDefault();
//	public static HashMap <String, ArrayList<Course>> PLANNER_ME = MEDefault();
//	public static HashMap <String, ArrayList<Course>> PLANNER_BME = BMEDefault();
//	public static HashMap <String, ArrayList<Course>> PLANNER_DEFAULT = BlankDefault();
	
	
	public ArrayList<Course> getCourseList() {
		return courseList;
	}
	
	private static CourseModel instance = null;
	protected CourseModel() {}
	public static CourseModel getInstance() {
		if (instance == null) {
			instance = new CourseModel();
		}
		return instance;
	}
	
	public Course getCourseByTitle(String Title) {
		for (Course c: courseList) {
			if (c.getTitle().equals(Title)) {
				Course retCourse  = new Course(c);
				return retCourse;
			}
		}
		return null;
	}
	
	
	public ArrayList<String> getCourseTitleList()
	{
		return courseTitleList;
	}
	//Blank Default Planning Sheet

		HashMap <String, ArrayList<Course>> BlankDefault() {
			HashMap <String, ArrayList<Course>> tempSemesters = new HashMap<String, ArrayList<Course>> ();
			
			//Freshman Semester 1
			ArrayList<Course> f1List = new ArrayList<Course>();
			tempSemesters.put("1f", f1List);
			
			//Freshman Semester 2
			ArrayList<Course> s1List = new ArrayList<Course>();
			tempSemesters.put("1s", s1List);
		
			//Sophomore Semester 1
			ArrayList<Course> f2List = new ArrayList<Course>();
			tempSemesters.put("2f", f2List);

			
			//Sophomore Semester 2
			ArrayList<Course> s2List = new ArrayList<Course>();
			tempSemesters.put("2s", s2List);

			
			//Junior Semester 1
			ArrayList<Course> f3List = new ArrayList<Course>();
			tempSemesters.put("3f", f3List);

			
			//Junior Semester 2
			ArrayList<Course> s3List = new ArrayList<Course>();
			tempSemesters.put("3s", s3List);

			
			//Senior Semester 1
			ArrayList<Course> f4List = new ArrayList<Course>();
			tempSemesters.put("4f", f4List);

			
			//Senior Semester 2
			ArrayList<Course> s4List = new ArrayList<Course>();
			tempSemesters.put("4s", s4List);

			//To initialize summer semesters as empty
			ArrayList<Course> u1List = new ArrayList<Course>();
			tempSemesters.put("1u", u1List);
			ArrayList<Course> u2List = new ArrayList<Course>();
			tempSemesters.put("2u", u2List);
			ArrayList<Course> u3List = new ArrayList<Course>();
			tempSemesters.put("3u", u3List);
			ArrayList<Course> u4List = new ArrayList<Course>();
			tempSemesters.put("4u", u4List);
			
			return tempSemesters;
		}

	
	
	//Computer Engineering Planning Sheet
	HashMap <String, ArrayList<Course>> CEDefault() {
		HashMap <String, ArrayList<Course>> tempSemesters = new HashMap<String, ArrayList<Course>> ();
		
		
		//Freshman Semester 1
		ArrayList<Course> f1List = new ArrayList<Course>();
		f1List.add(getCourseByTitle("CASMA123"));
		f1List.add(getCourseByTitle("CASCH131"));
		f1List.add(getCourseByTitle("ENGEK100"));
		f1List.add(getCourseByTitle("ENGEK128")); //Should this be given as a choice with EK127?
		f1List.add(getCourseByTitle("CASWR100"));
		tempSemesters.put("1f", f1List);

		
		
		//Freshman Semester 2
		ArrayList<Course> s1List = new ArrayList<Course>();
		s1List.add(getCourseByTitle("CASMA124"));
		s1List.add(getCourseByTitle("CASPY211"));
		s1List.add(getCourseByTitle("ENGEK131"));
		s1List.add(getCourseByTitle("ENGEK132"));
		s1List.add(getCourseByTitle("CASWR150"));
		tempSemesters.put("1s", s1List);

		
		//Sophomore Semester 1
		ArrayList<Course> f2List = new ArrayList<Course>();
		f2List.add(getCourseByTitle("CASMA225"));
		f2List.add(getCourseByTitle("CASPY212"));
		f2List.add(getCourseByTitle("ENGEK307"));
		f2List.add(getCourseByTitle("ENGEC327")); 
		tempSemesters.put("2f", f2List);

		
		//Sophomore Semester 2
		ArrayList<Course> s2List = new ArrayList<Course>();
		s2List.add(getCourseByTitle("CASMA226"));
		s2List.add(getCourseByTitle("ENGEC311"));
		s2List.add(getCourseByTitle("ENGEK301"));
		s2List.add(getCourseByTitle("ENGEK102")); //Or CASMA142
		//s2List.add(getCourseByTitle("Social Science Elective"));
		tempSemesters.put("2s", s2List);

		
		//Junior Semester 1
		ArrayList<Course> f3List = new ArrayList<Course>();
		f3List.add(getCourseByTitle("ENGEC481"));
		f3List.add(getCourseByTitle("ENGEC413"));
		f3List.add(getCourseByTitle("ENGEC410"));
		f3List.add(getCourseByTitle("CASMA193"));
		//f3List.add(getCourseByTitle("Humanities Elective"));
		tempSemesters.put("3f", f3List);

		
		//Junior Semester 2
		ArrayList<Course> s3List = new ArrayList<Course>();
		s3List.add(getCourseByTitle("ENGEC401"));
		s3List.add(getCourseByTitle("ENGEC440"));
		s3List.add(getCourseByTitle("ENGEC330"));
		s3List.add(getCourseByTitle("ENGEC450"));
		tempSemesters.put("3s", s3List);

		
		//Senior Semester 1
		ArrayList<Course> f4List = new ArrayList<Course>();
		f4List.add(getCourseByTitle("ENGEC463"));
		//f4List.add(getCourseByTitle("Track Elective")); //ENGEC441, ENGEC447, ENGEC535, or ENGEC571
		//f4List.add(getCourseByTitle("Breadth Elective"));
		//f4List.add(getCourseByTitle("Social Science/Humanities"));
		tempSemesters.put("4f", f4List);

		
		//Senior Semester 2
		ArrayList<Course> s4List = new ArrayList<Course>();
		s4List.add(getCourseByTitle("ENGEC464"));
		//s4List.add(getCourseByTitle("Technical Elective"));
		//s4List.add(getCourseByTitle("Technical Elective"));
		//s4List.add(getCourseByTitle("General Education Elective"));
		tempSemesters.put("4s", s4List);

		//To initialize summer semesters as empty
		ArrayList<Course> u1List = new ArrayList<Course>();
		tempSemesters.put("1u", u1List);
		ArrayList<Course> u2List = new ArrayList<Course>();
		tempSemesters.put("2u", u2List);
		ArrayList<Course> u3List = new ArrayList<Course>();
		tempSemesters.put("3u", u3List);
		ArrayList<Course> u4List = new ArrayList<Course>();
		tempSemesters.put("4u", u4List);
		
		/*Technical Elective Defined as:
		Any ENGEC Classes
		All ENG BE, EK, and ME courses at the 300-level and above
		Pre-approved CAS courses:
			CASAS414: Solar and Space Physics
			CASCS440: Artificial Intelligence
			CASCS480: Introduction to Computer Graphics
			CASCS585: Image and Video Computing
			CASMA511: Introduction to Analysis I
			CASMA528: Introduction to Geometry
			CASMA531: Computability and Logic
			CASMA541: Modern Algebra I
			CASMA583: Introduction to Stochastic Processes
			CASPY451: Quantum Physics I
			CASPY452: Quantum Physics II
			SMG SI480:The Business of Technology Innovation						
		*/
		
		/*Breadth Elective Defined as:
		  Any ENG course 400 or above
		  Any CS class 500 or above
		  Pre-approved Courses:
		 	ENGBE209: Principles of Molecular Cell Biology and Biotechnology
		 	CASCS410: Advanced Software Systems 
			CASPY313: Modern Physics 
			CASCS411: Software Engineering 
			CASCS212: Distributed Programming
			CASCS431: Algorithms for Life Sciences 
			CASCS235: Algebraic Algorithms 
			CASCS440: Artificial Intelligence 
			CASCS320: Concepts in Programming Languages 
			CASCS451: Distributed Systems 
			CASCS332: Elementary Theory of Computation 
			CASCS460: Database Systems 
			CASCS350: Fundamentals of Computer Systems
			CASCS480: Computer Graphics
		 */  
		return tempSemesters;
	}

	//Electrical Engineering Planning Sheet
	HashMap <String, ArrayList<Course>> EEDefault() {
		HashMap <String, ArrayList<Course>> tempSemesters = new HashMap<String, ArrayList<Course>> ();
		
		//Freshman Semester 1
		ArrayList<Course> f1List = new ArrayList<Course>();
		f1List.add(getCourseByTitle("CASMA123"));
		f1List.add(getCourseByTitle("CASCH131"));
		f1List.add(getCourseByTitle("ENGEK100"));
		f1List.add(getCourseByTitle("ENGEK127")); //Should this be given as a choice with EK128?
		f1List.add(getCourseByTitle("CASWR100"));
		tempSemesters.put("1f", f1List);

		
		//Freshman Semester 2
		ArrayList<Course> s1List = new ArrayList<Course>();
		s1List.add(getCourseByTitle("CASMA124"));
		s1List.add(getCourseByTitle("CASPY211"));
		s1List.add(getCourseByTitle("ENGEK131"));
		s1List.add(getCourseByTitle("ENGEK132"));
		s1List.add(getCourseByTitle("CASWR150"));
		tempSemesters.put("1s", s1List);

		
		//Sophomore Semester 1
		ArrayList<Course> f2List = new ArrayList<Course>();
		f2List.add(getCourseByTitle("CASMA225"));
		f2List.add(getCourseByTitle("CASPY212"));
		f2List.add(getCourseByTitle("ENGEK307"));
		f2List.add(getCourseByTitle("ENGEK102")); //Or CASMA142
		//f2List.add(getCourseByTitle("Social Science Elective"));
		tempSemesters.put("2f", f2List);

		
		//Sophomore Semester 2
		ArrayList<Course> s2List = new ArrayList<Course>();
		s2List.add(getCourseByTitle("CASMA226"));
		s2List.add(getCourseByTitle("CASPY313"));
		s2List.add(getCourseByTitle("ENGEK301"));
		//s2List.add(getCourseByTitle("Humanities Elective"));
		tempSemesters.put("2s", s2List);

		
		//Junior Semester 1
		ArrayList<Course> f3List = new ArrayList<Course>();
		f3List.add(getCourseByTitle("ENGEC401"));
		f3List.add(getCourseByTitle("ENGEC410"));
		f3List.add(getCourseByTitle("ENGEC311"));
		f3List.add(getCourseByTitle("ENGEC455"));
		tempSemesters.put("3f",f3List);

		
		//Junior Semester 2
		ArrayList<Course> s3List = new ArrayList<Course>();
		s3List.add(getCourseByTitle("ENGEC381"));
		//s3List.add(getCourseByTitle("Systems Elective")); //ENGEC402, ENGEC415, or ENGEC416
		//tempList.add(getCourseByTitle("Electronics Elective")); //ENGEC412, ENGEC417, ENGEC450, ENGEC470, ENGEC571, or ENGEC583
		//s3List.add(getCourseByTitle("Electrophysics Elective")); //ENGEC456, ENGEC470, ENGEC471, ENGEC481, or ENGEC560
		tempSemesters.put("3s", s3List);

		
		//Senior Semester 1
		ArrayList<Course> f4List = new ArrayList<Course>();
		f4List.add(getCourseByTitle("ENGEC463"));
		//f4List.add(getCourseByTitle("Technical Elective"));
		//f4List.add(getCourseByTitle("Computer Elective")); //ENGEC327, ENGEC413, or ENGEC441
		//f4List.add(getCourseByTitle("Social Science/Humanities Elective"));
		tempSemesters.put("4f", f4List);

		
		//Senior Semester 2
		ArrayList<Course> s4List = new ArrayList<Course>();
		s4List.add(getCourseByTitle("ENGEC464"));
		//s4List.add(getCourseByTitle("Technical Elective"));
		//s4List.add(getCourseByTitle("Technical Elective"));
		//s4List.add(getCourseByTitle("General Education Elective"));
		/*Technical Elective Defined as:
		Any ENGEC Classes
		All ENG BE, EK, and ME courses at the 300-level and above
		Pre-approved CAS courses:
			CASAS414: Solar and Space Physics
			CASCS440: Artificial Intelligence
			CASCS480: Introduction to Computer Graphics
			SMG SI480:The Business of Technology Innovation
								
		*/
		tempSemesters.put("4s", s4List);
		
		//To initialize summer semesters as empty
		ArrayList<Course> u1List = new ArrayList<Course>();
		tempSemesters.put("1u", u1List);
		ArrayList<Course> u2List = new ArrayList<Course>();
		tempSemesters.put("2u", u2List);
		ArrayList<Course> u3List = new ArrayList<Course>();
		tempSemesters.put("3u", u3List);
		ArrayList<Course> u4List = new ArrayList<Course>();
		tempSemesters.put("4u", u4List);
		
		return tempSemesters;
	}
	
	//Biomedical Engineering Planning Sheet
	HashMap <String, ArrayList<Course>> BMEDefault() {
		HashMap <String, ArrayList<Course>> tempSemesters = new HashMap<String, ArrayList<Course>> ();

		
		//Freshman Semester 1
		ArrayList<Course> f1List = new ArrayList<Course>();
		f1List.add(getCourseByTitle("CASMA123"));
		f1List.add(getCourseByTitle("ENGEK100"));
		f1List.add(getCourseByTitle("CASCH101"));
		f1List.add(getCourseByTitle("ENGEK127"));
		f1List.add(getCourseByTitle("CASWR100"));
		tempSemesters.put("1f", f1List);

		
		//Freshman Semester 2
		ArrayList<Course> s1List = new ArrayList<Course>();
		s1List.add(getCourseByTitle("CASMA124"));
		s1List.add(getCourseByTitle("CASPY211"));
		s1List.add(getCourseByTitle("CASCH102"));
		s1List.add(getCourseByTitle("ENGEK131"));
		s1List.add(getCourseByTitle("ENGEK132"));
		tempSemesters.put("1s", s1List);

		
		//Sophomore Semester 1
		ArrayList<Course> f2List = new ArrayList<Course>();
		f2List.add(getCourseByTitle("CASMA225"));
		f2List.add(getCourseByTitle("CASPY212"));
		f2List.add(getCourseByTitle("ENGEK307"));
		f2List.add(getCourseByTitle("ENGEK102")); //Or CASMA142
		f2List.add(getCourseByTitle("CASWR150"));
		tempSemesters.put("2f", f2List);

		
		//Sophomore Semester 2
		ArrayList<Course> s2List = new ArrayList<Course>();
		s2List.add(getCourseByTitle("CASMA226"));
		s2List.add(getCourseByTitle("ENGBE209"));
		s2List.add(getCourseByTitle("ENGEK301"));
		s2List.add(getCourseByTitle("ENGBE200"));
		//s2List.add(getCourseByTitle("Social Science Elective"));
		tempSemesters.put("2s", s2List);

		
		//Junior Semester 1
		ArrayList<Course> f3List = new ArrayList<Course>();
		f3List.add(getCourseByTitle("ENGEK424"));
		f3List.add(getCourseByTitle("CASBI315"));
		f3List.add(getCourseByTitle("ENGBE491"));
		f3List.add(getCourseByTitle("ENGBE401"));
		//f3List.add(getCourseByTitle("Humanities Elective"));
		tempSemesters.put("3f", f3List);

		
		//Junior Semester 2
		ArrayList<Course> s3List = new ArrayList<Course>();
		//s3List.add(getCourseByTitle("Biomedical Elective"));
		//s3List.add(getCourseByTitle("Fields Elective")); //ENGBE419, ENGBE420, ENGBE435, ENGBE436
		s3List.add(getCourseByTitle("ENGBE492"));
		s3List.add(getCourseByTitle("ENGBE402"));
		//s3List.add(getCourseByTitle("Social Science/Humanities Elective");
		tempSemesters.put("3s", s3List);		

		
		//Senior Semester 1
		ArrayList<Course> f4List = new ArrayList<Course>();
		//f4List.add(getCourseByTitle("Engineering Elective"));
		//f4List.add(getCourseByTitle("Professional Elective"));
		f4List.add(getCourseByTitle("ENGBE467"));
		f4List.add(getCourseByTitle("ENGBE465"));
		//f4List.add(getCourseByTitle("Computer Elective")); //ENGEC327, ENGEC413, or ENGEC441
		//f4List.add(getCourseByTitle("General Education Elective"));
		tempSemesters.put("4f", f4List);

		
		//Senior Semester 2
		ArrayList<Course> s4List = new ArrayList<Course>();
		//s4List.add(getCourseByTitle("Biomedical Elective"));
		//s4List.add(getCourseByTitle("Biomedical Elective"));
		//s4List.add(getCourseByTitle("Professional Elective"));
		s4List.add(getCourseByTitle("ENGEC464"));
		tempSemesters.put("4s", s4List);

		//To initialize summer semesters as empty
		ArrayList<Course> u1List = new ArrayList<Course>();
		tempSemesters.put("1u", u1List);
		ArrayList<Course> u2List = new ArrayList<Course>();
		tempSemesters.put("2u", u2List);
		ArrayList<Course> u3List = new ArrayList<Course>();
		tempSemesters.put("3u", u3List);
		ArrayList<Course> u4List = new ArrayList<Course>();
		tempSemesters.put("4u", u4List);
		
		/*
		Professional Electives Defined as:
		All BE, EC, EK, and ME 300 level or above courses
		CAS CH 203, CAS CH 204 and all CAS CH 300, 400 and 500 level courses (except: CAS CH 391, 392, 401, 402, 491, 492). 
		All CAS PY 300 and 400 level courses (except PY 371, 401, 402, 482, 491, 492). 
		All CAS MA 300, 400, and 500 level courses (except CAS MA 381, 401, 402). 
		All CAS BI 300, 400 and 500 level courses (except BI 315, 371, 372, 391, 392) 
		ENG BF 527 Applications in Bioinformatics SAR HS 360 Muscle Biology in Health & Disease CAS CH 629 � DNA Nanotechnology 
		ENG EK 156 Design & Manufacture SMG SI 480 The Business of Technology Innovation
		 */
		
		/* Engineering Electives Defined as:
		 * 
			ENG EC 311 Intro to Logic Design 
			ENG EC 312 Computer Organization 
			ENG EC 412 Analog Electronics 
			ENG EC 415 Communications Systems 
			ENG EC 416 Intro Digital Signal Processing 
			ENG EC 450 Microprocessors 
			ENG EC 455 Electromagnetic Systems I 
			ENG EC 471 Physics Semiconductor Devices 
			ENG EC 481 Nanomatrls & Nanotechnology 
			ENG EC 505 Stochastic Processes 
			ENG EC 580 Modern Active Circuit Design 
			ENG ME 302 Engineering Mechanics II 
			ENG ME 304 Energy & Thermodynamics 
			ENG ME 305 Mechanics of Materials 
			ENG ME 309 Structural Mechanics 
			ENG ME 400 Engineering Mathematics 
			ENG ME 407** Comp-Aided Design & Mnfcture 
			ENG ME 419 Heat Transfer 
			ENG ME 441 Mechanical Vibrations 
			ENG ME 502 Intellectual Assets 
			ENG ME 555 MEMS: Fabrication & Materials 
			ENG EC 456 Electromagnetic Systems II ENG ME 306 Material Science 
			Additionally, any Biomedical Elective (below) that has not been used to satisfy the BME Elective requirement (except BF 527) may be used as an Engineering Elective. 
		 * 
		 */
		
		/*Biomedical Engineering Electives
		 * 
		 * 
		 */
		return tempSemesters;
	}
	
	
	HashMap <String, ArrayList<Course>> TestDefault(){
		HashMap <String, ArrayList<Course>> tempSemesters = new HashMap<String, ArrayList<Course>> ();
		
		
		ArrayList<Course> tempList = new ArrayList<Course>();
		tempList.add(getCourseByTitle("CASMA123"));
		tempList.add(getCourseByTitle("CASCH131"));
		tempList.add(getCourseByTitle("ENGEK100"));
		tempList.add(getCourseByTitle("ENGEK127")); //Should this be given as a choice with EK128?
		tempList.add(getCourseByTitle("CASWR100"));
		tempSemesters.put("1f", tempList);
		return tempSemesters;

	}
	
	//Mechanical Engineering Planning Sheet
	HashMap <String, ArrayList<Course>> MEDefault() {
		HashMap <String, ArrayList<Course>> tempSemesters = new HashMap<String, ArrayList<Course>> ();
	
		
		//Freshman Semester 1
		ArrayList<Course> f1List = new ArrayList<Course>();
		f1List.add(getCourseByTitle("CASMA123"));
		f1List.add(getCourseByTitle("CASCH131"));
		f1List.add(getCourseByTitle("ENGEK100"));
		f1List.add(getCourseByTitle("ENGEK127")); //Should this be given as a choice with EK128?
		f1List.add(getCourseByTitle("CASWR100"));
		tempSemesters.put("1f", f1List);

		
		//Freshman Semester 2
		ArrayList<Course> s1List = new ArrayList<Course>();
		s1List.add(getCourseByTitle("CASMA124"));
		s1List.add(getCourseByTitle("CASPY211"));
		s1List.add(getCourseByTitle("ENGEK131"));
		s1List.add(getCourseByTitle("ENGEK132"));
		s1List.add(getCourseByTitle("CASWR150"));
		tempSemesters.put("1s", s1List);

		
		//Sophomore Semester 1
		ArrayList<Course> f2List = new ArrayList<Course>();
		f2List.add(getCourseByTitle("CASMA225"));
		f2List.add(getCourseByTitle("CASPY212"));
		f2List.add(getCourseByTitle("ENGEK301"));
		f2List.add(getCourseByTitle("ENGEK156"));
		//f2List.add(getCourseByTitle("Social Science Elective")); 
		tempSemesters.put("2f", f2List);

		
		//Sophomore Semester 2
		ArrayList<Course> s2List = new ArrayList<Course>();
		s2List.add(getCourseByTitle("CASMA226"));
		//s2List.add(getCourseByTitle("Natural Science Elective"));
		s2List.add(getCourseByTitle("ENGEK307"));
		s2List.add(getCourseByTitle("ENGEK102")); //Or CASMA142
		//s2List.add(getCourseByTitle("Humanities Elective"));
		tempSemesters.put("2s", s2List);

		
		//Junior Semester 1
		ArrayList<Course> f3List = new ArrayList<Course>();
		f3List.add(getCourseByTitle("ENGME359"));
		f3List.add(getCourseByTitle("ENGME305"));
		f3List.add(getCourseByTitle("ENGME304"));
		f3List.add(getCourseByTitle("ENGME303"));
		//f3List.add(getCourseByTitle("Social Science OR Humanities Elective"));
		tempSemesters.put("3f", f3List);

		
		//Junior Semester 2
		ArrayList<Course> s3List = new ArrayList<Course>();
		s3List.add(getCourseByTitle("ENGME360"));
		s3List.add(getCourseByTitle("ENGME302"));
		s3List.add(getCourseByTitle("ENGME306"));
		s3List.add(getCourseByTitle("ENGME419"));
		s3List.add(getCourseByTitle("ENGME366"));
		tempSemesters.put("3s", s3List);

		
		//Senior Semester 1
		ArrayList<Course> f4List = new ArrayList<Course>();
		f4List.add(getCourseByTitle("ENGME460"));
		//f4List.add(getCourseByTitle("Advanced Elective"));
		//f4List.add(getCourseByTitle("Advanced Elective"));
		f4List.add(getCourseByTitle("ENGME410"));
		tempSemesters.put("4f", f4List);

		
		//Senior Semester 2
		ArrayList<Course> s4List = new ArrayList<Course>();
		s4List.add(getCourseByTitle("ENGME461"));
		//s4List.add(getCourseByTitle("Advanced Elective"));
		//s4List.add(getCourseByTitle("Advanced Elective"));
		//s4List.add(getCourseByTitle("General Education Elective"));
		tempSemesters.put("4s", s4List);

		//To initialize summer semesters as empty
		ArrayList<Course> u1List = new ArrayList<Course>();
		tempSemesters.put("1u", u1List);
		ArrayList<Course> u2List = new ArrayList<Course>();
		tempSemesters.put("2u", u2List);
		ArrayList<Course> u3List = new ArrayList<Course>();
		tempSemesters.put("3u", u3List);
		ArrayList<Course> u4List = new ArrayList<Course>();
		tempSemesters.put("4u", u4List);
		
		/* Advanced Elective Defined as:
		  	All ENG courses 300 level or above, (without overlap)
			Additional Pre-approved:
				CAS AS 414 � Solar and Space Physics 
				SMG SI 480 � The Business of Technology Innovation. 
			Other 300-level or above Mathematics and Natural Science courses may be acceptable by petition
		 */
		
		/*
		 * The Natural Science Elective:
			ENG BE 209 � Principles of Molecular Cell Biology & Biotechnology 
			Astronomy (AS) - 200-level or higher course or any 100-level course that includes a lab 
			Biology (BI) - Any 200-level or higher course or any 100-level course that includes a lab 
			Neuroscience (NE) - All 
			Chemistry (CH) - Any 200-level or higher course 
			Physics (PY) - Any 300-level or higher course, CAS PY 231- The Physics in Music 
			Earth Science (ES) - Any 300-level or higher course. Also the following: 
				CAS ES 101 � Dynamic Earth CAS ES 142 � Intro Beach & Shoreline Processes 
 				CAS ES 105 � Environmental Earth Sciences CAS ES 144 - Oceanography 
 				CAS ES 140 � Earthquakes, Volcanoes, Natural Disasters CAS ES 222 - Mineralogy 
			The following GE courses: 
 				CAS GE 101 � Natural Environ: Atmosphere CAS GE 375 � Intro Quant Environmental Modeling 
 				CAS GE 104 � Natural Environ: Physical Landscape CAS GE 440 � Digital Image Proc & Remote Sensing 
 				CAS GE 110 � Our Changing Planet CAS GE 445 � Physical Models in Remote Sensing 
 				CAS GE 302 � Remote Sensing of the Environment CAS GE 448 � Remote Sensing of Vegetation 
 				CAS GE 307 � Biogeography CAS GE 456 � Terrestrial Ecosystems & Carbon Cycle 
 				CAS GE 310 � Climate & the Environment CAS GE 483 � Geodynamics II: Fluids & Fluid Transport 
 				CAS GE 365 � Intro Geographic Information Systems 

		 */
	
		return tempSemesters;
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
		ArrayList<String> tempCourseTitle = new ArrayList<String>();
		
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
				//tempCourse.cid = eElement.getElementsByTagName("link").item(0).getTextContent();
				String prereqs = eElement.getElementsByTagName("prereqs").item(0).getTextContent();
				ArrayList<String> prereqlist = new ArrayList<String>(Arrays.asList(prereqs.split(" ")));
				tempCourse.prereqs = (ArrayList<String>) prereqlist;
				tempCourse.credits = Integer.parseInt(eElement.getElementsByTagName("credits").item(0).getTextContent());
				tempCourse.user = false;
				
				tempCourseList.add(tempCourse);
				tempCourseTitle.add(tempCourse.getFullTitle());
			}
	 
		
		}
		
		courseList = tempCourseList;
		courseTitleList = tempCourseTitle;
		
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
		 
		 //Tim : I added some line to make an array of Strings of Course names For display on serach
		 
		 ArrayList<Course> tempList = new ArrayList<Course>();

		 
		 for (String mystr : semesterLabels) {
			 tempList.clear();
			 for (int j = 0; j < 5; j++) { 
//				 System.out.println(i);
				 tempList.add(courseList.get(i));
				 i++;
			 }
			 tempSemesters.put(mystr, new ArrayList<Course>(tempList));
		 }
			if(courseTitleList == null){
				courseTitleList = new ArrayList<String>();
				for(Course c: courseList)
				{
					courseTitleList.add(c.getFullTitle());
				}
			}
		// semesterLists = tempSemesters;
		 //Tim : Added to change default stores
		 //System.out.println(getCourseByTitle("ENGEK127"));
		semesterLists = new HashMap<String, ArrayList<Course>> ();
			//semesterLists = BMEDefault();
	}

	public ArrayList<Course> getClassWithYear(int year, char semester) {
	char yearChar = Character.forDigit(year, 10); 
	StringBuilder semesterChoice = new StringBuilder(2).append(yearChar).append(semester);
	System.out.println(semesterLists);
	return semesterLists.get(semesterChoice.toString());
}

	public void addClassWithYearToEnd(Course course, int year, char semester) {
		char yearChar = Character.forDigit(year, 10); 
		StringBuilder semesterChoice = new StringBuilder(2).append(yearChar).append(semester);
		semesterLists.get(semesterChoice.toString()).add(new Course(course));

		this.save();
	}
	
	public void addClassWithYear(Course course, int year, char semester, int position) {
		char yearChar = Character.forDigit(year, 10); 
		StringBuilder semesterChoice = new StringBuilder(2).append(yearChar).append(semester);
		semesterLists.get(semesterChoice.toString()).add(position,new Course(course));

		this.save();
		
	}
	
	public Course removeClassWithYear(Course c, int year, char semester) {
		char yearChar = Character.forDigit(year, 10); 
		StringBuilder semesterChoice = new StringBuilder(2).append(yearChar).append(semester);
		for (Course remc: semesterLists.get(semesterChoice.toString())) {
			if (c.getTitle().equals(remc.getTitle())) {
				semesterLists.get(semesterChoice.toString()).remove(remc);
				this.save();
				return c;
			}
		}

	    return null;
	}
	
	public void save()
	{
		this.saveState("savefile", Global.myContext);
	}
	
	public Course addCourse(String name, String school, String dept, String cid,
			String description, ArrayList<String> prereqs, int credits, Context context) {
		Course c = new Course(name, school, dept, cid,
				description, prereqs, credits);
		courseList.add(c);
		courseTitleList.add(c.getFullTitle());
		System.out.println(c.getFullTitle());
		this.saveState("savefile", context.getApplicationContext());
		return c;
	}
	
	
	public void printLists() {
		 for (Map.Entry entry : semesterLists.entrySet()) {
			 ArrayList<Course> iterList = (ArrayList<Course>) entry.getValue();
			 for (Course c : iterList) {
					System.out.println(entry.getKey() + " : " + c.getFullTitle()); 
			 }
		 }
	}
	@Override
	public void printCourseArray(ArrayList<Course> list) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getIndexWithTitle(String s) {
		return courseTitleList.indexOf(s);
	}

	public void saveState(String filename, Context context) {
		  try {
			  
			  FileOutputStream cos = context.getApplicationContext().openFileOutput("usercourses", 0);
			  
				DocumentBuilderFactory docFactory1 = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder1 = docFactory1.newDocumentBuilder();
			  
				Document doc1 = docBuilder1.newDocument();
				Element coursesElement = doc1.createElement("courses");
				doc1.appendChild(coursesElement);
				
				for (Course c: courseList) {
					if (c.user) {
						Element courseEL = doc1.createElement("course");
						
						Element cname = doc1.createElement("name");
						cname.appendChild(doc1.createTextNode(new String(c.name)));
						courseEL.appendChild(cname);
						
						Element cschool = doc1.createElement("school");
						cschool.appendChild(doc1.createTextNode(new String(c.school)));
						courseEL.appendChild(cschool);
						
						Element cdept = doc1.createElement("dept");
						cdept.appendChild(doc1.createTextNode(new String(c.dept)));
						courseEL.appendChild(cdept);
						
						Element ccid = doc1.createElement("cid");
						ccid.appendChild(doc1.createTextNode(new String(c.cid)));
						courseEL.appendChild(ccid);
						
						Element cdes = doc1.createElement("description");
						cdes.appendChild(doc1.createTextNode(new String(c.description)));
						courseEL.appendChild(cdes);					
						
						if (!c.prereqs.isEmpty()) {
							String tempString = new String ("");
							 for (String semString: c.prereqs) {
								 tempString = tempString + semString + " ";							 
								 }
							 Element cprereq = doc1.createElement("prereqs");
								cprereq.appendChild(doc1.createTextNode(new String(tempString)));
								courseEL.appendChild(cprereq);
						}
						else {
							Element cprereq = doc1.createElement("prereqs");
							cprereq.appendChild(doc1.createTextNode(new String()));
							courseEL.appendChild(cprereq);
						}
						
						
							
						Element ccredits = doc1.createElement("credits");
						ccredits.appendChild(doc1.createTextNode(new String(String.valueOf(c.credits))));
						courseEL.appendChild(ccredits);
						
						coursesElement.appendChild(courseEL);
						
					}
				}
				
				TransformerFactory transformerFactory1 = TransformerFactory.newInstance();
				Transformer transformer1 = transformerFactory1.newTransformer();
				DOMSource source1 = new DOMSource(doc1);
				StringWriter writer1 = new StringWriter();
				
				transformer1.transform(source1, new StreamResult(writer1));
				String output1 = writer1.getBuffer().toString().replaceAll("\n|\r", "");
				// Output to console for testing
				// StreamResult result = new StreamResult(System.out);
				cos.write(output1.getBytes());
				cos.close();
			  
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			  
			FileOutputStream fos = context.getApplicationContext().openFileOutput(filename, 0);
			  
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	 
			// semester elements
			Document doc = docBuilder.newDocument();
			Element semestersElement = doc.createElement("semesters");
			doc.appendChild(semestersElement);

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

			 String tempString;
			 for (String semString: semesterLabels) {
				 tempString = new String();
				 for (Course c: semesterLists.get(semString)) {
					 //System.out.println(c.getFullTitle());
					 if (c!= null)
						 if (c.completed)
							 tempString = tempString + c.getTitle() + "!T ";
						 else if (!c.completed)
							 tempString = tempString + c.getTitle() + "!F ";
				 }
				 Element tempEl = doc.createElement(new StringBuffer(semString).reverse().toString());
				 tempEl.appendChild(doc.createTextNode(tempString));
				 semestersElement.appendChild(tempEl);
			 }
			 
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StringWriter writer = new StringWriter();
			
			transformer.transform(source, new StreamResult(writer));
			String output = writer.getBuffer().toString().replaceAll("\n|\r", "");
			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);
			fos.write(output.getBytes());
			fos.close();
		//	System.out.println("File saved!");
		  
		  } catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("transformer exception");
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("parser exception");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {} 
	}

	public void loadState(String filename, Context context) {
		try {
			FileInputStream fis1 = context.getApplicationContext().openFileInput("usercourses");
			 InputStreamReader inputStreamReader1 = new InputStreamReader(fis1);
			 BufferedReader bufferedReader1 = new BufferedReader(inputStreamReader1);
			 StringBuilder sb1 = new StringBuilder();
			 String line1;
			 while ((line1 = bufferedReader1.readLine()) != null) {
			     sb1.append(line1);
			 }
			//InputStream inputStream= new FileInputStream(fXmlFile);
			DocumentBuilderFactory dbFactory1 = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder1 = dbFactory1.newDocumentBuilder();
			
			InputSource is1 = new InputSource(new StringReader(sb1.toString()));
			
			Document doc1 = dBuilder1.parse(is1);
		 
			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc1.getDocumentElement().normalize();
			
			NodeList nList = doc1.getElementsByTagName("course");
			
			
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
					//tempCourse.cid = eElement.getElementsByTagName("link").item(0).getTextContent();
					String prereqs = eElement.getElementsByTagName("prereqs").item(0).getTextContent();
					ArrayList<String> prereqlist = new ArrayList<String>(Arrays.asList(prereqs.split(" ")));
					tempCourse.prereqs = (ArrayList<String>) prereqlist;
					tempCourse.credits = Integer.parseInt(eElement.getElementsByTagName("credits").item(0).getTextContent());
					tempCourse.user = true;
					
					courseList.add(tempCourse);
					courseTitleList.add(tempCourse.getFullTitle());
				}
			
			
			}
			
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			
			
			FileInputStream fis = context.getApplicationContext().openFileInput(filename);
			 InputStreamReader inputStreamReader = new InputStreamReader(fis);
			 BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			 StringBuilder sb = new StringBuilder();
			 String line;
			 while ((line = bufferedReader.readLine()) != null) {
			     sb.append(line);
			 }
			//InputStream inputStream= new FileInputStream(fXmlFile);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			InputSource is = new InputSource(new StringReader(sb.toString()));
			
			Document doc = dBuilder.parse(is);
		 
			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();
			
			NodeList sList = doc.getElementsByTagName("semesters");
			
		//	ArrayList<String> tempSemCourses = new ArrayList<String>();
			
			HashMap<String, ArrayList<Course>> semesterListsTEMP = new HashMap<String, ArrayList<Course>>();		
				 
				Node sNode = sList.item(0);
				//tempSemCourses.clear();

		                 NodeList nl = sNode.getChildNodes();
		                 for(int j=0; j<nl.getLength(); j++)
		                 {
		                     Node nd = nl.item(j);
		                    // System.out.println(nd.getTextContent());
					
		                     String semCourseList = nd.getTextContent();
		                     ArrayList<String> tempSemCourses = new ArrayList<String>(Arrays.asList(new String(semCourseList).split(" ")));

							ArrayList<Course> tempSemCourseList = new ArrayList<Course>();
							//ArrayList<String> tempSemCoursesSep;
							Course tempc;
							for (String courseTitle: tempSemCourses) {
								//System.out.println(courseTitle);
								if (courseTitle.isEmpty()) {
									break;
								}
								ArrayList<String> tempSemCoursesSep = new ArrayList<String>(Arrays.asList(new String(courseTitle).split("!")));
								
								tempc = new Course(getCourseByTitle(tempSemCoursesSep.get(0)));
								if (tempSemCoursesSep.get(1).equals("T"))
									tempc.completed = true;
								else if (tempSemCoursesSep.get(1).equals("F"))
									tempc.completed = false;
								tempSemCourseList.add(tempc);
							}
				//			System.out.println(nd.getNodeName());
							semesterListsTEMP.put(new StringBuffer(nd.getNodeName()).reverse().toString(), new ArrayList<Course>(tempSemCourseList)); 
				}
			semesterLists = semesterListsTEMP;
			fis.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	
	public final static int PLANNER_BME = 0;
	public final static int PLANNER_EE = 1;
	public final static int PLANNER_CE = 2;
	public final static int PLANNER_ME = 3;
	public final static int PLANNER_DEFAULT = 4;
	
	
	public void setSemester(int major) {
		switch (major) {
		case PLANNER_BME:{
			semesterLists = BMEDefault();
			break;}
		case PLANNER_EE:{
			semesterLists = EEDefault();
			break;}
		case PLANNER_CE:{
			semesterLists = CEDefault();
			break;}
		case PLANNER_ME:{
			semesterLists = MEDefault();
			break;}
		case PLANNER_DEFAULT:{
			semesterLists = BlankDefault();
			break;}
		default:
			break;
		}
		
	}
	
	
}

