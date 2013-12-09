package com.sunTANDproductions.eng_schedule_planner.courseModel;

import java.util.ArrayList;

import android.content.Context;

public interface ModelAccessor{

	public ArrayList<Course> getClassWithYear(int year, char semester);
	public void printCourseArray (ArrayList<Course> list);
	
	//position of zero-indexed.
	public void addClassWithYear(Course course, int year, char semester, int position);
	
	//Return the course that is removed
	//Throw exception if can't removed 
	public Course removeClassWithYear(Course c, int year, char semester) throws Exception;

	public int getIndexWithTitle(String s);
	
	public void saveState(String filename, Context context);
	
}
