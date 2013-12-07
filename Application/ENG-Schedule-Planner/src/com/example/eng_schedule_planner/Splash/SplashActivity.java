package com.example.eng_schedule_planner.Splash;

import com.example.eng_schedule_planner.R;
import com.example.eng_schedule_planner.scheduleActivity.ScheduleActivity;

import courseModel.CourseModel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;

public class SplashActivity extends Activity {

    private final int SPLASH_DISPLAY_LENGHT = 2000;

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceStat) {
        super.onCreate(savedInstanceStat);
        
        
        setContentView(R.layout.splash_screen);

        /* New Handler to start the Menu-Activity 
         * and close this Splash-Screen after some seconds.*/
        final Activity s = this;
        CourseModel model = CourseModel.getInstance();
		model.loadCourseFile(this);
        new Handler().postDelayed(new Runnable(){
          
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(s,ScheduleActivity.class);
                s.startActivity(mainIntent);
                s.finish();
            }
        }, SPLASH_DISPLAY_LENGHT);
    }
}
