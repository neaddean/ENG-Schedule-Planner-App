package com.sunTANDProd.EYD.courseModel;

import java.util.ArrayList;

public class OfficialCourse extends GenericCourse {

	String school; //Three character College Code
	String dept; //Two character Department code
	String cid; //course ID number; 3 digits
	ArrayList <String> prereqs; //Prerequisites for the OfficialCourse
	
	public OfficialCourse(GenericCourse gc) {
		super(gc);

	}

	public OfficialCourse(OfficialCourse oc) {
		
		this.school = oc.school;
		this.dept = oc.dept;
		this.cid = oc.cid;
		this.prereqs = oc.prereqs;
		
		this.name = oc.name;
		this.title = oc.title;
		this.iconTitle = oc.iconTitle;
		this.fullTitle = oc.fullTitle;
		
		this.description = oc.description;
		this.credits = oc.credits;
		this.category = oc.category;
		this.editCategory = false; //Official courses cannot have category changed by user
		this.user = oc.user;
		this.completed = oc.completed;
	}
	
	//Will be used with the XML to create all of the course objects
	public OfficialCourse(String name, String school, String dept, String cid, String description, 
			ArrayList<String> prereqs, double credits)
	{
		this.name = name;
		this.school = school;
		this.dept = dept;
		this.cid = cid;
		
		//Using name, school, dept, and cid, creates title, iconTitle, and fullTitle fields
		setTitle();
		setIconTitle();
		setFullTitle();
		
		this.description = description;
		this.prereqs = prereqs;
		this.credits = credits;
		
		this.editCategory = false;
		setCategory();
		this.user = true;
		this.completed = false;
		
	}

	public OfficialCourse() {

	}


public void setCategory(int cat)
{
}

public void setCategory()
{
	if (school.equals("CAS")){
		if (dept.equals("MA")){
			this.category = CATEGORY_MATH;
		}
		else if (dept.equals("PY") || dept.equals("BI") || dept.equals("CH")){
			this.category =CATEGORY_NAT_SCI;
		}
		else{
			this.category =CATEGORY_GEN_ED;
		}
	}
	else if (school.equals("ENG")){
			if (dept.equals("EK")){
				this.category =CATEGORY_ENG_CORE;
			}
			//We may need to change these to more similar colors...
			//Or the same color
			else if (dept.equals("EC")){
				this.category =CATEGORY_ECE_REQ;
			}
			else if (dept.equals("BE")){
				this.category =CATEGORY_BME_REQ;
			}
			else if (dept.equals("ME")){
				this.category =CATEGORY_ME_REQ;
			}
			else{
				this.category = CATEGORY_DEFAULT;
			}
	}
	else{
		this.category = CATEGORY_DEFAULT;
	}
}

public void setTitle()
{
	this.title = school+dept+cid;
}

public void setIconTitle()
{
	this.iconTitle = school+"\n"+dept+cid;
}

public void setFullTitle()
{
	this.fullTitle = school + " " + dept + cid + ": " + name;
}

	
	
}
