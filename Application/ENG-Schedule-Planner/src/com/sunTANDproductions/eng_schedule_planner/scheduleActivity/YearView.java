package com.sunTANDproductions.eng_schedule_planner.scheduleActivity;


import java.util.ArrayList;



import android.animation.LayoutTransition;
import android.content.Context;
import android.graphics.Color;

import android.view.DragEvent;

import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Space;
import android.widget.TextView;

import com.sunTANDproductions.eng_schedule_planner.courseModel.Course;
import com.sunTANDproductions.eng_schedule_planner.courseModel.CourseModel;
import com.sunTANDproductions.eng_schedule_planner.scheduleActivity.ClassButton;

public class YearView extends LinearLayout{

	final int SPACE_WIDTH = 30;
	//String yearLabel;
	public TextView yearLabel;
	
	ArrayList<ClassButton> classList;
	HorizontalScrollView horizontalScroll;
	LinearLayout horizontalLayout;
	LayoutTransition transition;
	int year;
	char semester;

	
	private YearView(Context context) {
		super(context);
	}
		
	public YearView(final Context context, String yearName, int year, char semester)
	{
		super(context);
		this.year = year;
		this.semester = semester;
		
		//Setting up the layout param for itself
		this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
		this.setOrientation(1);
				
		classList = new ArrayList<ClassButton>();	
		
		//Adding the year label
		yearLabel = new TextView(context);
		yearLabel.setText(yearName);
		yearLabel.setPadding(50, 0, 0, 0);
		yearLabel.setTextSize(20);
		//NATE: change yearLabel color
		yearLabel.setTextColor(Color.BLACK);
		this.addView(yearLabel);
				
		//Add the horizontal scroll layout
		horizontalScroll = new HorizontalScrollView(context);
		horizontalScroll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		horizontalScroll.setHorizontalScrollBarEnabled(false);
		horizontalScroll.setOverScrollMode(ScrollView.OVER_SCROLL_NEVER);

		
		//Linear layout inside horizontal scroll layout
		horizontalLayout = new LinearLayout(context);
		horizontalLayout.setLayoutParams(new LayoutParams(0,LayoutParams.WRAP_CONTENT,1f));
		horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);
		horizontalScroll.addView(horizontalLayout);

		
		//Add space in front and the end
		LayoutParams spaceParam = new LayoutParams(SPACE_WIDTH,LayoutParams.WRAP_CONTENT);
		Space beforeSpace= new Space(context);
		beforeSpace.setLayoutParams(spaceParam);
		
		horizontalLayout.addView(beforeSpace);
		ArrayList<Course> cs= CourseModel.getInstance().getClassWithYear(year, semester);
		System.out.println(cs.size());
		if(cs != null)
		for(int j = 0; j <cs.size(); j++)
		{
			if(cs.get(j) != null)
			{ClassButton myButton = new ClassButton(context,ClassButton.STANDARD_BUTTON, cs.get(j));
			horizontalLayout.addView(myButton);
			classList.add(myButton);}
		}
		
	
		ClassButton addButton = new ClassButton(context, ClassButton.ADD_BUTTON, null);;
		horizontalLayout.addView(addButton);
		
		this.addView(horizontalScroll);
		
		Space afterSpace= new Space(context);
		afterSpace.setLayoutParams(spaceParam);
		horizontalLayout.addView(afterSpace);
		horizontalLayout.setOnDragListener(new ClassDragListener());
		
	}
			
	class ClassDragListener implements OnDragListener
	{
	
		@Override
		public boolean onDrag(View v, DragEvent dragEvent) {
			int action = dragEvent.getAction();
			final ClassButton view = (ClassButton) ((Button)dragEvent.getLocalState()).getParent();
			switch(action)
			{
				case DragEvent.ACTION_DRAG_STARTED:
					//System.out.println("Drag started");
					break;
				case DragEvent.ACTION_DRAG_ENTERED:
					//System.out.println("Drag entered");
			        break;
				case DragEvent.ACTION_DRAG_LOCATION:
					break;
		      case DragEvent.ACTION_DRAG_EXITED:
		    	  //System.out.println("Drag exited");
		        break;
		      case DragEvent.ACTION_DROP:
		          YearView yearView  = (YearView) view.getParent().getParent().getParent();
		          
		          try {
		        	  yearView.removeButton((ClassButton) view);
					} catch (Exception e1) {
						e1.printStackTrace();
					} 
		       
		        float x = dragEvent.getX();
		    	int index = (int)((x - SPACE_WIDTH + ClassButton.WIDTH/2) / ClassButton.WIDTH);
		        YearView currentView = (YearView)v.getParent().getParent();
		        currentView.addButton((ClassButton) view,index);
		        view.setVisibility(View.VISIBLE);
		        view.animate().scaleX(1).alpha(1);
		        YearView.this.printCoursesInStore();
		        break;
		      case DragEvent.ACTION_DRAG_ENDED:
		    	 
		    	  if(!dragEvent.getResult()){
		    		  view.setVisibility(View.VISIBLE);
		    		  view.animate().scaleX(1).alpha(1);
		    	  }
		    	  
		    	  break;
		      default:
		        break;
			}
			
			return true;
		}
		
	
	}
	
	public void removeButton(ClassButton button) throws Exception
	{
		LinearLayout parent = (LinearLayout) button.getParent();
		parent.removeView(button);
		if(!classList.remove(button))
			throw new Exception("Fail to remove button from classlist.");
		CourseModel.getInstance().removeClassWithYear(button.myCourse, year, semester);
	}
	
	public void addButton(ClassButton button, int index)
	{
		index = index < 0 ? 0 : index;
		int childCount = horizontalLayout.getChildCount();
		index = index >= childCount-2? childCount - 3 : index;
		//System.out.println(index);
		horizontalLayout.addView(button,index+1);
		classList.add(index, button);
		CourseModel.getInstance().addClassWithYear(button.myCourse, year, semester, index);
	}

	public void addNewClassWithName(String s)
	{
		//ClassButton myButton = new ClassButton(this.getContext(),ClassButton.STANDARD_BUTTON, s);
		//horizontalLayout.addView(myButton,horizontalLayout.getChildCount()-2);
		//classList.add(myButton);
		System.out.println("NEED TO ADD CLASS WITH STRING");
		//NEED ADD THAT LATER
	}
	
	public void addNewClassWithCourse(Course s)
	{
		ClassButton myButton = new ClassButton(this.getContext(),ClassButton.STANDARD_BUTTON, s);
		horizontalLayout.addView(myButton,horizontalLayout.getChildCount()-2);
		classList.add(myButton);
		CourseModel.getInstance().addClassWithYearToEnd(s, year, semester);
	}


	public void printCoursesInStore()
	{
		System.out.println("List:");
		ArrayList<Course> c = CourseModel.getInstance().getClassWithYear(year, semester);
		for(Course a : c)
		{
			System.out.println(a.getTitle());
		}
		System.out.println("ButtonList:");
		for(ClassButton q : classList){
			System.out.println(q.myCourse.getTitle());
		}
		
	}
}


