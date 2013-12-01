package com.example.eng_schedule_planner.scheduleActivity;

import com.example.eng_schedule_planner.R;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
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

	LinearLayout listLayout;
	private void setUpGrid()
	{
		listLayout = (LinearLayout) findViewById(R.id.schedule_vertical_layout);
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


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		super.onActivityResult(requestCode, resultCode, data);
		
		
		String className = data.getStringExtra("className");
		for(int i = 0; i < listLayout.getChildCount(); i++) {
	        if(listLayout.getChildAt(i) instanceof YearView){
	        	YearView yv = (YearView)listLayout.getChildAt(i);
	        	if(yv.addClassClicked == true)
	        	{
	        		if(resultCode == Activity.RESULT_OK)
	        		   yv.addNewClassWithName(className);
	        		else if (resultCode == Activity.RESULT_CANCELED);
	        		yv.addClassClicked = false;
	        		
	        	}
	        }
	        	
	       		
	    }
		
		
		
		
	}
}
