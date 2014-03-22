package com.sunTANDProd.EYD.courseModel;

import java.util.ArrayList;

public class GenericCourse {

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
	//Ideally this would be the pale purple color that appears on the academic planning sheets
	
	//I'm demonstrating the benefit of a commit!!!! :)
	
	
	//Data Fields

		public String name; //Full name that appears for Course
		String description; //Text that appears in Description Activity
		double credits; //Number of credits
		int category; //For the color
		boolean canEditCategory; //If the user can edit Category or if preset
		//boolean user; //Purpose unknown
		public boolean isCompleted; //For checkmark
		String school; //Three character College Code
		String dept; //Two character Department code
		String cid; //course ID number; 3 digits
		ArrayList <String> prereqs; //Prerequisites for the OfficialCourse
		boolean isCustom; //Rather than have separate CustomCourse Class, to distinguish
		boolean isHolder; //NRM: we now have 1 class... this bool will determine how we handle special holder courses such as tech electives

	//Constructors
		public GenericCourse(String name, String school, String dept, String cid, String description, 
				ArrayList<String> prereqs, double credits)
		{
			this.name = name;
			this.school = school;
			this.dept = dept;
			this.cid = cid;
			
			this.description = description;
			this.prereqs = prereqs;
			this.credits = credits;
			
			setCategory();
			this.canEditCategory = false;
			this.isCompleted = false;
			this.isCustom = true;
		}
		public GenericCourse(GenericCourse gc)
		{
			this.name = gc.name;
			this.description = gc.description;
			this.credits = gc.credits;
			this.category = gc.category;
			this.canEditCategory = gc.canEditCategory;
			//this.user = gc.user;
			this.isCompleted = gc.isCompleted;
			
			this.school = gc.school;
			this.dept = gc.dept;
			this.cid = gc.cid;
			this.prereqs = gc.prereqs;
			this.canEditCategory = false; //Official courses cannot have category changed by user
			this.isCompleted = gc.isCompleted;
			this.isCustom = gc.isCustom;
		}
		
		
		public GenericCourse() {}
		
		//Accessors for various Title formats

		//Used to find a course to add to the HashMap in CourseModel
		public String getTitle()
		{
			if(!isHolder){
				return school+dept+cid;
			}
			else{
				return name;
			}
			
		}

		//As appears on the icons
		public String getIconTitle()
		{
			if(!isHolder){
				return school+"\n"+dept+cid;
			}
			else{
				return name;
			}
			
		}

		//As appears in menus and in the search
		public String getFullTitle()
		{
			if(!isHolder){
				return school + " " + dept + cid + ": " + name;
			}
			else{
				return name;
			}

		}
		
		//With current system, possible
		public void setCategory(int cat)
		{
			if (canEditCategory)
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
				this.category = cat;  //NRM: for now... not really sure it's necessary
				//AN ERROR OF SORTS BECAUSE YOU CANNOT CHANGE THIS CATEGORY
			}
		}
		
		public int getCategory()
		{
			return category;
		}
		
		
		public String getDescription()
		{
			return description;
		}

		public ArrayList<String> getPrereqs() {
			// TODO Auto-generated method stub
			return null;
		}
		//Following the scheme provided in the Planning Sheet to color the icons 
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

