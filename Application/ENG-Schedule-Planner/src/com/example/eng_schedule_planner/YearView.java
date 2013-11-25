package com.example.eng_schedule_planner;

import java.util.ArrayList;


import android.animation.LayoutTransition;
import android.content.Context;
import android.graphics.Color;
import android.view.DragEvent;
import android.view.Menu;
import android.view.View;

import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ScrollView;
import android.widget.Space;
import android.widget.TextView;

import com.example.eng_schedule_planner.ClassButton;

public class YearView extends LinearLayout {

	
	final int SPACE_WIDTH = 30;
	//String yearLabel;
	TextView yearLabel;
	ArrayList<ClassButton> classList;
	HorizontalScrollView horizontalScroll;
	LinearLayout horizontalLayout;
	LayoutTransition transition;
	
	public YearView(Context context) throws Exception {
		super(context);
		throw new Exception("Invalid YearView Class Object Created");
	}
	
	public YearView(final Context context, String yearName)
	{
		super(context);
		
	
		//Setting up the layout param for itself
		this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
		this.setOrientation(1);
				
		classList = new ArrayList<ClassButton>();	
		
		//Adding the year label
		yearLabel = new TextView(context);
		yearLabel.setText(yearName);
		yearLabel.setPadding(50, 0, 0, 0);
		yearLabel.setTextSize(50);
		this.addView(yearLabel);
		
		//Add the horizontal scroll layout
		horizontalScroll = new HorizontalScrollView(context);
		horizontalScroll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		horizontalScroll.setHorizontalScrollBarEnabled(false);
		horizontalScroll.setOverScrollMode(ScrollView.OVER_SCROLL_NEVER);
		//horizontalScroll.setBackgroundColor(Color.GREEN);
		
		//Linear layout inside horizontal scroll layout
		horizontalLayout = new LinearLayout(context);
		horizontalLayout.setLayoutParams(new LayoutParams(0,LayoutParams.WRAP_CONTENT,1f));
		horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);
		horizontalScroll.addView(horizontalLayout);

		//horizontalLayout.setBackgroundColor(Color.RED);	
		
		//Add space in front and the end
		LayoutParams spaceParam = new LayoutParams(SPACE_WIDTH,LayoutParams.WRAP_CONTENT);
		Space beforeSpace= new Space(context);
		beforeSpace.setLayoutParams(spaceParam);
		
		horizontalLayout.addView(beforeSpace);
		
		for(int j = 0; j <6; j++)
		{
			ClassButton myButton = new ClassButton(context,ClassButton.STANDARD_BUTTON);
			horizontalLayout.addView(myButton);
			classList.add(myButton);
			myButton.setText(Integer.toString(j));
		}
		ClassButton addButton = new ClassButton(context, ClassButton.ADD_BUTTON);
		horizontalLayout.addView(addButton);
		
		this.addView(horizontalScroll);
		
		Space afterSpace= new Space(context);
		afterSpace.setLayoutParams(spaceParam);
		horizontalLayout.addView(afterSpace);
		horizontalLayout.setOnDragListener(new ClassDragListener());
	}
			
	class ClassDragListener implements OnDragListener
	{
	
		@Override
		public boolean onDrag(View v, DragEvent dragEvent) {
			int action = dragEvent.getAction();
			ClassButton view = (ClassButton) dragEvent.getLocalState();
			switch(action)
			{
				case DragEvent.ACTION_DRAG_STARTED:
					System.out.println("Drag started");
					break;
				case DragEvent.ACTION_DRAG_ENTERED:
					System.out.println("Drag entered");
			        break;
				case DragEvent.ACTION_DRAG_LOCATION:
					break;
		      case DragEvent.ACTION_DRAG_EXITED:
		    	  System.out.println("Drag exited");
		        break;
		      case DragEvent.ACTION_DROP:
		          YearView yearView  = (YearView) view.getParent().getParent().getParent();
		          
		          try {
		        	  yearView.removeButton((ClassButton) view);
					} catch (Exception e1) {
						e1.printStackTrace();
					} 
		       
		        float x = dragEvent.getX();
		    	int index = (int)((x - SPACE_WIDTH + ClassButton.WIDTH/2) / ClassButton.WIDTH);
		        YearView currentView = (YearView)v.getParent().getParent();
		        currentView.addButton((ClassButton) view,index);
		        view.setVisibility(View.VISIBLE);
		        break;
		      case DragEvent.ACTION_DRAG_ENDED:
		    	 
		    	  if(!dragEvent.getResult())
		    		  view.setVisibility(View.VISIBLE);

		    	  break;
		      default:
		        break;
			}
			
			return true;
		}
		
	
	}
	
	public void removeButton(ClassButton button) throws Exception
	{
		LinearLayout parent = (LinearLayout) button.getParent();
		parent.removeView(button);
		if(!classList.remove(button))
			throw new Exception("Fail to remove button from classlist.");
	}
	
	public void addButton(ClassButton button, int index)
	{
		index = index < 0 ? 0 : index;
		int childCount = horizontalLayout.getChildCount();
		index = index >= childCount-2? childCount - 3 : index;
		System.out.println(index);
		horizontalLayout.addView(button,index+1);
		classList.add(index, button);
		System.out.println(classList);
	}
	
}


