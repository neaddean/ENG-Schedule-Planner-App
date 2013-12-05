package courseModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;

import android.content.Context;
import courseModel.Course;

import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader; 

public class CourseModel{// implements ModelAccessor{
	
	ArrayList<Course> courseList;
	
	HashMap<String, ArrayList<Course>> semesterLists;

	
	public final static char FALL = 'f';
	public final static char SPRING = 's';
	public final static char SUMMER = 'u';
	
	public final static int EXTERNAL_CREDITS = 0;
	public final static int FRESHMAN_YEAR = 1;
	public final static int SOPHOMORE_YEAR = 2;
	public final static int JUNIOR_YEAR = 3;
	public final static int SENIOR_YEAR = 4;
	
	
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
	
	public Course getCourseByTitle(String Title) {
		for (Course c: courseList) {
			if (c.getTitle() == Title)
				return c;
		}
		return null;
	}
	
	HashMap <String, ArrayList<Course>> CEDefault() {
		HashMap <String, ArrayList<Course>> tempSemesters = new HashMap<String, ArrayList<Course>> ();
		
		ArrayList<Course> tempList = new ArrayList<Course>();
		
		//Freshman Semester 1
		tempList.add(getCourseByTitle("CASMA123"));
		tempList.add(getCourseByTitle("CASCH131"));
		tempList.add(getCourseByTitle("ENGEK100"));
		tempList.add(getCourseByTitle("ENGEK128")); //Should this be given as a choice with EK127?
		tempList.add(getCourseByTitle("CASWR100"));
		tempSemesters.put("1f", tempList);
		tempList.clear();
		
		//Freshman Semester 2
		tempList.add(getCourseByTitle("CASMA124"));
		tempList.add(getCourseByTitle("CASPY211"));
		tempList.add(getCourseByTitle("ENGEK131"));
		tempList.add(getCourseByTitle("ENGEK132"));
		tempList.add(getCourseByTitle("CASWR150"));
		tempSemesters.put("1s", tempList);
		tempList.clear();
		
		//Sophomore Semester 1
		tempList.add(getCourseByTitle("CASMA225"));
		tempList.add(getCourseByTitle("CASPY212"));
		tempList.add(getCourseByTitle("ENGEK307"));
		tempList.add(getCourseByTitle("ENGEC327")); 
		tempSemesters.put("2f", tempList);
		tempList.clear();
		
		//Sophomore Semester 2
		tempList.add(getCourseByTitle("CASMA226"));
		tempList.add(getCourseByTitle("CASEC311"));
		tempList.add(getCourseByTitle("ENGEK301"));
		tempList.add(getCourseByTitle("ENGEK102")); //Or CASMA142
		//tempList.add(getCourseByTitle("Social Science Elective"));
		tempSemesters.put("2s", tempList);
		tempList.clear();
		
		//Junior Semester 1
		tempList.add(getCourseByTitle("ENGEC481"));
		tempList.add(getCourseByTitle("ENGEC413"));
		tempList.add(getCourseByTitle("ENGEC410"));
		tempList.add(getCourseByTitle("ENGEC193"));
		//tempList.add(getCourseByTitle("Humanities Elective"));
		tempSemesters.put("3f", tempList);
		tempList.clear();
		
		//Junior Semester 2
		tempList.add(getCourseByTitle("ENGEC401"));
		tempList.add(getCourseByTitle("ENGEC440"));
		tempList.add(getCourseByTitle("ENGEC330"));
		tempList.add(getCourseByTitle("ENGEC450"));
		tempSemesters.put("3s", tempList);
		tempList.clear();
		
		//Senior Semester 1
		tempList.add(getCourseByTitle("ENGEC463"));
		//tempList.add(getCourseByTitle("Track Elective")); //ENGEC441, ENGEC447, ENGEC535, or ENGEC571
		//tempList.add(getCourseByTitle("Breadth Elective"));
		//tempList.add(getCourseByTitle("Social Science/Humanities"));
		tempSemesters.put("4f", tempList);
		tempList.clear();
		
		//Senior Semester 2
		tempList.add(getCourseByTitle("ENGEC464"));
		//tempList.add(getCourseByTitle("Technical Elective"));
		//tempList.add(getCourseByTitle("Technical Elective"));
		//tempList.add(getCourseByTitle("General Education Elective"));

		tempSemesters.put("4s", tempList);
		tempList.clear();
		
		
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
		return null;
	}

