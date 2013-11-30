package xmlparser;
 
//import javax.xml.parsers.DocumentBuilderFactory;

import xmlparser.Courses;

//import javax.xml.parsers.DocumentBuilder;
//
//import org.w3c.dom.Document;
//import org.w3c.dom.NodeList;
//import org.w3c.dom.Node;
//import org.w3c.dom.Element;
//import org.xml.sax.InputSource;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.Reader;
//import java.util.ArrayList;
 

public class xmlreader {
 
  public static void main(String argv[]) {
 
    Courses model = new Courses("courses.xml");
    model.printStuff();	  
	  
  }
 
}