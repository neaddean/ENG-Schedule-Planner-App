package com.example.eng_schedule_planner.menus;


import java.util.concurrent.Semaphore;

import com.example.eng_schedule_planner.R;
import com.example.eng_schedule_planner.scheduleActivity.ScheduleActivity;

import courseModel.Course;
import courseModel.CourseModel;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class mainMenuActivity extends Activity {
	
		
		Button newPlanButton;
		Button loadPlanButton;
		Button aboutButton;
		AlertDialog.Builder alertDialog;
	
		@Override
		public void onCreate(Bundle savedInstanceState){
			super.onCreate(savedInstanceState);
			setContentView(R.layout.main_menu);
			aboutButton = (Button) findViewById(R.id.about_button);
			newPlanButton = (Button) findViewById(R.id.newplan_button);
			loadPlanButton = (Button) findViewById(R.id.loadplan_button);
			
			aboutButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent goToAboutActivity = new Intent(arg0.getContext(), aboutActivity.class);
					startActivity(goToAboutActivity);				
				}			
			});
			
			newPlanButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					//Intent goToScheduleActivity = new Intent(arg0.getContext(), ScheduleActivity.class);
					//startActivity(goToScheduleActivity);
					clickNewPlanButton();					
				}
			});
			
			loadPlanButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0){
					//TODO: load previous plan
					Intent goToScheduleActivity = new Intent(arg0.getContext(), ScheduleActivity.class);
					startActivity(goToScheduleActivity);
				}
			});
		}
		
		public void clickNewPlanButton(){
			alertDialog = new AlertDialog.Builder(this);
			alertDialog.setTitle("Choose a Plan Type");
			CharSequence options[] = new CharSequence[] {"Biomedical Engineering", "Mechanical Engineering", "Electrical Engineering", "Computer Engineering", "Blank Schedule"};
			alertDialog.setItems(options, new DialogInterface.OnClickListener() {
				@Override 
				public void onClick(DialogInterface dialog, int which) {
					alertDialog = null;
					switch(which){
					case 0:	{
						//load BME
						//CourseModel.getInstance().
					}
					case 1: {
						//load ME
					}
					case 2: {
						//load EE
					}
					case 3: {
						//load CE
					}
					case 4:{
						//blank schedule
					}
					}
					}
			});
			alertDialog.setOnCancelListener(new OnCancelListener() {
				@Override
				public void onCancel(DialogInterface dialog) {
					alertDialog = null;
				}
			});
			alertDialog.show();	
		
		}
};