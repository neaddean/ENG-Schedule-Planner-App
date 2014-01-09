package com.sunTANDProd.EYD.scheduleActivity;


import java.util.Random;

import com.sunTANDProd.EYD.Global.Global;
import com.sunTANDProd.EYD.addClassActivity.addClassActivity;
import com.sunTANDProd.EYD.courseModel.CourseModel;
import com.sunTANDProd.EYD.courseModel.GenericCourse;
import com.sunTANDProd.EYD.menus.descriptionActivity;
import com.sunTANDproductions.eng_schedule_planner.R;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.RelativeLayout;

public class ClassButton extends RelativeLayout implements GestureDetector.OnDoubleTapListener, OnGestureListener{

	/**
	 * @param context
	 */
	 
	final static int WIDTH = 160;
	final static int HEIGHT = 120;
	
	final static int STANDARD_BUTTON = 0;
	final static int ADD_BUTTON = 1;
	
	final static int NOT_COMPLETED = 0;
	final static int COMPLETED = 1;
	
	final static int CHECK_WIDTH = 40;
	final static int CHECK_HEIGHT = 40;
	
	final static int buttonPictures[] = {
		R.drawable.eng_core,
		R.drawable.nat_sci,
		R.drawable.math,
		R.drawable.gen_ed,
		R.drawable.me_req,
		R.drawable.ece_req,
		R.drawable.bme_req,
		R.drawable.default_course,
		R.drawable.generic_class,
		};
	
	Button button;
	ImageView check;
	boolean isCompleted;
	int buttonType; 
	GestureDetectorCompat myDetector;
	AlertDialog.Builder alertDialog;
	GenericCourse myCourse;
	
 	public ClassButton(final Context context, int identifier, GenericCourse myCourse) {
		super(context);
		
		//Set up variables
		buttonType = identifier;
		this.myCourse = myCourse;
		
		
		this.setLayoutParams(new LayoutParams(WIDTH,HEIGHT));
		
		//Detector Listeners
		myDetector = new GestureDetectorCompat(context, this);
		myDetector.setOnDoubleTapListener(this);
		
		
		//Set up button
		button = new Button(context);
		button.setLayoutParams(new LayoutParams(WIDTH,HEIGHT));
		button.setTextSize(15);
		if(buttonType == STANDARD_BUTTON)
			button.setText(myCourse.getFullTitle());
		else if(buttonType == ADD_BUTTON)
			button.setText("Add");
		Random rnd = new Random(); 
		int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));   
		button.getBackground().setColorFilter(color,PorterDuff.Mode.MULTIPLY);
		button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				return myDetector.onTouchEvent(event);
			}
		});
		this.addView(button);
	
		
		//int randomnumber = rnd.nextInt(buttonPictures.length);
		if(buttonType == STANDARD_BUTTON)
			button.setBackgroundResource(buttonPictures[myCourse.getCategory()]);
		else if(buttonType == ADD_BUTTON)
			button.setBackgroundResource(R.drawable.generic_class);
		//Set check mark
		check = new ImageView(context);
		check.setImageResource(R.drawable.checkmark);
	
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(CHECK_WIDTH, CHECK_HEIGHT);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.rightMargin = 10;
		params.bottomMargin = 10;
		this.addView(check,params);
		if(buttonType == STANDARD_BUTTON)
			setCompleted(myCourse.completed);
		else
			check.setVisibility(View.INVISIBLE);
 	}
	
 	
	
	public void toggleCompleted()
	{
		setCompleted(!isCompleted);
	}
	
	public void setCompleted(boolean completed)
	{
		if(buttonType == STANDARD_BUTTON)
		{
			if(!completed){
				isCompleted = completed;
				check.setVisibility(View.INVISIBLE);

			}else{
				isCompleted = completed;
				check.setVisibility(View.VISIBLE);
			}
			
			myCourse.completed = isCompleted;
			CourseModel.getInstance().save();
		}
		
		
	}

	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}



	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		
		return false;
	}

	public void onLongPress(MotionEvent e) {
		if(buttonType == STANDARD_BUTTON)
		{
			final Button b = this.button;
			this.animate().alpha(0).scaleX(0).withStartAction(new Runnable() {
				public void run() {
					ClipData data = ClipData.newPlainText("", "");
					DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(b);
					b.startDrag(data, shadowBuilder, b, 0);
					setVisibility(View.GONE);
				}
			});
			
		}
		
	}

	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}

	public boolean onDoubleTap(MotionEvent arg0) {
		if(buttonType == STANDARD_BUTTON)
		{
			toggleCompleted();
		}
		return false;
	}

	@Override
	public boolean onDoubleTapEvent(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onSingleTapConfirmed(MotionEvent e) {
		if(buttonType == STANDARD_BUTTON)
		{
			String completeStr =(isCompleted)? "Mark as not Completed" : "Mark as Completed";
			CharSequence colors[] = new CharSequence[] {completeStr, "View Description", "Delete"};
			if(alertDialog == null)
			{
				final ClassButton buttonPtr = this;
				alertDialog = new AlertDialog.Builder(this.getContext());
				alertDialog.setTitle(myCourse.getFullTitle());
				alertDialog.setItems(colors, new DialogInterface.OnClickListener() {
				    @Override
				    public void onClick(DialogInterface dialog, int which) {
				    	alertDialog = null;
				    	switch(which)
				       {
				       case 0:{
				    	   buttonPtr.toggleCompleted();
				    	   Toast.makeText(buttonPtr.getContext(), "Tip: Double Click Button to Mark Complete", Toast.LENGTH_SHORT).show();
				    	   break;
				       }
				       case 1:{
				    	   //go to description activity, also shows prereqs
				    	   Global.courseDescriptionTitle = myCourse.getFullTitle();
				    	   Global.courseDescriptionText = myCourse.getDescription();
				    	   Global.courseDescriptionPrereqs = myCourse.getPrereqs();
				    	   Intent goToDescriptionActivity = new Intent(buttonPtr.getContext(), descriptionActivity.class);
				    	   buttonPtr.getContext().startActivity(goToDescriptionActivity);
				       }break;
				       case 2:{
				    	   YearView yearPtr = (YearView) buttonPtr.getParent().getParent().getParent();
				    	   try {
							yearPtr.removeButton(buttonPtr);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}						    	   
				       }
				       }
				       
				    }
				});
				alertDialog.setOnCancelListener(new OnCancelListener() {
					
					@Override
					public void onCancel(DialogInterface dialog) {
						alertDialog = null;
					}
				});
				alertDialog.show();
			}
		}else if(buttonType == ADD_BUTTON)
		{
			ScheduleActivity s = (ScheduleActivity) this.getContext();
			
			Intent intent = new Intent(s, addClassActivity.class);
			
			YearView yearPtr = (YearView) this.getParent().getParent().getParent();
			if(Global.YearToAddClass == null)
				Global.YearToAddClass = yearPtr;
			else
				System.out.println ("YearView Already set");
			Button view = button;
			ActivityOptions options = ActivityOptions.makeScaleUpAnimation(view, 0,
				      0, view.getWidth(), view.getHeight());
			s.startActivityForResult(intent, 0,options.toBundle());
			System.out.println();
		}
		return false;
	}

	

	
}

	


