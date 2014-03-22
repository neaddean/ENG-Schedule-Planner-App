package com.sunTANDProd.EYD.scheduleActivity;


import com.sunTANDProd.EYD.Global.Global;
import com.sunTANDProd.EYD.courseModel.CourseModel;
import com.sunTANDproductions.eng_schedule_planner.R;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;


import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.LinearLayout.LayoutParams;


public class ScheduleActivity extends Activity {
	
	//Button saveAsBitmapButton = new Button(getApplicationContext());

	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//create a button to save the screen as an image

		/*saveAsBitmapButton.setText("Save Image");
		saveAsBitmapButton.setLayoutParams(new LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT));
		saveAsBitmapButton.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				loadBitmapFromView(v, v.getLayoutParams().width, v.getLayoutParams().height);
			}
		});*/
		setUpGrid();
	}

	LinearLayout listLayout;
	private void setUpGrid()
	{
		listLayout = (LinearLayout) findViewById(R.id.schedule_vertical_layout);
		listLayout.addView(new YearView(this,"AP / Transfer Credits",CourseModel.EXTERNAL_CREDITS,CourseModel.SUMMER));
		listLayout.addView(new YearView(this,"Freshman Fall",CourseModel.FRESHMAN_YEAR,CourseModel.FALL));
		listLayout.addView(new YearView(this,"Freshman Spring",CourseModel.FRESHMAN_YEAR,CourseModel.SPRING));
		listLayout.addView(new YearView(this,"Sophomore Fall",CourseModel.SOPHOMORE_YEAR,CourseModel.FALL));
		listLayout.addView(new YearView(this,"Sophomore Spring",CourseModel.SOPHOMORE_YEAR,CourseModel.SPRING));
		listLayout.addView(new YearView(this,"Junior Fall",CourseModel.JUNIOR_YEAR,CourseModel.FALL));
		listLayout.addView(new YearView(this,"Junior Spring",CourseModel.JUNIOR_YEAR,CourseModel.SPRING));
		listLayout.addView(new YearView(this,"Senior Fall",CourseModel.SENIOR_YEAR,CourseModel.FALL));
		listLayout.addView(new YearView(this,"Senior Spring",CourseModel.SENIOR_YEAR,CourseModel.SPRING));
		//listLayout.addView(saveAsBitmapButton);
		LayoutParams spaceParam = new LayoutParams(LayoutParams.WRAP_CONTENT,50);
		Space afterSpace= new Space(this);
		afterSpace.setLayoutParams(spaceParam);
		listLayout.addView(afterSpace);
		
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		super.onActivityResult(requestCode, resultCode, data);
		YearView yToAdd = Global.YearToAddClass;
		Global.YearToAddClass = null;
		if(resultCode == Activity.RESULT_OK)
		{
			if(Global.courseToAdd == null){
				if(yToAdd != null)
					{
						String className = data.getStringExtra("className");
						yToAdd.addNewClassWithName(className);
						
					}else
						System.out.println("Error: Schedul Acitvity: No year to add");
			}else
			{
				yToAdd.addNewClassWithCourse(Global.courseToAdd);
				Global.courseToAdd = null;
			}
		}
	}
	
	public static Bitmap loadBitmapFromView(View v, int width, int height) {
	    Bitmap b = Bitmap.createBitmap(width , height, Bitmap.Config.ARGB_8888);                
	    Canvas c = new Canvas(b);
	    v.layout(0, 0, v.getLayoutParams().width, v.getLayoutParams().height);
	    v.draw(c);
	    return b;
	}
}
