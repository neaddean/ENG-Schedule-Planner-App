package com.sunTANDProd.EYD.Splash;

import java.util.ArrayList;
import java.util.Arrays;

import com.sunTANDProd.EYD.Global.Global;
import com.sunTANDProd.EYD.courseModel.CourseModel;
import com.sunTANDProd.EYD.menus.mainMenuActivity;
import com.sunTANDProd.EYD.scheduleActivity.ScheduleActivity;
import com.sunTANDproductions.eng_schedule_planner.R;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;

public class SplashActivity extends Activity {

    private final int SPLASH_DISPLAY_LENGHT = 3000;

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Global.myContext = this;
        
        setContentView(R.layout.splash_screen);

        /* New Handler to start the Menu-Activity 
         * and close this Splash-Screen after some seconds.*/
        final Activity s = this;
        new Handler().postDelayed(new Runnable(){
          
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                //Intent mainIntent = new Intent(s,ScheduleActivity.class);
            	//Nate -- should take us to main menu first
            	//can switch back to scheduleActivity by uncommenting above and commenting below
            	Intent mainIntent = new Intent(s,mainMenuActivity.class);
                s.startActivity(mainIntent);
                s.finish();
            }
        }, SPLASH_DISPLAY_LENGHT);
    }
    
    @Override
    protected void onStart() {
    	// TODO Auto-generated method stub
    	super.onStart();
    	
    	CourseModel model = CourseModel.getInstance();
		model.loadCourseFile(this);
    	//model.saveState("savefile", this);
		//model.loadState("savefile", this);
	//	model.addCourse("Dean", "ENG", "EC", "111", "dean", new ArrayList<String>(), 4, this);
		System.out.println("done");
    }
}
