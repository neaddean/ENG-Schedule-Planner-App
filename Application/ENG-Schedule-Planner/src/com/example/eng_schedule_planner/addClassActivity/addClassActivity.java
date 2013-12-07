package com.example.eng_schedule_planner.addClassActivity;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.eng_schedule_planner.R;
import com.example.eng_schedule_planner.Global.Global;
import com.example.eng_schedule_planner.scheduleActivity.YearView;

import courseModel.CourseModel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class addClassActivity extends Activity {

	TextView title;
	EditText editText;
	Button addCustomButton;
	
	// List view
    private ListView lv;
     
    // Listview Adapter
    ArrayAdapter<String> adapter;
    // Search EditText
    EditText inputSearch;

    // ArrayList for Listview
    ArrayList<HashMap<String, String>> productList;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_addsearchableclass);
		addCustomButton = (Button) findViewById(R.id.customClassButton);
		// Listview Data
        
        ArrayList<String> titleList = CourseModel.getInstance().getCourseTitleList();
        title = (TextView) findViewById(R.id.addClassText);
        lv = (ListView) findViewById(R.id.list_view);
        inputSearch = (EditText) findViewById(R.id.inputSearch);
        
        lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
			}
        });
        // Adding items to listview
        adapter = new ArrayAdapter<String>(this, R.layout.addsearchableclass_item, R.id.product_name, titleList);
        lv.setAdapter(adapter);       
		
  	  inputSearch.addTextChangedListener(new TextWatcher() {
		     
		    @Override
		    public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
		        // When user changed the Text
		        addClassActivity.this.adapter.getFilter().filter(cs);   
		    }
		     
		    @Override
		    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
		            int arg3) {
		        // TODO Auto-generated method stub
		         
		    }
		     
		    @Override
		    public void afterTextChanged(Editable arg0) {
		        // TODO Auto-generated method stub                          
		    }
		});
  	  final addClassActivity s = this;
  	  addCustomButton.setOnClickListener(new OnClickListener() {
		public void onClick(View arg0) {
			Intent intent = new Intent(s,addCustomClassActivity.class);
			s.startActivityForResult(intent, 0);
		}
	});
  	  
  	  
	}

	@Override
	protected void onStart() {
		super.onStart();
		YearView str = Global.YearToAddClass;
		title.setText("Add Class to "+str.yearLabel.getText()+":");
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if((resultCode == RESULT_OK)) {
	        setResult(RESULT_OK, data);
	        finish();
	    }
	}
	
}
