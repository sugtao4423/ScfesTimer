package com.tao.scfestimer;

import com.google.analytics.tracking.android.EasyTracker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
		.setTitle(R.string.hide_function_dialog_enable)
		.setMessage(R.string.hide_function_dialog_next___)
		.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				return;
			}
		});
		builder.create().show();
		}else{
			Toast.makeText(this, R.string.hide_function_dialog_already_enabled, Toast.LENGTH_SHORT).show();
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
			.setTitle(String.valueOf(customLeftTime) + " " +
					getResources().getString(R.string.hide_function_dialog_entry_beforeMinute))
			.setMessage(R.string.hide_function_dialog_app_reboot)
			.setPositiveButton(R.string.hide_function_dialog_reboot, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					finish();
					android.os.Process.killProcess(android.os.Process.myPid());
				}
			});
			builder.create().show();
		}else{
			Toast.makeText(this, R.string.hide_function_dialog_enable_earlier, Toast.LENGTH_SHORT).show();
			InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
		}
		}catch(Exception e){
			Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
		}
	}
	
	public void Disable(View v){
		try{
		final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
		boolean HideFunction = pref.getBoolean("HideFunction", false);
		if(HideFunction == false)
			Toast.makeText(this, R.string.hide_function_dialog_not_enable, Toast.LENGTH_SHORT).show();
		else{
			AlertDialog.Builder builder = new AlertDialog.Builder(this)
			.setTitle(R.string.hide_function_dialog_doyoudisable)
			.setMessage(R.string.hide_function_dialog_ifDisable_appReboot)
			.setPositiveButton(R.string.hide_function_dialog_disable, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Editor editor = pref.edit();
					editor.putBoolean("HideFunction", false).commit();
					finish();
					android.os.Process.killProcess(android.os.Process.myPid());
				}
			})
			.setNegativeButton(R.string.hide_function_dialog_cancel, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					return;
				}
			});
			builder.create().show();
		}
		}catch(Exception e){
			Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
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
