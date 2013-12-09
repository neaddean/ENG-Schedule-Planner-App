package com.sunTANDproductions.eng_schedule_planner.Global;

import java.util.ArrayList;

import android.content.Context;

import com.sunTANDproductions.eng_schedule_planner.courseModel.Course;
import com.sunTANDproductions.eng_schedule_planner.scheduleActivity.YearView;


public class Global {
	//For storing any global variable
	//Everything should be static
	 
	public static YearView YearToAddClass;
	public static Course courseToAdd;
	
	public static String courseDescriptionTitle;
	public static String courseDescriptionText;
	public static ArrayList<String> courseDescriptionPrereqs;
	
	public static Context myContext;
	
}
