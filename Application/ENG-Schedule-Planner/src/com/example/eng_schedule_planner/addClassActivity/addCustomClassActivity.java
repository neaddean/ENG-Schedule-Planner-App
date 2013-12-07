package com.example.eng_schedule_planner.addClassActivity;

import com.example.eng_schedule_planner.R;

import com.example.eng_schedule_planner.Global.Global;
import com.example.eng_schedule_planner.scheduleActivity.YearView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class addCustomClassActivity extends Activity {
	
	TextView title;
	EditText editText;
	Button addCustomButton;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_class);
		
		title = (TextView) findViewById(R.id.addClassLabel);
		editText = (EditText) findViewById(R.id.editText1);
		addCustomButton = (Button) findViewById(R.id.addClassButton);
		
		addCustomButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                    addCustomClassActivity c = (addCustomClassActivity) v.getContext();
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
	
	private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }
	
	protected void onStart() {
		super.onStart();
		YearView str = Global.YearToAddClass;
		title.setText("Add Custom Class to "+str.yearLabel.getText()+":");
	}
}
