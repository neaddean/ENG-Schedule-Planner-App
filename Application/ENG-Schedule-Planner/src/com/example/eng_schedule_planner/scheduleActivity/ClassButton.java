package com.example.eng_schedule_planner.scheduleActivity;


import java.util.Random;

import com.example.eng_schedule_planner.R;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;

import android.graphics.PorterDuff;
import android.view.View;

import android.view.ViewGroup.LayoutParams;
import android.widget.Button;



/**
 * @author timothychongg
 *
 */
public class ClassButton extends Button {

	/**
	 * @param context
	 */
	
	final static int WIDTH = 200;
	final static int HEIGHT = 150;
	
	final static int STANDARD_BUTTON = 0;
	final static int ADD_BUTTON = 1;
	
	
	AlertDialog.Builder alertDialog;
	
	public ClassButton(final Context context, int identifier, final String title) {
		super(context);
		this.setLayoutParams(new LayoutParams(
				WIDTH,HEIGHT)
				);
		Random rnd = new Random(); 
		int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));   
		this.getBackground().setColorFilter(color,PorterDuff.Mode.MULTIPLY);
		this.setText(title);
		if(identifier == STANDARD_BUTTON)
		{
			
			this.setOnLongClickListener(
				new View.OnLongClickListener() {
					@Override
					public boolean onLongClick(View view) {
						view.setVisibility(View.GONE);
						ClipData data = ClipData.newPlainText("", "");
				        DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
				        view.startDrag(data, shadowBuilder, view, 0);
						return true;
					}
				});
			
			this.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					CharSequence colors[] = new CharSequence[] {"Mark as Completed", "View Syllabus", "Check for Prerequisites", "Delete"};
					if(alertDialog == null)
					{
						alertDialog = new AlertDialog.Builder(context);
						alertDialog.setTitle(title);
						alertDialog.setItems(colors, new DialogInterface.OnClickListener() {
						    @Override
						    public void onClick(DialogInterface dialog, int which) {
						       alertDialog = null;
						    }
						});
						alertDialog.show();
					}
				}
			});
		}else if(identifier == ADD_BUTTON)
		{		
			this.setText("Add");
			//Pop up menu
			
		}
		
		this.setBackgroundResource(R.drawable.coursebutton);
		
	}
}

	


