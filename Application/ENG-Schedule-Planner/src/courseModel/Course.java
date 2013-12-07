package courseModel;

import java.util.ArrayList;

public class Course {
	
	public Course(String name, String school, String dept, String cid,
			String description, ArrayList<String> prereqs) {
		super();
		this.name = name;
		this.school = school;
		this.dept = dept;
		this.cid = cid;
		this.description = description;
		this.prereqs = prereqs;
	}
	
	public Course (Course c) {
		this.name = c.name;
		this.school = c.school;
		this.dept = c.dept;
		this.cid = c.cid;
		this.description = c.description;
		this.prereqs = c.prereqs;
	}
	
	public Course() {}

	String name;
	String school;
	String dept;
	String cid;
	String description;
	ArrayList<String> prereqs;
	
	public String getTitle() {
		return school+" "+ dept+" " + cid;
	}
	
	String getFullTitle() {
		return school + " " + dept + " " + cid + ": " + name;
	}

}
