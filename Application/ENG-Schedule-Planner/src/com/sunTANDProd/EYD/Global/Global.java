package com.sunTANDProd.EYD.Global;

import java.util.ArrayList;

import android.content.Context;

import com.sunTANDProd.EYD.courseModel.GenericCourse;
import com.sunTANDProd.EYD.scheduleActivity.YearView;


public class Global {
	//For storing any global variable
	//Everything should be static
	 
	public static YearView YearToAddClass;
	public static GenericCourse courseToAdd;
	
	public static String courseDescriptionTitle;
	public static String courseDescriptionText;
	public static ArrayList<String> courseDescriptionPrereqs;
	
	public static Context myContext;
	
}
