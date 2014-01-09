package com.sunTANDProd.EYD.courseModel;

public abstract class GenericCourse {

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
	
	
	
	//Data Fields

		String name; //Full name that appears for Course
		String description; //Text that appears in Description Activity
		double credits; //Number of credits
		int category; //For the color
		boolean editCategory; //If the user can edit Category or if preset
		//boolean user; //Purpose unknown
		public boolean completed; //For checkmark

	//Constructors
		
		public GenericCourse(GenericCourse gc)
		{
			this.name = gc.name;
			this.description = gc.description;
			this.credits = gc.credits;
			this.category = gc.category;
			this.editCategory = gc.editCategory;
			//this.user = gc.user;
			this.completed = gc.completed;
		}
		
		//Do we need a constructor with arguments for the abstract course?
		
		public GenericCourse() {}
		
		public String getTitle()
		{
			return null;
		}
		
		public String getIconTitle()
		{
			return null;
		}
		
		public String getFullTitle()
		{
			return null;
		}
		
		public int getCategory()
		{
			return category;
		}
		
		public void setCategory(int cat)
		{
			if (editCategory)
			{
				//Should probably check to make sure it's a valid category int
				this.category = cat;
			}
		}
		
		public String getDescription()
		{
			return description;
		}

	
}

