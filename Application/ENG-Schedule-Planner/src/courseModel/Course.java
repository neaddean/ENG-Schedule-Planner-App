package courseModel;

import java.util.ArrayList;

public class Course {
	String name;
	String school;
	String dept;
	String cid;
	String description;
	ArrayList<Course> prereqs;
	
	String getTitle() {
		return school + dept + cid;
	}
	
	String getFullTitle() {
		return school + " " + dept + " " + cid + ": " + name;
	}

}
