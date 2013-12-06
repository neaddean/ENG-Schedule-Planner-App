package courseModel;

import java.util.ArrayList;

public interface ModelAccessor{

	public ArrayList<Course> getClassWithYear(int year, char semester);
	public void printCourseArray (ArrayList<Course> list);
	
	//position of zero-indexed.
	public boolean addClassWithYear(Course course, int year, char semester, int position);
	
	//Return the course that is removed
	//Throw exception if can't removed 
	public Course removeClassWithYear(int year, char semester, Course c) throws Exception;

}
