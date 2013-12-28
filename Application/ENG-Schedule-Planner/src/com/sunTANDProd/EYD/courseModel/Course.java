package com.sunTANDProd.EYD.courseModel;

import java.util.ArrayList;

public class Course extends GenericCourse {
	
	public final static int CATEGORY_ENG_CORE = 0;
	public final static int CATEGORY_NAT_SCI = 1;
	public final static int CATEGORY_MATH = 2;
	public final static int CATEGORY_GEN_ED = 3;
	//Ruthie would like all of these to be the same color to be consistent with the course planning sheet
	//Maybe we do something where the required courses are all blue, but different shades?
	//I'm just concerned about how we'd differentiate colors...
	//Do we had a functionality where the user can change the color for electives/non-required?
	//Do we need to mark instances of courses as required in order to indicate that it shouldn't be deleted?
	public final static int CATEGORY_ME_REQ = 4;
	public final static int CATEGORY_ECE_REQ = 5;
	public final static int CATEGORY_BME_REQ = 6;
	
	
	public final static int CATEGORY_DEFAULT = 7;
	public final static int CATEGORY_GENERIC = 8;
	
	//Maybe we should add another color for the Electives while they are a generic course and not an actual course yet

	
	public Course(String name, String school, String dept, String cid,
			String description, ArrayList<String> prereqs, int credits) {
		super();
		this.name = name;
		this.school = school;
		this.dept = dept;
		this.cid = cid;
		this.description = description;
		this.prereqs = prereqs;
		this.credits = credits;
		this.user = true;
		this.completed = false;
	}
	
	public Course (Course c) {
		this.name = c.name;
		this.school = c.school;
		this.dept = c.dept;
		this.cid = c.cid;
		this.description = c.description;
		this.prereqs = c.prereqs;
		this.credits = c.credits;
		this.user = c.user;
		this.completed = c.completed;
	}
	 
	public Course() {}

	String name;
	String school;
	String dept;
	String cid;
	String description;
	int credits;
	ArrayList<String> prereqs;
	boolean user;
	public boolean completed;
	
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
	
	public int getCategory()
	{
		if (school.equals("CAS")){
				if (dept.equals("MA")){
					return CATEGORY_MATH;
				}
				else if (dept.equals("PY") || dept.equals("BI") || dept.equals("CH")){
					return CATEGORY_NAT_SCI;
				}
				else{
					return CATEGORY_GEN_ED;
				}
		}
		else if (school.equals("ENG")){
					if (dept.equals("EK")){
						return CATEGORY_ENG_CORE;
					}
					else if (dept.equals("EC")){
						return CATEGORY_ECE_REQ;
					}
					else if (dept.equals("BE")){
						return CATEGORY_BME_REQ;
					}
					else if (dept.equals("ME")){
						return CATEGORY_ME_REQ;
					}
					else{
						return CATEGORY_DEFAULT;
					}
		}
		else{
			return CATEGORY_DEFAULT;
		}
	}
	public String getDescription()
	{
		return description;
	}
	public ArrayList<String> getPrereqs(){
		return prereqs;
	}
		
}

