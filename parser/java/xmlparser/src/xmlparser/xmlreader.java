package xmlparser;

import xmlparser.CourseModel;

public class xmlreader {
 
  public static void main(String argv[]) {
 
    CourseModel model = new CourseModel("courses.xml");
//  model.printStuff();	  
	model.printLists();  
  }
 
}