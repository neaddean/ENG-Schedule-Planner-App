package com.example.eng_schedule_planner.scheduleActivity;

import com.example.eng_schedule_planner.R;
import com.example.eng_schedule_planner.R.id;
import com.example.eng_schedule_planner.R.layout;
import com.example.eng_schedule_planner.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.LinearLayout.LayoutParams;


public class ScheduleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//NATE:
		setActivityBackgroundColor(0xffffffff);
		setUpGrid();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void setActivityBackgroundColor(int color){
		//NATE: method to change background color, called in onCreate
		View view = this.getWindow().getDecorView();
		view.setBackgroundColor(color);
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