	HashMap <String, ArrayList<Course>> EEDefault() {
		HashMap <String, ArrayList<Course>> tempSemesters = new HashMap<String, ArrayList<Course>> ();
		
		ArrayList<Course> tempList = new ArrayList<Course>();
		
		//Freshman Semester 1
		tempList.add(getCourseByTitle("CASMA123"));
		tempList.add(getCourseByTitle("CASCH131"));
		tempList.add(getCourseByTitle("ENGEK100"));
		tempList.add(getCourseByTitle("ENGEK127")); //Should this be given as a choice with EK128?
		tempList.add(getCourseByTitle("CASWR100"));
		tempSemesters.put("1f", tempList);
		tempList.clear();
		
		//Freshman Semester 2
		tempList.add(getCourseByTitle("CASMA124"));
		tempList.add(getCourseByTitle("CASPY211"));
		tempList.add(getCourseByTitle("ENGEK131"));
		tempList.add(getCourseByTitle("ENGEK132"));
		tempList.add(getCourseByTitle("CASWR150"));
		tempSemesters.put("1s", tempList);
		tempList.clear();
		
		//Sophomore Semester 1
		tempList.add(getCourseByTitle("CASMA225"));
		tempList.add(getCourseByTitle("CASPY212"));
		tempList.add(getCourseByTitle("ENGEK307"));
		tempList.add(getCourseByTitle("ENGEK102")); //Or CASMA142
		//tempList.add(getCourseByTitle("Social Science Elective"));
		tempSemesters.put("2f", tempList);
		tempList.clear();
		
		//Sophomore Semester 2
		tempList.add(getCourseByTitle("CASMA226"));
		tempList.add(getCourseByTitle("CASPY313"));
		tempList.add(getCourseByTitle("ENGEK301"));
		//tempList.add(getCourseByTitle("Humanities Elective"));
		tempSemesters.put("2s", tempList);
		tempList.clear();
		
		//Junior Semester 1
		tempList.add(getCourseByTitle("ENGEC401"));
		tempList.add(getCourseByTitle("ENGEC410"));
		tempList.add(getCourseByTitle("ENGEC311"));
		tempList.add(getCourseByTitle("ENGEC455"));
		tempSemesters.put("3f", tempList);
		tempList.clear();
		
		//Junior Semester 2
		tempList.add(getCourseByTitle("ENGEC381"));
		//tempList.add(getCourseByTitle("Systems Elective")); //ENGEC402, ENGEC415, or ENGEC416
		//tempList.add(getCourseByTitle("Electronics Elective")); //ENGEC412, ENGEC417, ENGEC450, ENGEC470, ENGEC571, or ENGEC583
		//tempList.add(getCourseByTitle("Electrophysics Elective")); //ENGEC456, ENGEC470, ENGEC471, ENGEC481, or ENGEC560
		tempSemesters.put("3s", tempList);
		tempList.clear();
		
		//Senior Semester 1
		tempList.add(getCourseByTitle("ENGEC463"));
		//tempList.add(getCourseByTitle("Technical Elective"));
		//tempList.add(getCourseByTitle("Computer Elective")); //ENGEC327, ENGEC413, or ENGEC441
		//tempList.add(getCourseByTitle("Social Science/Humanities Elective"));
		tempSemesters.put("4f", tempList);
		tempList.clear();
		
		//Senior Semester 2
		tempList.add(getCourseByTitle("ENGEC464"));
		//tempList.add(getCourseByTitle("Technical Elective"));
		//tempList.add(getCourseByTitle("Technical Elective"));
		//tempList.add(getCourseByTitle("General Education Elective"));
		/*Technical Elective Defined as:
		Any ENGEC Classes
		All ENG BE, EK, and ME courses at the 300-level and above
		Pre-approved CAS courses:
			CASAS414: Solar and Space Physics
			CASCS440: Artificial Intelligence
			CASCS480: Introduction to Computer Graphics
			SMG SI480:The Business of Technology Innovation
								
		*/
		tempSemesters.put("4s", tempList);
		tempList.clear();
		
		
		//do other semesters
		//must also initialize summer semester
		//see load file for example
		//(its in a confusing for loop though)
		return null;
	}
	
