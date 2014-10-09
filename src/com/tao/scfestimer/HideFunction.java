package com.tao.scfestimer;

import com.google.analytics.tracking.android.EasyTracker;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class HideFunction extends Activity {
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hide_function);
		//アナリティクスstart
		EasyTracker.getInstance(this).activityStart(this);
	}
	
	public void Enable(View v){
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
		if(pref.getBoolean("HideFunction", false) == false){
		pref.edit().putBoolean("HideFunction", true).commit();
		Toast.makeText(this, "有効化されました！", Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(this, "もう有効化されてるんですがそれは...", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void Sign(View v){
		try{
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
		boolean HideFunction = pref.getBoolean("HideFunction", false);
		if(HideFunction == true){
			EditText time = (EditText)findViewById(R.id.HideFunction_time);
			int customLeftTime = Integer.parseInt(time.getText().toString());
			pref.edit().putInt("customLeftTime", customLeftTime).commit();
			Toast.makeText(this, customLeftTime + "分前で登録されました！", Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(this, "先に有効化してください", Toast.LENGTH_SHORT).show();
		}
		}catch(Exception e){
			Toast.makeText(this, "ん？", Toast.LENGTH_SHORT).show();
		}
	}
	
	//アナリティクスstop
	public void onStop(){
	super.onStop();
	EasyTracker.getInstance(this).activityStop(this);
}
}
