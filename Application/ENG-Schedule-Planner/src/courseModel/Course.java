package courseModel;

import java.util.ArrayList;

public class Course {
	
	public final static int CATEGORY_ENG_CORE = 0;
	public final static int CATEGORY_NAT_SCI = 1;
	public final static int CATEGORY_MATH = 2;
	public final static int CATEGORY_GEN_ED = 3;
	public final static int CATEGORY_ME_REQ = 4;
	public final static int CATEGORY_ECE_REQ = 5;
	public final static int CATEGORY_BME_REQ = 6;
	public final static int CATEGORY_DEFAULT = 7;
	public final static int CATEGORY_GENERIC = 8;
	
	public Course(String name, String school, String dept, String cid,
			String description, ArrayList<String> prereqs, String credits) {
		super();
		this.name = name;
		this.school = school;
		this.dept = dept;
		this.cid = cid;
		this.description = description;
		this.prereqs = prereqs;
		this.credits = credits;
	}
	
	public Course (Course c) {
		this.name = c.name;
		this.school = c.school;
		this.dept = c.dept;
		this.cid = c.cid;
		this.description = c.description;
		this.prereqs = c.prereqs;
		this.credits = c.credits;
	}
	 
	public Course() {}

	String name;
	String school;
	String dept;
	String cid;
	String description;
	String credits;
	ArrayList<String> prereqs;
	
	public String getTitle() {
		return school+ dept + cid;
	}
	
	public String getFullTitle() {
		return school + " " + dept + cid + ": " + name;
	}
	
	public String getTitleWithSpace()
	{
		return school+"\n"+dept+cid;
	
	}

}