	HashMap <String, ArrayList<Course>> BMEDefault() {
		HashMap <String, ArrayList<Course>> tempSemesters = new HashMap<String, ArrayList<Course>> ();
		
		ArrayList<Course> tempList = new ArrayList<Course>();
		
		//Freshman Semester 1
		tempList.add(getCourseByTitle("CASMA123"));
		tempList.add(getCourseByTitle("ENGEK100"));
		tempList.add(getCourseByTitle("CASCH101"));
		tempList.add(getCourseByTitle("ENGEK127"));
		tempList.add(getCourseByTitle("CASWR100"));
		tempSemesters.put("1f", tempList);
		tempList.clear();
		
		//Freshman Semester 2
		tempList.add(getCourseByTitle("CASMA124"));
		tempList.add(getCourseByTitle("CASPY211"));
		tempList.add(getCourseByTitle("CASCH102"));
		tempList.add(getCourseByTitle("ENGEK131"));
		tempList.add(getCourseByTitle("ENGEK132"));
		tempSemesters.put("1s", tempList);
		tempList.clear();
		
		//Sophomore Semester 1
		tempList.add(getCourseByTitle("CASMA225"));
		tempList.add(getCourseByTitle("CASPY212"));
		tempList.add(getCourseByTitle("ENGEK307"));
		tempList.add(getCourseByTitle("ENGEK102")); //Or CASMA142
		tempList.add(getCourseByTitle("CASWR150"));
		tempSemesters.put("2f", tempList);
		tempList.clear();
		
		//Sophomore Semester 2
		tempList.add(getCourseByTitle("CASMA226"));
		tempList.add(getCourseByTitle("ENGBE209"));
		tempList.add(getCourseByTitle("ENGEK301"));
		tempList.add(getCourseByTitle("ENGBE200"));
		//tempList.add(getCourseByTitle("Social Science Elective"));
		tempSemesters.put("2s", tempList);
		tempList.clear();
		
		//Junior Semester 1
		tempList.add(getCourseByTitle("ENGEC424"));
		tempList.add(getCourseByTitle("CASBI315"));
		tempList.add(getCourseByTitle("ENGBE491"));
		tempList.add(getCourseByTitle("ENGBE401"));
		//tempList.add(getCourseByTitle("Humanities Elective"));
		tempSemesters.put("3f", tempList);
		tempList.clear();
		
		//Junior Semester 2
		//tempList.add(getCourseByTitle("Biomedical Elective"));
		//tempList.add(getCourseByTitle("Fields Elective")); //ENGBE419, ENGBE420, ENGBE435, ENGBE436
		tempList.add(getCourseByTitle("ENGEC492"));
		tempList.add(getCourseByTitle("ENGEC402"));
		//tempList.add(getCourseByTitle("Social Science/Humanities Elective");
		tempSemesters.put("3s", tempList);		
		tempList.clear();
		
		//Senior Semester 1
		//tempList.add(getCourseByTitle("Engineering Elective"));
		//tempList.add(getCourseByTitle("Professional Elective"));
		tempList.add(getCourseByTitle("ENGBE467"));
		tempList.add(getCourseByTitle("ENGBE465"));
		//tempList.add(getCourseByTitle("Computer Elective")); //ENGEC327, ENGEC413, or ENGEC441
		//tempList.add(getCourseByTitle("General Education Elective"));
		tempSemesters.put("4f", tempList);
		tempList.clear();
		
		//Senior Semester 2
		//tempList.add(getCourseByTitle("Biomedical Elective"));
		//tempList.add(getCourseByTitle("Biomedical Elective"));
		//tempList.add(getCourseByTitle("Professional Elective"));
		tempList.add(getCourseByTitle("ENGEC464"));
		tempSemesters.put("4s", tempList);
		tempList.clear();
		
		
		/*
		Professional Electives Defined as:
		All BE, EC, EK, and ME 300 level or above courses
		CAS CH 203, CAS CH 204 and all CAS CH 300, 400 and 500 level courses (except: CAS CH 391, 392, 401, 402, 491, 492). 
		All CAS PY 300 and 400 level courses (except PY 371, 401, 402, 482, 491, 492). 
		All CAS MA 300, 400, and 500 level courses (except CAS MA 381, 401, 402). 
		All CAS BI 300, 400 and 500 level courses (except BI 315, 371, 372, 391, 392) 
		ENG BF 527 Applications in Bioinformatics SAR HS 360 Muscle Biology in Health & Disease CAS CH 629 Ð DNA Nanotechnology 
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
		return null;
	}
	
	HashMap <String, ArrayList<Course>> MEDefault() {
		HashMap <String, ArrayList<Course>> tempSemesters = new HashMap<String, ArrayList<Course>> ();
		
		ArrayList<Course> tempList = new ArrayList<Course>();
		
		//Freshman Semester 1
		tempList.add(getCourseByTitle("CASMA123"));
		tempList.add(getCourseByTitle("CASCH131"));
		tempList.add(getCourseByTitle("ENGEK100"));
		tempList.add(getCourseByTitle("ENGEK127")); //Should this be given as a choice with EK128?
		tempList.add(getCourseByTitle("CASWR100"));
		tempSemesters.put("1f", tempList);
		tempList.clear();
		
		//Freshman Semester 2
		tempList.add(getCourseByTitle("CASMA124"));
		tempList.add(getCourseByTitle("CASPY211"));
		tempList.add(getCourseByTitle("ENGEK131"));
		tempList.add(getCourseByTitle("ENGEK132"));
		tempList.add(getCourseByTitle("CASWR150"));
		tempSemesters.put("1s", tempList);
		tempList.clear();
		
		//Sophomore Semester 1
		tempList.add(getCourseByTitle("CASMA225"));
		tempList.add(getCourseByTitle("CASPY212"));
		tempList.add(getCourseByTitle("ENGEK301"));
		tempList.add(getCourseByTitle("ENGEK156"));
		//tempList.add(getCourseByTitle("Social Science Elective")); 
		tempSemesters.put("2f", tempList);
		tempList.clear();
		
		//Sophomore Semester 2
		tempList.add(getCourseByTitle("CASMA226"));
		//tempList.add(getCourseByTitle("Natural Science Elective"));
		tempList.add(getCourseByTitle("ENGEK307"));
		tempList.add(getCourseByTitle("ENGEK102")); //Or CASMA142
		//tempList.add(getCourseByTitle("Humanities Elective"));
		tempSemesters.put("2s", tempList);
		tempList.clear();
		
		//Junior Semester 1
		tempList.add(getCourseByTitle("ENGME359"));
		tempList.add(getCourseByTitle("ENGME305"));
		tempList.add(getCourseByTitle("ENGME304"));
		tempList.add(getCourseByTitle("ENGME303"));
		//tempList.add(getCourseByTitle("Social Science OR Humanities Elective"));
		tempSemesters.put("3f", tempList);
		tempList.clear();
		
		//Junior Semester 2
		tempList.add(getCourseByTitle("ENGME360"));
		tempList.add(getCourseByTitle("ENGME302"));
		tempList.add(getCourseByTitle("ENGME306"));
		tempList.add(getCourseByTitle("ENGME419"));
		tempList.add(getCourseByTitle("ENGME366"));
		tempSemesters.put("3s", tempList);
		tempList.clear();
		
		//Senior Semester 1
		tempList.add(getCourseByTitle("ENGME460"));
		//tempList.add(getCourseByTitle("Advanced Elective"));
		//tempList.add(getCourseByTitle("Advanced Elective"));
		tempList.add(getCourseByTitle("ENGME410"));
		tempSemesters.put("4f", tempList);
		tempList.clear();
		
		//Senior Semester 2
		tempList.add(getCourseByTitle("ENGME461"));
		//tempList.add(getCourseByTitle("Advanced Elective"));
		//tempList.add(getCourseByTitle("Advanced Elective"));
		//tempList.add(getCourseByTitle("General Education Elective"));

		tempSemesters.put("4s", tempList);
		tempList.clear();
		
		/* Advanced Elective Defined as:
		  	All ENG courses 300 level or above, (without overlap)
			Additional Pre-approved:
				CAS AS 414 Ð Solar and Space Physics 
				SMG SI 480 Ð The Business of Technology Innovation. 
			Other 300-level or above Mathematics and Natural Science courses may be acceptable by petition
		 */
		
		/*
		 * The Natural Science Elective:
			ENG BE 209 Ð Principles of Molecular Cell Biology & Biotechnology 
			Astronomy (AS) - 200-level or higher course or any 100-level course that includes a lab 
			Biology (BI) - Any 200-level or higher course or any 100-level course that includes a lab 
			Neuroscience (NE) - All 
			Chemistry (CH) - Any 200-level or higher course 
			Physics (PY) - Any 300-level or higher course, CAS PY 231- The Physics in Music 
			Earth Science (ES) - Any 300-level or higher course. Also the following: 
				CAS ES 101 Ð Dynamic Earth CAS ES 142 Ð Intro Beach & Shoreline Processes 
 				CAS ES 105 Ð Environmental Earth Sciences CAS ES 144 - Oceanography 
 				CAS ES 140 Ð Earthquakes, Volcanoes, Natural Disasters CAS ES 222 - Mineralogy 
			The following GE courses: 
 				CAS GE 101 Ð Natural Environ: Atmosphere CAS GE 375 Ð Intro Quant Environmental Modeling 
 				CAS GE 104 Ð Natural Environ: Physical Landscape CAS GE 440 Ð Digital Image Proc & Remote Sensing 
 				CAS GE 110 Ð Our Changing Planet CAS GE 445 Ð Physical Models in Remote Sensing 
 				CAS GE 302 Ð Remote Sensing of the Environment CAS GE 448 Ð Remote Sensing of Vegetation 
 				CAS GE 307 Ð Biogeography CAS GE 456 Ð Terrestrial Ecosystems & Carbon Cycle 
 				CAS GE 310 Ð Climate & the Environment CAS GE 483 Ð Geodynamics II: Fluids & Fluid Transport 
 				CAS GE 365 Ð Intro Geographic Information Systems 

		 */
	
		return null;
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

	public ArrayList<Course> getClassWithYear(int year, char semester) {
	char yearChar = Character.forDigit(year, 10); 
	StringBuilder semesterChoice = new StringBuilder(2).append(yearChar).append(semester);
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
	
	//Nate commented because i think i broke it
/*
	@Override
	public void printCourseArray(ArrayList<Course> list) {
		// TODO Auto-generated method stub
		
	}

	@Override

	public boolean addClassWithYear(int year, char semester, int position) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override

	public Course removeClassWithYear(int year, char semester, Course c)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
*/
	
}

