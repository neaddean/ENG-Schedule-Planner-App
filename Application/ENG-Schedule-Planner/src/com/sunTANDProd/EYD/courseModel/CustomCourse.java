package com.sunTANDProd.EYD.courseModel;

public class CustomCourse extends GenericCourse {

	/*
	 * From GenericCourse:
	 * 	String name; //Full name that appears for Course
		String title; //To use when finding course
		String iconTitle; //With Spaces to appear on icons
		String fullTitle; //As appears with course code and name in other versions
		String description; //Text that appears in Description Activity
		double credits; //Number of credits
		int category; //For the color
		boolean editCategory; //If the user can edit Category or if preset
		boolean user; //Purpose unknown
		public boolean completed; //For checkmark
	 */
	//Additional Data Fields
	String school; //Three character College Code
	String dept; //Two character Department code
	String cid; //course ID number; 3 digits
	
	
	//Inherited from GenericCourse
	public CustomCourse(GenericCourse gc) {
		super(gc);
		// TODO Auto-generated constructor stub
	}

	//Inherited from GenericCourse
	public CustomCourse() {
		// TODO Auto-generated constructor stub
	}

	public CustomCourse(CustomCourse cc)
	{
		this.name = cc.name;
		this.school = cc.school;
		this.dept = cc.dept;
		this.cid = cc.cid;
		
		
		this.description = cc.description;
		this.credits = cc.credits;
		
		this.category = cc.category;
		this.editCategory = cc.editCategory;
		//this.user = cc.user;
		this.completed = cc.completed;
	}
	
	public CustomCourse(String name, String school, String dept, String cid, String description, double credits)
	{
		this.name = name;
		this.school = school;
		this.dept = dept;
		this.cid = cid;
		
		this.description = description;
		this.credits = credits;
		
		setCategory();
		this.editCategory = true;
		//this.user = true;
		this.completed = false;
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
	
	//The user is able to make changes here
	//Needs to include error checking to make sure it is a reasonable change
	//Otherwise will need to give the user a warning
	public void setCategory(int cat)
	{
		this.category = cat;
	}

	//Sets category according to the rules of categories
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
	
	
}
