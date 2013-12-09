package com.sunTANDproductions.eng_schedule_planner.menus;

import com.sunTANDproductions.eng_schedule_planner.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class aboutActivity  extends Activity{
	Button mainMenu;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_screen);
		
		mainMenu = (Button) findViewById(R.id.back_button);
	/*	mainMenu.setOnClickListener(new onClickListener(){
			public void onClick(View v){
				setContentView(R.layout.main_menu);
			}
		}
		
	*/
		mainMenu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				//Intent goToMainMenuActivity = new Intent(arg0.getContext(), mainMenuActivity.class);
				//startActivity(goToMainMenuActivity);			
				finish();
			}
		
		
	});
	}
};