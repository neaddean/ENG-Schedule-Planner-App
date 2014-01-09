package com.sunTANDProd.EYD.courseModel;

public class HolderCourse extends GenericCourse {

	/*
	DATA FIELDS
	String name; //Full name that appears for Course
	String title; //To use when finding course
	String iconTitle; //With Spaces to appear on icons
	String fullTitle; //As appears with course code and name in other versions
	String description; //Text that appears in Description Activity
	double credits; //Number of credits
	int category; //For the color
	boolean editCategory; //If the user can edit Category or if preset
	//boolean user; //Purpose unknown
	public boolean completed; //For checkmark
	*/
	
	//New Data Fields
	String title;
	String iconTitle;
	
	
	//From Base Class
	public HolderCourse(GenericCourse gc) {
		super(gc);
		// TODO Auto-generated constructor stub
	}

	//From Base Class
	public HolderCourse() {
		// TODO Auto-generated constructor stub
	}


	public HolderCourse(HolderCourse hc)
	{
		this.name = hc.name;
		this.title = hc.title;
		this.iconTitle = hc.iconTitle;
		this.description = hc.description;
		this.credits = hc.credits;
		this.category = hc.category;
		this.editCategory = hc.editCategory;
		//this.user = hc.user;
		this.completed = hc.completed;
		
	}
	
	
	public HolderCourse(String title, String iconTitle, String name, String description, double credits, int category)
	{
		this.title = title;
		this.iconTitle = iconTitle;
		this.name = name;
		this.description = description;
		this.credits = credits;
		
		this.description = description;
		this.credits = credits;
		
		this.category = category;
		this.editCategory = false;
		//this.user = true;
		this.completed = false;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getIconTitle()
	{
		return iconTitle;
	}
	
	
	//The full title will be the same as the name
	public String getFullTitle()
	{
		return name;
	}
}
