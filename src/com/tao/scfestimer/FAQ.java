package com.tao.scfestimer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FAQ extends Activity {
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.faq);
	}
	public void back(View v){
		Intent main = new Intent(this, MainActivity.class);
		startActivity(main);
		finish();
	}
}
