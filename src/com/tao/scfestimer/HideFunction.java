package com.tao.scfestimer;

import com.google.analytics.tracking.android.EasyTracker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
		AlertDialog.Builder builder = new AlertDialog.Builder(this)
		.setTitle("有効化されました！")
		.setMessage("次にボタン下にあるフィールドに任意の分数を入力してください")
		.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				return;
			}
		});
		builder.create().show();
		}else{
			Toast.makeText(this, "もう有効化されてるんだよなぁ...", Toast.LENGTH_SHORT).show();
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
			InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
			AlertDialog.Builder builder = new AlertDialog.Builder(this)
			.setTitle(customLeftTime + "分前で登録されました！")
			.setMessage("アプリを再起動します")
			.setPositiveButton("再起動", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					finish();
					android.os.Process.killProcess(android.os.Process.myPid());
				}
			});
			builder.create().show();
		}else{
			Toast.makeText(this, "先に有効化してください", Toast.LENGTH_SHORT).show();
			InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
		}
		}catch(Exception e){
			Toast.makeText(this, "ん？", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void background (View v){
		InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
	}
	
	//アナリティクスstop
	public void onStop(){
	super.onStop();
	EasyTracker.getInstance(this).activityStop(this);
}
}
