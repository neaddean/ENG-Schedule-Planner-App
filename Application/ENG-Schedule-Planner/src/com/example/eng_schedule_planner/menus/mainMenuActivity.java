package com.example.eng_schedule_planner.menus;


import com.example.eng_schedule_planner.R;
import com.example.eng_schedule_planner.scheduleActivity.ScheduleActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class mainMenuActivity extends Activity {
	
		
		Button newPlanButton;
		Button loadPlanButton;
		Button aboutButton;
	
		@Override
		public void onCreate(Bundle savedInstanceState){
			super.onCreate(savedInstanceState);
			setContentView(R.layout.main_menu);
			aboutButton = (Button) findViewById(R.id.about_button);
			newPlanButton = (Button) findViewById(R.id.newplan_button);
			
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
					Intent goToScheduleActivity = new Intent(arg0.getContext(), ScheduleActivity.class);
					startActivity(goToScheduleActivity);				
				}			
			});
		}
};