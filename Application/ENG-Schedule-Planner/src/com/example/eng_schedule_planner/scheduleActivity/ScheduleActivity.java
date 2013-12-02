package com.example.eng_schedule_planner.scheduleActivity;

import java.util.ArrayList;

import com.example.eng_schedule_planner.R;

import courseModel.Course;
import courseModel.CourseModel;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;


import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.LinearLayout.LayoutParams;


public class ScheduleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		CourseModel model = CourseModel.getInstance();
		model.loadCourseFile(this); 
		
		ArrayList<Course> c = model.getClassWithYear(CourseModel.FRESHMAN_YEAR, CourseModel.FALL);
		for(Course co : c)
		{
			System.out.println(co.getTitle());
		}
		
		setUpGrid();
	}

	LinearLayout listLayout;
	private void setUpGrid()
	{
		listLayout = (LinearLayout) findViewById(R.id.schedule_vertical_layout);
		listLayout.addView(new YearView(this,"Freshman Fall"));
		listLayout.addView(new YearView(this,"Freshman Spring"));
		listLayout.addView(new YearView(this,"Sophok omore Fall"));
		listLayout.addView(new YearView(this,"Sophomore Spring"));
		listLayout.addView(new YearView(this,"Junior Fall"));
		listLayout.addView(new YearView(this,"Junior Spring"));
		listLayout.addView(new YearView(this,"Senior Fall"));
		listLayout.addView(new YearView(this,"Senior Spring"));
		LayoutParams spaceParam = new LayoutParams(LayoutParams.WRAP_CONTENT,50);
		Space afterSpace= new Space(this);
		afterSpace.setLayoutParams(spaceParam);
		listLayout.addView(afterSpace);
		
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		super.onActivityResult(requestCode, resultCode, data);
		
		for(int i = 0; i < listLayout.getChildCount(); i++) {
	        if(listLayout.getChildAt(i) instanceof YearView){
	        	YearView yv = (YearView)listLayout.getChildAt(i);
	        	if(yv.addClassClicked == true)
	        	{
	        		
	        		if(resultCode == Activity.RESULT_OK)
	        		{
	        		String className = data.getStringExtra("className");
	        		   yv.addNewClassWithName(className);
	        		}
	        		else if (resultCode == Activity.RESULT_CANCELED);
	        		yv.addClassClicked = false;
	        		
	        	}
	        }
	        	
	       		
	    }
		
		
		
		
	}
}
