package com.example.eng_schedule_planner.addClassActivity;

import com.example.eng_schedule_planner.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class addClassActivity extends Activity {

	
	Button addButton;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_class);
		addButton = (Button) findViewById(R.id.addClassButton);
		addButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = getIntent();
				setResult(Activity.RESULT_OK,i);
				finish();
			}
		});
	}
	
	
}
