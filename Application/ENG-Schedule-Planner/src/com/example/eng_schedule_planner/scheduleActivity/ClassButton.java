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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;



/**
 * @author timothychongg
 *
 */
public class ClassButton extends RelativeLayout {

	/**
	 * @param context
	 */
	 
	final static int WIDTH = 200;
	final static int HEIGHT = 150;
	
	final static int STANDARD_BUTTON = 0;
	final static int ADD_BUTTON = 1;
	
	final static int NOT_COMPLETED = 0;
	final static int COMPLETED = 1;
	
	final static int CHECK_WIDTH = 50;
	final static int CHECK_HEIGHT = 50;
	
	Button button;
	ImageView check;
	int isCompleted;
	
	
	AlertDialog.Builder alertDialog;
	
	public ClassButton(final Context context, int identifier, final String title) {
		super(context);
		
			
		this.setLayoutParams(new LayoutParams(WIDTH,HEIGHT));
		
		button = new Button(context);
		button.setLayoutParams(new LayoutParams(WIDTH,HEIGHT));
		button.setTextSize(20);
		button.setText(title);
		Random rnd = new Random(); 
		int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));   
		button.getBackground().setColorFilter(color,PorterDuff.Mode.MULTIPLY);
		this.addView(button);
		
		if(identifier == STANDARD_BUTTON)
		{
			
			button.setOnLongClickListener(
				new View.OnLongClickListener() {
					@Override
					public boolean onLongClick(View view) {
						((ClassButton)view.getParent()).setVisibility(View.GONE);
						ClipData data = ClipData.newPlainText("", "");
				        DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
				        view.startDrag(data, shadowBuilder, view, 0);
						return true;
					}
				});
			
			button.setOnClickListener(new OnClickListener() {
				public void onClick(final View v) {
					String completeStr =(isCompleted == COMPLETED)? "Mark as not Completed" : "Mark as Completed";
					CharSequence colors[] = new CharSequence[] {completeStr, "View Syllabus", "Check for Prerequisites", "Delete"};
					if(alertDialog == null)
					{
						alertDialog = new AlertDialog.Builder(context);
						alertDialog.setTitle(title);
						alertDialog.setItems(colors, new DialogInterface.OnClickListener() {
						    @Override
						    public void onClick(DialogInterface dialog, int which) {
						       alertDialog = null;
						       switch(which)
						       {
						       case 0:{
						    	   ClassButton buttonPtr = (ClassButton) ((Button)v).getParent();
						    	   buttonPtr.toggleCompleted();
						    	   break;
						       }
						       
						       }
						       
						    }
						});
						alertDialog.show();
					}
				}
			});
		}else if(identifier == ADD_BUTTON)
		{		
			button.setText("Add");
			//Pop up menu
			
		}
		
		button.setBackgroundResource(R.drawable.eng_core);
		//Set check mark
		check = new ImageView(context);
		check.setImageResource(R.drawable.checkmark);
	
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(CHECK_WIDTH, CHECK_HEIGHT);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.rightMargin = 10;
		params.bottomMargin = 10;
		this.addView(check,params);
		setCompleted(NOT_COMPLETED);
}
	public void toggleCompleted()
	{
		if(isCompleted == 0) setCompleted(1);
		else setCompleted(0);
	}
	
	public void setCompleted(int completed)
	{
		switch(completed)
		{
		case NOT_COMPLETED:{
			isCompleted = completed;
			check.setVisibility(View.INVISIBLE);
			break;
		}
		case COMPLETED:{
			isCompleted = completed;
			check.setVisibility(View.VISIBLE);
			break;
		}
		default: System.out.println("ERROR Setting completion of button");
		}
		
	}
	
	
}

	


