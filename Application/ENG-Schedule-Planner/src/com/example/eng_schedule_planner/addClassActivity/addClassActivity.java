package com.example.eng_schedule_planner.addClassActivity;

import com.example.eng_schedule_planner.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class addClassActivity extends Activity {

	TextView title;
	EditText editText;
	Button addButton;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_class);
		title = (TextView) findViewById(R.id.addClassLabel);
		addButton = (Button) findViewById(R.id.addClassButton);
		editText = (EditText) findViewById(R.id.editText1);
		addButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				addClassActivity c = (addClassActivity) v.getContext();
				if(c.isEmpty(c.editText)){
					Toast.makeText(c, "Enter Class Name Please", Toast.LENGTH_SHORT).show();
					return;
				}
				Intent i = new Intent();
				i.putExtra("className", c.editText.getText().toString());
				setResult(Activity.RESULT_OK,i);
				finish();
			}
		});
	}

	@Override
	protected void onStart() {
		super.onStart();
		String str = getIntent().getStringExtra("str");
		title.setText("Add Class to "+str+":");
	}
	  private boolean isEmpty(EditText etText) {
	        return etText.getText().toString().trim().length() == 0;
	    }
	
	
}
