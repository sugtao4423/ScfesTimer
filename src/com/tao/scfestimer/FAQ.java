package com.tao.scfestimer;

import com.google.analytics.tracking.android.EasyTracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FAQ extends Activity {
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.faq);
		//アナリティクスstart
		EasyTracker.getInstance(this).activityStart(this);
	}
	public void back(View v){
		Intent main = new Intent(this, MainActivity.class);
		startActivity(main);
		finish();
	}
	//アナリティクスstop
	public void onStop(){
		super.onStop();
		EasyTracker.getInstance(this).activityStop(this);
	}
}
