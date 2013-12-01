package com.example.eng_schedule_planner.scheduleActivity;

import com.example.eng_schedule_planner.R;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.Window;

import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.LinearLayout.LayoutParams;


public class ScheduleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setUpGrid();
	}

	
	private void setUpGrid()
	{
		LinearLayout listLayout = (LinearLayout) findViewById(R.id.schedule_vertical_layout);
		listLayout.addView(new YearView(this,"Freshman Fall"));
		listLayout.addView(new YearView(this,"Freshman Spring"));
		listLayout.addView(new YearView(this,"Sophomore Fall"));
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
}
