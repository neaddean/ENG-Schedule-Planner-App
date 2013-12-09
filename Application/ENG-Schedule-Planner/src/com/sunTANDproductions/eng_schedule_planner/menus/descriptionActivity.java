package com.sunTANDproductions.eng_schedule_planner.menus;

import com.sunTANDproductions.eng_schedule_planner.R;
import com.sunTANDproductions.eng_schedule_planner.Global.Global;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class descriptionActivity extends Activity{
	
	TextView descriptionTitle;
	TextView descriptionText;
	TextView descriptionPrereq;
	Button backButton;
	
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.description_activity);
		descriptionTitle = (TextView) findViewById(R.id.description_title);
		descriptionText = (TextView) findViewById(R.id.description_text);
		descriptionPrereq = (TextView) findViewById(R.id.description_prereq);
		backButton = (Button) findViewById(R.id.back_button);
		descriptionTitle.setText(Global.courseDescriptionTitle);
		String prereqs = new String();
		prereqs = prereqs.concat("Prerequisites: ");
		if(Global.courseDescriptionPrereqs != null){
    		for(int i = 0; i < Global.courseDescriptionPrereqs.size(); i++){
    			prereqs = prereqs.concat(Global.courseDescriptionPrereqs.get(i));
    			if (i+1 < Global.courseDescriptionPrereqs.size()){
    				prereqs = prereqs.concat(", ");
    			}
	    	}
    		if (prereqs.contentEquals("Prerequisites: ")){
    			prereqs = prereqs.concat("None for this course");
    		}
		}
		descriptionPrereq.setText(prereqs);
		descriptionText.setText(Global.courseDescriptionText);
		backButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//go back to the scheduleActivity
				finish();
			}
		});
	}
}
