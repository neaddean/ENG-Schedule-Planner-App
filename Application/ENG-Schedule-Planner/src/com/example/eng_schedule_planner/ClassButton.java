package com.example.eng_schedule_planner;


import java.util.Random;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;

import android.view.Gravity;

import android.view.View;

import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

import android.widget.PopupWindow;


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
	
	public ClassButton(final Context context, int identifier) {
		super(context);
		this.setLayoutParams(new LayoutParams(
				WIDTH,HEIGHT)
				);
		Random rnd = new Random(); 
		int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));   
		this.setBackgroundColor(color);
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
			
			final String title = this.getText().toString();
			this.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					CharSequence colors[] = new CharSequence[] {"red", "green", "blue", "black"};
					AlertDialog.Builder builder = new AlertDialog.Builder(context);
					builder.setTitle(title);
					builder.setItems(colors, new DialogInterface.OnClickListener() {
					    @Override
					    public void onClick(DialogInterface dialog, int which) {
					        // the user clicked on colors[which]
					    }
					});
					builder.show();
				}
			});
		}else if(identifier == ADD_BUTTON)
		{		
			this.setText("Add");
			//Pop up menu
			
		}
		
	}
}

	


