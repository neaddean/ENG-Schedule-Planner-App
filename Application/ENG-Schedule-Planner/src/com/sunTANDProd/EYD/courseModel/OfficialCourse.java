package com.sunTANDProd.EYD.courseModel;

import java.util.ArrayList;

public class OfficialCourse extends GenericCourse {

	String school; //Three character College Code
	String dept; //Two character Department code
	String cid; //course ID number; 3 digits
	ArrayList <String> prereqs; //Prerequisites for the OfficialCourse
	boolean isCustom; //Rather than have separate CustomCourse Class, to distinguish
	
	public OfficialCourse(GenericCourse gc) {
		super(gc);

	}

	public OfficialCourse(OfficialCourse oc) {
		
		this.school = oc.school;
		this.dept = oc.dept;
		this.cid = oc.cid;
		this.prereqs = oc.prereqs;
		this.name = oc.name;

		this.description = oc.description;
		this.credits = oc.credits;
		this.category = oc.category;
		this.editCategory = false; //Official courses cannot have category changed by user
		this.user = oc.user;
		this.completed = oc.completed;
		this.isCustom = isCustom;
	}
	
	//Will be used with the XML to create all of the course objects
	public OfficialCourse(String name, String school, String dept, String cid, String description, 
			ArrayList<String> prereqs, double credits, boolean isCustom)
	{
		this.name = name;
		this.school = school;
		this.dept = dept;
		this.cid = cid;
		
		this.description = description;
		this.prereqs = prereqs;
		this.credits = credits;
		
		this.editCategory = false;
		setCategory();
		this.user = true;
		this.completed = false;
		this.isCustom = isCustom;	
	}

	public OfficialCourse() {

	}

//With current system, possible
public void setCategory(int cat)
{
	if (editCategory)
	{
		//Should probably check for valid int values
		//1 = NAT_SCI
		//3 = GEN_ED
		//4 = ME
		//5 = ECE
		//6 = BME
		//8 = Engineering Elective
		this.category = cat;
	}
	else 
	{
		//AN ERROR OF SORTS BECAUSE YOU CANNOT CHANGE THIS CATEGORY
	}
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

public String getTitle()
{
	return school+dept+cid;
}

public String getIconTitle()
{
	return school+"\n"+dept+cid;
}

public String getFullTitle()
{
	return school + " " + dept + cid + ": " + name;
}

	
	
}
