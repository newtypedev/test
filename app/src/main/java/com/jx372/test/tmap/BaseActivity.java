package com.jx372.test.tmap;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Switch;
import android.widget.Toast;

import com.jx372.test.R;


public class BaseActivity extends AppCompatActivity implements View.OnClickListener {
	
	private RelativeLayout contentView = null;
	private static Context mCtx = null;






	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.setContentView(R.layout.base_activity);
		mCtx = this;
		getSupportActionBar().setTitle("경로검색");
		
		contentView  = (RelativeLayout)findViewById(R.id.contentView);

		super.onCreate(savedInstanceState);
		
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	
	
	@Override
	public void setContentView(int res)  {
		
		contentView.removeAllViews();
		
		LayoutInflater inflater;
		inflater = LayoutInflater.from(this);
		
		View item = inflater.inflate(res, null);
		contentView.addView(item, new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
				
	}

	
	
	@Override
	public void setContentView(View view) {
		
		contentView.removeAllViews();
		
		contentView.addView(view, new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		
	}
	
	
	public void addView(View v)
	{
		contentView.removeAllViews();
		contentView.addView(v, new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
	}

	
	
	@Override
	public void onClick(View v) {
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
