package courseModel;

import java.util.ArrayList;

public interface ModelAccessor{

	public ArrayList<Course> getClassWithYear(int year, char semester);
	public void printCourseArray (ArrayList<Course> list);
	
	//position of zero-indexed.
	public void addClassWithYear(Course course, int year, char semester, int position);
	
	//Return the course that is removed
	//Throw exception if can't removed 
	public Course removeClassWithYear(Course c, int year, char semester);

	public Course getCourseByTitle(String Title);
	
	public ArrayList<String> getCourseTitles();
}
