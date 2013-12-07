package com.example.eng_schedule_planner.scheduleActivity;

import java.util.ArrayList;

import com.example.eng_schedule_planner.R;
import com.example.eng_schedule_planner.Global.Global;

import courseModel.Course;
import courseModel.CourseModel;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;


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
		
		//getWindow().getDecorView().setBackgroundColor(0xFFFFDDB7);
		getWindow().setBackgroundDrawableResource(R.drawable.background_texture);
		
		setUpGrid();
	}

	LinearLayout listLayout;
	private void setUpGrid()
	{
		listLayout = (LinearLayout) findViewById(R.id.schedule_vertical_layout);
		listLayout.addView(new YearView(this,"Freshman Fall",CourseModel.FRESHMAN_YEAR,CourseModel.FALL));
		listLayout.addView(new YearView(this,"Freshman Spring",CourseModel.FRESHMAN_YEAR,CourseModel.SPRING));
		listLayout.addView(new YearView(this,"Sophomore Fall",CourseModel.SOPHOMORE_YEAR,CourseModel.FALL));
		listLayout.addView(new YearView(this,"Sophomore Spring",CourseModel.SOPHOMORE_YEAR,CourseModel.SPRING));
		listLayout.addView(new YearView(this,"Junior Fall",CourseModel.JUNIOR_YEAR,CourseModel.FALL));
		listLayout.addView(new YearView(this,"Junior Spring",CourseModel.JUNIOR_YEAR,CourseModel.SPRING));
		listLayout.addView(new YearView(this,"Senior Fall",CourseModel.SENIOR_YEAR,CourseModel.FALL));
		listLayout.addView(new YearView(this,"Senior Spring",CourseModel.SENIOR_YEAR,CourseModel.SPRING));
		LayoutParams spaceParam = new LayoutParams(LayoutParams.WRAP_CONTENT,50);
		Space afterSpace= new Space(this);
		afterSpace.setLayoutParams(spaceParam);
		listLayout.addView(afterSpace);
		
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		super.onActivityResult(requestCode, resultCode, data);
		YearView yToAdd = Global.YearToAddClass;
		Global.YearToAddClass = null;
		if(resultCode == Activity.RESULT_OK)
		{
			if(yToAdd != null)
			{
				String className = data.getStringExtra("className");
				yToAdd.addNewClassWithName(className);
				
			}else
				System.out.println("Error: Schedul Acitvity: No year to add");
		}
		  				
		
		
	}
}
