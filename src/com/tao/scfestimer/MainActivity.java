package com.tao.scfestimer;

import java.util.Calendar;
import java.util.Locale;

import com.google.analytics.tracking.android.EasyTracker;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Once();
		loadHideFunction();
		//アナリティクスstart
		EasyTracker.getInstance(this).activityStart(this);
		loadLP();
		loadEditTextBoolean();
		NotificationEnable();
		startCountDown();
		LPhealCalc();
	}
	
	public int LeftLPTime;
	public int MONTH, DATE, HOUR_OF_DAY, HOUR, MINUTE, SECOND, ampm;
	public long LeftLPSec;
	public MyCountDownTimer cdt;

	//アラーム開始
	public void start(View v){
		
		//オブジェクトの取得
		EditText MaxLP = (EditText)findViewById(R.id.MaxLP);
		EditText NowLP = (EditText)findViewById(R.id.NowLP);
		Spinner spinner = (Spinner)findViewById(R.id.spinner1);
		
		try{
		//文字から数値に変換
		String maxLP = MaxLP.getText().toString();
		String nowLP = NowLP.getText().toString();
		
		int maxlp = Integer.parseInt(maxLP);
		int nowlp = Integer.parseInt(nowLP);
		
		//maxからnowを引いて残りのLPを算出
		int LeftLP = maxlp - nowlp;
		
		//残りのLPに1回復の6分を掛ける
		int LeftLPTime = LeftLP * 6;
		
		// 選択されているアイテムのIndexを取得
        int spi = spinner.getSelectedItemPosition();
        
        //if(目標LP≦現在LP)
        if(maxlp <= nowlp){
        	if(Locale.getDefault().toString().startsWith("ja"))
        		Toast.makeText(this, "もう回復してるんですがそれは...", Toast.LENGTH_SHORT).show();
        	else
        		Toast.makeText(this, "Already recovered", Toast.LENGTH_SHORT).show();
        }else{
        
        if(spi==0){
        	//呼び出す日時の設定
    		Calendar triggerTime = Calendar.getInstance();
    		triggerTime.add(Calendar.MINUTE, LeftLPTime);
    		
    		Calendar cal = Calendar.getInstance();
    		cal.add(Calendar.MINUTE, LeftLPTime);
    		
    		MONTH = cal.get(Calendar.MONTH) + 1;
    		DATE = cal.get(Calendar.DATE);
    		HOUR_OF_DAY = cal.get(Calendar.HOUR_OF_DAY);
    		HOUR = cal.get(Calendar.HOUR);
    		MINUTE = cal.get(Calendar.MINUTE);
    		SECOND = cal.get(Calendar.SECOND);
    		ampm = cal.get(Calendar.AM_PM);
    		
    		SetResultTime();
    		startCountDown();
    		
    		//設定した日時で発行するIntent生成
    		Intent intent = new Intent(this, Notifier.class);
    		PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

    		//日時と発行するIntentをAlarmManagerにセット
    		AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
    		manager.set(AlarmManager.RTC_WAKEUP, triggerTime.getTimeInMillis(), sender);
       		
    		//EditTextを入力不可にする
    		NoEditText();
    		SettingTime();
    		if(Locale.getDefault().toString().startsWith("ja"))
    			Toast.makeText(this, "LP回復時に通知します", Toast.LENGTH_SHORT).show();
    		else
    			Toast.makeText(this, "Full notification", Toast.LENGTH_SHORT).show();
        }
        if(spi==1){
        	LeftLPTime = LeftLPTime - 5;
        	//呼び出す日時の設定
    		Calendar triggerTime = Calendar.getInstance();
    		triggerTime.add(Calendar.MINUTE, LeftLPTime);
    		
    		Calendar cal = Calendar.getInstance();
    		cal.add(Calendar.MINUTE, LeftLPTime);
    		
    		MONTH = cal.get(Calendar.MONTH) + 1;
    		DATE = cal.get(Calendar.DATE);
    		HOUR_OF_DAY = cal.get(Calendar.HOUR_OF_DAY);
    		HOUR = cal.get(Calendar.HOUR);
    		MINUTE = cal.get(Calendar.MINUTE);
    		SECOND = cal.get(Calendar.SECOND);
    		ampm = cal.get(Calendar.AM_PM);
    		
    		SetResultTime();
    		startCountDown();
    		
    		//設定した日時で発行するIntent生成
    		Intent intent = new Intent(this, Notifier02.class);
    		PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    				
    		//日時と発行するIntentをAlarmManagerにセット
    		AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
    		manager.set(AlarmManager.RTC_WAKEUP, triggerTime.getTimeInMillis(), sender);
    		
    		//EditTextを入力不可にする
    		NoEditText();
    		SettingTime();
    		if(Locale.getDefault().toString().startsWith("ja"))
    			Toast.makeText(this, "LP回復5分前に通知します", Toast.LENGTH_SHORT).show();
    		else
    			Toast.makeText(this, "5 minute before notification.", Toast.LENGTH_SHORT).show();
        }
        if(spi==2){
        	LeftLPTime = LeftLPTime - 10;
        	//呼び出す日時の設定
    		Calendar triggerTime = Calendar.getInstance();
    		triggerTime.add(Calendar.MINUTE, LeftLPTime);
    		
    		Calendar cal = Calendar.getInstance();
    		cal.add(Calendar.MINUTE, LeftLPTime);
    		
    		MONTH = cal.get(Calendar.MONTH) + 1;
    		DATE = cal.get(Calendar.DATE);
    		HOUR_OF_DAY = cal.get(Calendar.HOUR_OF_DAY);
    		HOUR = cal.get(Calendar.HOUR);
    		MINUTE = cal.get(Calendar.MINUTE);
    		SECOND = cal.get(Calendar.SECOND);
    		ampm = cal.get(Calendar.AM_PM);
    		
    		SetResultTime();
    		startCountDown();
    		
    		//設定した日時で発行するIntent生成
    		Intent intent = new Intent(this, Notifier03.class);
    		PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    				
    		//日時と発行するIntentをAlarmManagerにセット
    		AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
    		manager.set(AlarmManager.RTC_WAKEUP, triggerTime.getTimeInMillis(), sender);
    		
    		//EditTextを入力不可にする
    		NoEditText();
    		SettingTime();
    		if(Locale.getDefault().toString().startsWith("ja"))
    			Toast.makeText(this, "LP回復10分前に通知します", Toast.LENGTH_SHORT).show();
    		else
    			Toast.makeText(this, "10 minute before notification.", Toast.LENGTH_SHORT).show();
        }
        if(spi==3){
        	SharedPreferences DefPref = PreferenceManager.getDefaultSharedPreferences(this);
        	int customlefttime = DefPref.getInt("customLeftTime", 0);
        	LeftLPTime = LeftLPTime - customlefttime;
        	//呼び出す日時の設定
    		Calendar triggerTime = Calendar.getInstance();
    		triggerTime.add(Calendar.MINUTE, LeftLPTime);
    		
    		Calendar cal = Calendar.getInstance();
    		cal.add(Calendar.MINUTE, LeftLPTime);
 
    		MONTH = cal.get(Calendar.MONTH) + 1;
    		DATE = cal.get(Calendar.DATE);
    		HOUR_OF_DAY = cal.get(Calendar.HOUR_OF_DAY);
    		HOUR = cal.get(Calendar.HOUR);
    		MINUTE = cal.get(Calendar.MINUTE);
    		SECOND = cal.get(Calendar.SECOND);
    		ampm = cal.get(Calendar.AM_PM);
    		
    		SetResultTime();
    		startCountDown();
    		
    		//設定した日時で発行するIntent生成
    		Intent intent = new Intent(this, Notifier04.class);
    		PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    				
    		//日時と発行するIntentをAlarmManagerにセット
    		AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
    		manager.set(AlarmManager.RTC_WAKEUP, triggerTime.getTimeInMillis(), sender);
    		
    		//EditTextを入力不可にする
    		NoEditText();
    		SettingTime();
    		if(Locale.getDefault().toString().startsWith("ja"))
    			Toast.makeText(this, "LP回復" + customlefttime + "分前に通知します", Toast.LENGTH_SHORT).show();
    		else
    			Toast.makeText(this, customlefttime + " minute before notification.", Toast.LENGTH_SHORT).show();
        }
        }
		}catch (Exception e){
			Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
			stop(null);
		}
		saveLP();
	}
	
	//アラームキャンセル
	public void stop(View v){
		cancel1();
		cancel2();
		cancel3();
		cancel4();
		//CDT関連
		cancelCountDown();
		
		//プリファレンスの準備
		SharedPreferences pref2 = getSharedPreferences("saveTIME", MODE_PRIVATE);
		//全削除
		pref2.edit().clear().commit();
				
		TextView time = (TextView)findViewById(R.id.textView3);
		time.setText("");
		//EditTextを入力可能にする
		YesEditText();
		
		if(Locale.getDefault().toString().startsWith("ja"))
			Toast.makeText(this, "キャンセルしました", Toast.LENGTH_SHORT).show();
		else
			Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
	}
	
	public void cancel1(){
		AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
		Intent intent = new Intent(this, Notifier.class);
		PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		manager.cancel(sender);
	}
	
	public void cancel2(){
		AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
		Intent intent = new Intent(this, Notifier02.class);
		PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		manager.cancel(sender);
	}
	
	public void cancel3(){
		AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
		Intent intent = new Intent(this, Notifier03.class);
		PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		manager.cancel(sender);
	}
	
	public void cancel4(){
		AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
		Intent intent = new Intent(this, Notifier04.class);
		PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		manager.cancel(sender);
	}
	
	public void check(View v){
		//オブジェクトの取得
		EditText MaxLP = (EditText)findViewById(R.id.MaxLP);
		EditText NowLP = (EditText)findViewById(R.id.NowLP);
		TextView time = (TextView)findViewById(R.id.textView3);
		
		try{
		//文字から数値に変換
		String maxLP = MaxLP.getText().toString();
		String nowLP = NowLP.getText().toString();
		
		int maxlp = Integer.parseInt(maxLP);
		int nowlp = Integer.parseInt(nowLP);
		
		//maxからnowを引いて残りのLPを算出
		int LeftLP = maxlp - nowlp;
		
		//残りのLPに1回復の6分を掛ける
		int LeftLPTime = LeftLP * 6;
		
		Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.MINUTE, LeftLPTime);
	    
	    SharedPreferences ampm = PreferenceManager.getDefaultSharedPreferences(this);
		Boolean AMPM = ampm.getBoolean("AMPM", false);
		
		String tmp;
		if(AMPM == true){
			String am_pm = null;
			if(Locale.getDefault().toString().startsWith("ja")){
			switch(cal.get(Calendar.AM_PM)){
			case 0:
				am_pm = "午前";
				break;
			case 1:
				am_pm = "午後";
				break;
			}
			}else{
				switch(cal.get(Calendar.AM_PM)){
				case 0:
					am_pm = "AM";
					break;
				case 1:
					am_pm = "PM";
					break;
				}
			}
			if(Locale.getDefault().toString().startsWith("ja")){
		tmp = "目標LP回復時刻は" + "\n" + "　　" + (cal.get(Calendar.MONTH) + 1) + "月" + cal.get(Calendar.DATE)
	            + "日" + am_pm + cal.get(Calendar.HOUR) + "時"
	            + cal.get(Calendar.MINUTE) + "分";
			}else{
				tmp = "The goal LP recovery time" + "\n" + "　　" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE)
			            + " " + cal.get(Calendar.HOUR) + ":"
			            + cal.get(Calendar.MINUTE) + am_pm;
			}
		}else{
			if(Locale.getDefault().toString().startsWith("ja")){
		tmp = "目標LP回復時刻は" + "\n" + "　　" + (cal.get(Calendar.MONTH) + 1) + "月" + cal.get(Calendar.DATE)
	            + "日" + cal.get(Calendar.HOUR_OF_DAY) + "時"
	            + cal.get(Calendar.MINUTE) + "分";
			}else{
				tmp = "The goal LP recovery time" + "\n" + "　　" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE)
			            + " " + cal.get(Calendar.HOUR_OF_DAY) + ":"
			            + cal.get(Calendar.MINUTE);
			}
		}
	    time.setText(tmp);
	    
	    //残り時間
	    Calendar cal2 = Calendar.getInstance();
	  	int nowDate = cal2.get(Calendar.DATE);
	 	int nowHourOfDay = cal2.get(Calendar.HOUR_OF_DAY);
	  	int nowMinute = cal2.get(Calendar.MINUTE);
	  	
	  	cal2.set(Calendar.DATE, cal.get(Calendar.DATE));
	  	cal2.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY));
	  	cal2.set(Calendar.MINUTE, cal.get(Calendar.MINUTE));
	  	
	  	cal2.add(Calendar.DATE, -nowDate);
	  	cal2.add(Calendar.HOUR_OF_DAY, -nowHourOfDay);
	  	cal2.add(Calendar.MINUTE, -nowMinute);
	  	
	  	int date, hour, min;
	  	String HOUR, MIN;
	  	date = cal2.get(Calendar.DATE);
	  	hour = cal2.get(Calendar.HOUR_OF_DAY);
	  	min = cal2.get(Calendar.MINUTE);
	  	if(date >= 10)
	  		date = 0;
	  	if(hour <= 9)
	  		HOUR = "0" + hour;
	  	else
	  		HOUR = String.valueOf(hour);
	  	if(min <= 9)
	  		MIN = "0" + min;
	  	else
	  		MIN = String.valueOf(min);
	  	
	  	Resources res = getResources();
	  	TextView left = (TextView)findViewById(R.id.textView1);
	  	if(date == 0)
	  		left.setText(res.getString(R.string.remaining_time) + HOUR + ":" + MIN + ":00");
	  	else
	  		left.setText(res.getString(R.string.remaining_time) + date + "日" + HOUR + ":" + MIN + ":00");
	  	
		}catch (Exception e){
			Toast.makeText(getApplicationContext(), "ん？", Toast.LENGTH_SHORT).show();
		}
	}
	
	
	public void saveLP(){
		EditText MaxLP = (EditText)findViewById(R.id.MaxLP);
		EditText NowLP = (EditText)findViewById(R.id.NowLP);
		String maxLP = MaxLP.getText().toString();
		String nowLP = NowLP.getText().toString();
		//プリファレンスの準備
		SharedPreferences pref = getSharedPreferences("saveLP", MODE_PRIVATE);
		//Editorオブジェクト取得
		Editor editor = pref.edit();
		//maxLPというキーで最大LPを登録
		editor.putString("maxLP", maxLP);
		//nowLPというキーで現在LPを登録
		editor.putString("nowLP", nowLP);
		//書き込み
		editor.commit();
	}
	public void loadLP(){
		EditText MaxLP = (EditText)findViewById(R.id.MaxLP);
		EditText NowLP = (EditText)findViewById(R.id.NowLP);
		//プリファレンスの準備
		SharedPreferences pref = getSharedPreferences("saveLP", MODE_PRIVATE);
		//maxLPというキーで保存されている値を読み出す
		String MAX = pref.getString("maxLP", "");
		//nowLPというキーで保存されている値を読み出す
		String NOW = pref.getString("nowLP", "");
		
		MaxLP.setText(MAX);
		NowLP.setText(NOW);
	}
	
	public void onResume(){
		super.onResume();
		TextView result = (TextView)findViewById(R.id.textView3);
		SharedPreferences DefPref = PreferenceManager.getDefaultSharedPreferences(this);
		SharedPreferences saveTime = getSharedPreferences("saveTIME", MODE_PRIVATE);
		boolean ampm = DefPref.getBoolean("AMPM", false);
		Spinner spinner = (Spinner)findViewById(R.id.spinner1);
		int spi = spinner.getSelectedItemPosition();
		String spi0, spi0ampm, spi1, spi1ampm;
		spi0 = saveTime.getString("spi0", "");
		spi0ampm = saveTime.getString("spi0ampm", "");
		spi1 = saveTime.getString("spi1", "");
		spi1ampm = saveTime.getString("spi1ampm", "");
		if(spi == 0){
			if(ampm == false)
				result.setText(spi0);
			else
				result.setText(spi0ampm);
		}else{
			if(ampm == false)
				result.setText(spi1);
			else
				result.setText(spi1ampm);
		}
	}
	
	public void clear(View v){
		//キャンセルする（stopメソッド）
		stop(null);
		//プリファレンスの削除
		//プリファレンスの準備
		SharedPreferences pref = getSharedPreferences("saveLP", MODE_PRIVATE);
		//maxLPのみ削除
		pref.edit().remove("nowLP").commit();
		//プリファレンスの準備
		SharedPreferences pref2 = getSharedPreferences("saveTIME", MODE_PRIVATE);
		//全削除
		pref2.edit().clear().commit();
		
		//オブジェクトの取得
		EditText NowLP = (EditText)findViewById(R.id.NowLP);
		TextView time = (TextView)findViewById(R.id.textView3);
		
		//EditTextを入力可能にする
		YesEditText();
		
		//EditText1へフォーカスを移動
		NowLP.requestFocus();
		
		//キーボードを表示（フォーカス移動のみではキーボードは表示されない）
		InputMethodManager manager =
				(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
				manager.toggleSoftInput(1, InputMethodManager.SHOW_IMPLICIT);
		
		//空にする
		NowLP.setText("");
		time.setText("");
	}
	
	public void NoEditText(){
		EditText MaxLP = (EditText)findViewById(R.id.MaxLP);
		EditText NowLP = (EditText)findViewById(R.id.NowLP);
		Button start = (Button)findViewById(R.id.button1);
		Button check = (Button)findViewById(R.id.button3);
		Spinner spi = (Spinner)findViewById(R.id.spinner1);
		MaxLP.setEnabled(false);
		NowLP.setEnabled(false);
		MaxLP.setFocusable(false);
		NowLP.setFocusable(false);
		start.setEnabled(false);
		check.setEnabled(false);
		spi.setEnabled(false);
		
		int SpiItem = spi.getSelectedItemPosition();
		
		//プリファレンスの準備
		SharedPreferences pref = getSharedPreferences("saveEditText", MODE_PRIVATE);
		//Editorオブジェクト取得
		Editor editor = pref.edit()
		.putBoolean("MaxLPEnable", false)
		.putBoolean("NowLPEnable", false)
		.putBoolean("MaxLPFocus", false)
		.putBoolean("NowLPFocus", false)
		.putBoolean("start", false)
		.putBoolean("check", false)
		.putBoolean("spinner", false)
		.putInt("SpinnerItem", SpiItem);
		//書き込み
		editor.commit();
	}
	public void YesEditText(){
		EditText MaxLP = (EditText)findViewById(R.id.MaxLP);
		EditText NowLP = (EditText)findViewById(R.id.NowLP);
		Button start = (Button)findViewById(R.id.button1);
		Button check = (Button)findViewById(R.id.button3);
		Spinner spi = (Spinner) findViewById(R.id.spinner1);
		MaxLP.setEnabled(true);
		NowLP.setEnabled(true);
		MaxLP.setFocusable(true);
		NowLP.setFocusable(true);
		MaxLP.setFocusableInTouchMode(true);
		NowLP.setFocusableInTouchMode(true);
		start.setEnabled(true);
		check.setEnabled(true);
		spi.setEnabled(true);
		
		//プリファレンスの準備
		SharedPreferences pref = getSharedPreferences("saveEditText", MODE_PRIVATE);
		//Editorオブジェクト取得
		Editor editor = pref.edit()
		.putBoolean("MaxLPEnable", true)
		.putBoolean("NowLPEnable", true)
		.putBoolean("MaxLPFocus", true)
		.putBoolean("NowLPFocus", true)
		.putBoolean("start", true)
		.putBoolean("check", true)
		.putBoolean("spinner", true);
		//書き込み
		editor.commit();
	}
	public void loadEditTextBoolean(){
		EditText MaxLP = (EditText)findViewById(R.id.MaxLP);
		EditText NowLP = (EditText)findViewById(R.id.NowLP);
		Button Start = (Button)findViewById(R.id.button1);
		Button Check = (Button)findViewById(R.id.button3);
		Spinner spi = (Spinner)findViewById(R.id.spinner1);
		//プリファレンスの準備
		SharedPreferences pref = getSharedPreferences("saveEditText", MODE_PRIVATE);
		//()というキーで保存されている値を読み出す
		Boolean MaxEna = pref.getBoolean("MaxLPEnable", true);
		Boolean NowEna = pref.getBoolean("NowLPEnable", true);
		Boolean MaxFocus = pref.getBoolean("MaxLPFocus", true);
		Boolean NowFocus = pref.getBoolean("NowLPFocus", true);
		Boolean StartEna = pref.getBoolean("start", true);
		Boolean CheckEna = pref.getBoolean("check", true);
		Boolean Spinner = pref.getBoolean("spinner", true);
		int SpinnerItem = pref.getInt("SpinnerItem", 0);
				
		MaxLP.setEnabled(MaxEna);
		NowLP.setEnabled(NowEna);
		MaxLP.setFocusable(MaxFocus);
		NowLP.setFocusable(NowFocus);
		Start.setEnabled(StartEna);
		Check.setEnabled(CheckEna);
		spi.setEnabled(Spinner);
		spi.setSelection(SpinnerItem);
	}

	public void NotificationEnable(){
		SharedPreferences NotiPref = PreferenceManager.getDefaultSharedPreferences(this);
		boolean NotiEnable = NotiPref.getBoolean("NotiEnable", false);
		if(NotiEnable == true){
		YesEditText();
		
		//trueだったものをfalseに書き換える
		Editor edit = NotiPref.edit()
				.putBoolean("NotiEnable", false);
		edit.commit();
		//countdowntimerストップ
		SharedPreferences saveCalendar = getSharedPreferences("saveCalendar", MODE_PRIVATE);
		saveCalendar.edit().putBoolean("CountDown", false).commit();
		}
	}
	
	public void loadHideFunction(){
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
		boolean HideFunction = pref.getBoolean("HideFunction", false);
		Spinner spi = (Spinner)findViewById(R.id.spinner1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		if(HideFunction == true){
			int custom = pref.getInt("customLeftTime", 0);
			String CUSTOM = String.valueOf(custom);
			Resources res = getResources();
			adapter.add(res.getString(R.string.full_notice));
			adapter.add("5 " + res.getString(R.string.minutes_before_notice));
			adapter.add("10 " + res.getString(R.string.minutes_before_notice));
			adapter.add(CUSTOM + " " + res.getString(R.string.minutes_before_notice));
			spi.setAdapter(adapter);
		}else{
			Resources res = getResources();
			adapter.add(res.getString(R.string.full_notice));
			adapter.add("5 " + res.getString(R.string.minutes_before_notice));
			adapter.add("10 " + res.getString(R.string.minutes_before_notice));
			spi.setAdapter(adapter);
		}
	}
	
	public void SetResultTime(){
		TextView Result = (TextView)findViewById(R.id.textView3);
		Spinner spinner = (Spinner)findViewById(R.id.spinner1);
		int spi = spinner.getSelectedItemPosition();
		String am_pm = null;
		if(Locale.getDefault().toString().startsWith("ja")){
		switch(ampm){
		case 0:
			am_pm = "午前";
			break;
		case 1:
			am_pm = "午後";
			break;
		}
		}else{
			switch(ampm){
			case 0:
				am_pm = "AM";
				break;
			case 1:
				am_pm = "PM";
				break;
			}
		}
		
		SharedPreferences ampm = PreferenceManager.getDefaultSharedPreferences(this);
		SharedPreferences saveTime = getSharedPreferences("saveTIME", MODE_PRIVATE);
		boolean AMPM = ampm.getBoolean("AMPM", false);
		String spi0 = null, spi0ampm = null, spi1 = null, spi1ampm = null;
		if(Locale.getDefault().toString().startsWith("ja")){
		spi0 = "目標LP回復時刻は" + "\n" + "　　" + MONTH + "月" + DATE
				+ "日" + HOUR_OF_DAY + "時"
				+ MINUTE + "分";
		spi0ampm = "目標LP回復時刻は" + "\n" + "　　" + MONTH + "月" + DATE
				+ "日" + am_pm + HOUR + "時"
				+ MINUTE + "分";
		spi1 = "目標LP回復通知時刻は" + "\n" + "　　" + MONTH + "月" + DATE
				+ "日" + HOUR_OF_DAY + "時"
				+ MINUTE + "分";
		spi1ampm = "目標LP回復通知時刻は" + "\n" + "　　" + MONTH + "月" + DATE
				+ "日" + am_pm + HOUR + "時"
				+ MINUTE + "分";
		}else{
			spi0 = "The goal LP recovery time" + "\n" + "　　" + MONTH + "/" + DATE
					+ " " + HOUR_OF_DAY + ":"
					+ MINUTE;
			spi0ampm = "The goal LP recovery time" + "\n" + "　　" + MONTH + "/" + DATE
					+ " " + HOUR + ":"
					+ MINUTE + am_pm;
			spi1 = "The goal LP recovery notice time" + "\n" + "　　" + MONTH + "/" + DATE
					+ " " + HOUR_OF_DAY + ":"
					+ MINUTE;
			spi1ampm = "The goal LP recovery notice time" + "\n" + "　　" + MONTH + "/" + DATE
					+ " " + HOUR + ":"
					+ MINUTE + am_pm;
		}
		saveTime.edit().putString("spi0", spi0).putString("spi0ampm", spi0ampm).putString("spi1", spi1)
		.putString("spi1ampm", spi1ampm).commit();
		if(spi == 0){
			if(AMPM == false){
				Result.setText(spi0);
			}else{
				Result.setText(spi0ampm);
			}
		}else{
			if(AMPM == false){
				Result.setText(spi1);
			}else{
				Result.setText(spi1ampm);
			}
		}
		SharedPreferences saveCalendar = getSharedPreferences("saveCalendar", MODE_PRIVATE);
		saveCalendar.edit()
		.putInt("DATE", DATE).putInt("HOUR_OF_DAY", HOUR_OF_DAY).putInt("MINUTE", MINUTE)
		.putInt("SECOND", SECOND).putBoolean("CountDown", true).commit();
	}
	
	public void Once(){
		final SharedPreferences DefPre = PreferenceManager.getDefaultSharedPreferences(this);
		int VersionCode = DefPre.getInt("VersionCode", 18);
		if(VersionCode <= 18){
			if(Locale.getDefault().toString().startsWith("ja")){
			AlertDialog.Builder builder = new AlertDialog.Builder(this)
			.setTitle("アップデート！")
			.setMessage("あけましておめでとうございます！！\n今年もよろしくお願い致します。"
					+ "\n\n・一部の端末で画面のレイアウトが崩れる問題を解消"
					+ "\n・「ご協力お願いします」項目の削除"
					+ "\n・「よくあるかもしれない質問」の文章を少し変更"
					+ "\n・隠し機能の英語版の実装"
					+ "\n\nTwitterでレイアウトの改善に協力して頂いた方、ほんとうにありがとうございました。")
			.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					DefPre.edit().putInt("VersionCode", 19).commit();
				}
			});
			builder.create().show();
			}else{
				AlertDialog.Builder builder = new AlertDialog.Builder(this)
				.setTitle("Update!")
				.setMessage("Fixed : Layout bug that occurred in the part of the terminal."
						+ "\n\n\"Hide Function\", Implementation!"
						+ "\nPlease enter the \"UUDDLRLRBA\" to comment after pressing the \"Share this app with Twitter\".")
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						DefPre.edit().putInt("VersionCode", 19).commit();
					}
				});
				builder.create().show();
			}
		}
	}
	
	public void LeftTime(){
		SharedPreferences saveCalendar = getSharedPreferences("saveCalendar", MODE_PRIVATE);
		int saveDate = saveCalendar.getInt("DATE", 0);
		int saveHourOfDay = saveCalendar.getInt("HOUR_OF_DAY", 0);
		int saveMinute = saveCalendar.getInt("MINUTE", 0);
		int saveSecond = saveCalendar.getInt("SECOND", 0);
		boolean CountDown = saveCalendar.getBoolean("CountDown", false);
		
		if(CountDown == true){
		
		Calendar cal = Calendar.getInstance();
		int nowDate = cal.get(Calendar.DATE);
		int nowHourOfDay = cal.get(Calendar.HOUR_OF_DAY);
		int nowMinute = cal.get(Calendar.MINUTE);
		int nowSecond = cal.get(Calendar.SECOND);
		
		cal.set(Calendar.DATE, saveDate);
		cal.set(Calendar.HOUR_OF_DAY, saveHourOfDay);
		cal.set(Calendar.MINUTE, saveMinute);
		cal.set(Calendar.SECOND, saveSecond);
		
		cal.add(Calendar.DATE, -nowDate);
		cal.add(Calendar.HOUR_OF_DAY, -nowHourOfDay);
		cal.add(Calendar.MINUTE, -nowMinute);
		cal.add(Calendar.SECOND, -nowSecond);
		
		int date = cal.get(Calendar.DATE);
		int hourofday = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		
		if(date >= 10)
			date = 0;
		LeftLPSec = 1000 * (second + minute * 60 + hourofday * 3600 + date * 86400);
		}
	}
	
	public class MyCountDownTimer extends CountDownTimer{
		public MyCountDownTimer(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}
		TextView LeftTime = (TextView)findViewById(R.id.textView1);
		@Override
		public void onTick(long millisUntilFinished) {
			Resources res = getResources();
			Long hour = millisUntilFinished/1000/3600;
			Long minute = millisUntilFinished/1000%3600/60;
			Long second = millisUntilFinished/1000%60;
			String Min = null;
			String Sec = null;
			if(minute <= 9)
				Min = "0" + minute;
			else
				Min = String.valueOf(minute);
			if(second <= 9)
				Sec = "0" + second;
			else
				Sec = String.valueOf(second);
			if(hour == 0)
				LeftTime.setText(res.getString(R.string.remaining_time) + Min + ":" + Sec);
			else
				LeftTime.setText(res.getString(R.string.remaining_time) + hour + ":" + Min + ":" + Sec);
		}
		@Override
		public void onFinish() {
			LeftTime.setText("");
		}
	}
	
	public void startCountDown(){
		LeftTime();
		cdt = new MyCountDownTimer(LeftLPSec, 500);
		cdt.start();
	}
	public void cancelCountDown(){
		cdt.cancel();
		SharedPreferences saveCalendar = getSharedPreferences("saveCalendar", MODE_PRIVATE);
		saveCalendar.edit().putBoolean("CountDown", false).commit();
		TextView LeftTime = (TextView)findViewById(R.id.textView1);
		LeftTime.setText("");
	}
	
	public void SettingTime(){
		SharedPreferences SettingTime = getSharedPreferences("SettingTime", MODE_PRIVATE);
		Calendar cal = Calendar.getInstance();
		SettingTime.edit().putInt("Setting_DATE", cal.get(Calendar.DATE))
		.putInt("Setting_HOUR_OF_DAY", cal.get(Calendar.HOUR_OF_DAY))
		.putInt("Setting_MINUTE", cal.get(Calendar.MINUTE)).commit();
	}
	
	public void LPhealCalc(){
		SharedPreferences saveEditText = getSharedPreferences("saveEditText", MODE_PRIVATE);
		if(saveEditText.getBoolean("start", true) == false){
		SharedPreferences SettingTime = getSharedPreferences("SettingTime", MODE_PRIVATE);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -SettingTime.getInt("Setting_DATE", 0));
		cal.add(Calendar.HOUR_OF_DAY, -SettingTime.getInt("Setting_HOUR_OF_DAY", 0));
		cal.add(Calendar.MINUTE, -SettingTime.getInt("Setting_MINUTE", 0));
		int result_DATE = cal.get(Calendar.DATE);
		int result_HOUR_OF_DAY = cal.get(Calendar.HOUR_OF_DAY);
		int result_MINUTE = cal.get(Calendar.MINUTE);
		if(result_DATE > 10)
			result_DATE = 0;
		int FinalMINUTE = result_MINUTE + result_HOUR_OF_DAY * 60 + result_DATE * 1440;
		EditText nowLP = (EditText)findViewById(R.id.NowLP);
		int TotalLP = FinalMINUTE / 6 + Integer.parseInt(nowLP.getText().toString());
		nowLP.setText(String.valueOf(TotalLP));
		}
	}
	
	public void background(View v){
		//背景タップでキーボードを隠す
		InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
	}
	
	//アナリティクスstop
	public void onStop(){
		super.onStop();
		EasyTracker.getInstance(this).activityStop(this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.optionsmenu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()){
		case R.id.change:
			Intent Change = new Intent(this, Update.class);
			startActivity(Change);
			return true;
		case R.id.settings:
			Intent Settings = new Intent(this, MenuActivity.class);
			startActivity(Settings);
			return true;
		}
		return false;
	}
}
