package com.jx372.test.tmap;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jx372.test.R;

public class BalloonOverlayView extends FrameLayout {

	private LinearLayout layout;
	private TextView allPath;
	private TextView sectionPath;
	private ImageView clickImage;
	
	public BalloonOverlayView(Context context, int balloonBottomOffset) {

		super(context);

		setPadding(10, 0, 10, balloonBottomOffset);
		layout = new LinearLayout(context); //���� ���̾ƿ�
		layout.setVisibility(VISIBLE);

		setupView(context, layout);
		
		LayoutParams params = new LayoutParams(
								LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.gravity = Gravity.NO_GRAVITY;

		addView(layout, params);
	}
	
	
	protected void setupView(Context context, final ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		
		View view = inflater.inflate(R.layout.bubble_popup, parent, true);
		
		allPath = (TextView) view.findViewById(R.id.bubble_title);
		sectionPath = (TextView) view.findViewById(R.id.bubble_subtitle1);
		
		clickImage = (ImageView)view.findViewById(R.id.bubble_right);
		
	}
	
	
	public ImageView getClickImage() {
		
		return clickImage;
	}
	
	

}
