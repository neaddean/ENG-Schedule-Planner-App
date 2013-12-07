package com.example.eng_schedule_planner.addClassActivity;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.eng_schedule_planner.R;

import courseModel.CourseModel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class addClassActivity extends Activity {

	TextView title;
	EditText editText;
	Button addButton;
	
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
		
		// Listview Data
        ArrayList<String> nameList = new ArrayList<String>();//CourseModel.getInstance().getCourseTitles();
        
	
        lv = (ListView) findViewById(R.id.list_view);
        inputSearch = (EditText) findViewById(R.id.inputSearch);
         
        // Adding items to listview
        adapter = new ArrayAdapter<String>(this, R.layout.addsearchableclass_item, R.id.product_name, nameList);
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

	}

	@Override
	protected void onStart() {
		super.onStart();
		String str = getIntent().getStringExtra("str");
		//title.setText("Add Class to "+str+":");
	}
	  
	private boolean isEmpty(EditText etText) {
	        return etText.getText().toString().trim().length() == 0;
	    }
	  
	
	
}
