package com.example.eng_schedule_planner.scheduleActivity;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Button;

public class TapButton extends Button {


	GestureDetector myGestureDetector;
	public TapButton(Context context) {
		super(context);

		myGestureDetector = new GestureDetector(context, new GestureListener());
		// TODO Auto-generated constructor stub
	}
	   @Override
	    public boolean onTouchEvent(MotionEvent e) {
	        return myGestureDetector.onTouchEvent(e);
	    }


	    private class GestureListener extends GestureDetector.SimpleOnGestureListener {

	        @Override
	        public boolean onDown(MotionEvent e) {
	            return true;
	        }
	        // event when double tap occurs
	        @Override
	        public boolean onDoubleTap(MotionEvent e) {
	           
	            return true;
	        }
	    }
	    
}