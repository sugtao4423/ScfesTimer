package com.tao.scfestimer;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
		loadLP();
		loadTIME();
		loadEditTextBoolean();
		NotificationEnable();
	}
	public int LeftLPTime;

	//アラーム開始
	public void start(View v){
		
		//オブジェクトの取得
		EditText MaxLP = (EditText)findViewById(R.id.MaxLP);
		EditText NowLP = (EditText)findViewById(R.id.NowLP);
		Spinner spinner = (Spinner)findViewById(R.id.spinner1);
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
		
		// 選択されているアイテムのIndexを取得
        int spi = spinner.getSelectedItemPosition();
        
        //if(目標LP≦現在LP)
        if(maxlp <= nowlp){
        	Toast.makeText(getApplicationContext(), "もう回復してるんですがそれは...", Toast.LENGTH_SHORT).show();
        }else{
        
        if(spi==0){
        	//呼び出す日時の設定
    		Calendar triggerTime = Calendar.getInstance();
    		triggerTime.add(Calendar.MINUTE, LeftLPTime);
    		
    		Calendar cal = Calendar.getInstance();
    		cal.add(Calendar.MINUTE, LeftLPTime);
    		String tmp = "目標LP回復時刻は" + "\n" + "　　" + (cal.get(Calendar.MONTH) + 1) + "月" + cal.get(Calendar.DATE)
    	            + "日" + cal.get(Calendar.HOUR_OF_DAY) + "時"
    	            + cal.get(Calendar.MINUTE) + "分";
    		time.setText(tmp);
    		
    		//プリファレンスに保存
    		//プリファレンスの準備
    		SharedPreferences pref = getSharedPreferences("saveTIME", MODE_PRIVATE);
    		//Editorオブジェクト取得
    		Editor editor = pref.edit();
    		//TIMEというキーで最大LPを登録
    		editor.putString("TIME", tmp);
    		//書き込み
    		editor.commit();
    		
    		//設定した日時で発行するIntent生成
    		Intent intent = new Intent(this, Notifier.class);
    		PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

    		//日時と発行するIntentをAlarmManagerにセット
    		AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
    		manager.set(AlarmManager.RTC_WAKEUP, triggerTime.getTimeInMillis(), sender);
       		
    		//EditTextを入力不可にする
    		NoEditText();
    		//設定項目を変えさせない
    		DisableCheckBox();
    		    		
    		Toast.makeText(getApplicationContext(), "LP回復時に通知します", Toast.LENGTH_SHORT).show();
        }
        if(spi==1){
        	LeftLPTime = LeftLPTime - 5;
        	//呼び出す日時の設定
    		Calendar triggerTime = Calendar.getInstance();
    		triggerTime.add(Calendar.MINUTE, LeftLPTime);
    		
    		Calendar cal = Calendar.getInstance();
    		cal.add(Calendar.MINUTE, LeftLPTime);
    		String tmp = "目標LP回復通知時刻は" + "\n" + "　　" + (cal.get(Calendar.MONTH) + 1) + "月" + cal.get(Calendar.DATE)
    	            + "日" + cal.get(Calendar.HOUR_OF_DAY) + "時"
    	            + cal.get(Calendar.MINUTE) + "分";
    		time.setText(tmp);
    		
    		//プリファレンスに保存
    		//プリファレンスの準備
    		SharedPreferences pref = getSharedPreferences("saveTIME", MODE_PRIVATE);
    		//Editorオブジェクト取得
    		Editor editor = pref.edit();
    		//TIMEというキーで最大LPを登録
    		editor.putString("TIME", tmp);
    		//書き込み
    		editor.commit();
    		
    		//設定した日時で発行するIntent生成
    		Intent intent = new Intent(this, Notifier02.class);
    		PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    				
    		//日時と発行するIntentをAlarmManagerにセット
    		AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
    		manager.set(AlarmManager.RTC_WAKEUP, triggerTime.getTimeInMillis(), sender);
    		
    		//EditTextを入力不可にする
    		NoEditText();
    		//設定項目を変えさせない
    		DisableCheckBox();
    		
    		Toast.makeText(getApplicationContext(), "LP回復5分前に通知します", Toast.LENGTH_SHORT).show();
        }
        if(spi==2){
        	LeftLPTime = LeftLPTime - 10;
        	//呼び出す日時の設定
    		Calendar triggerTime = Calendar.getInstance();
    		triggerTime.add(Calendar.MINUTE, LeftLPTime);
    		
    		Calendar cal = Calendar.getInstance();
    		cal.add(Calendar.MINUTE, LeftLPTime);
    		String tmp = "目標LP回復通知時刻は" + "\n" + "　　" + (cal.get(Calendar.MONTH) + 1) + "月" + cal.get(Calendar.DATE)
    	            + "日" + cal.get(Calendar.HOUR_OF_DAY) + "時"
    	            + cal.get(Calendar.MINUTE) + "分";
    		time.setText(tmp);
    		
    		//プリファレンスに保存
    		//プリファレンスの準備
    		SharedPreferences pref = getSharedPreferences("saveTIME", MODE_PRIVATE);
    		//Editorオブジェクト取得
    		Editor editor = pref.edit();
    		//TIMEというキーで最大LPを登録
    		editor.putString("TIME", tmp);
    		//書き込み
    		editor.commit();
    		
    		//設定した日時で発行するIntent生成
    		Intent intent = new Intent(this, Notifier03.class);
    		PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    				
    		//日時と発行するIntentをAlarmManagerにセット
    		AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
    		manager.set(AlarmManager.RTC_WAKEUP, triggerTime.getTimeInMillis(), sender);
    		
    		//EditTextを入力不可にする
    		NoEditText();
    		//設定項目を変えさせない
    		DisableCheckBox();
    		
    		Toast.makeText(getApplicationContext(), "LP回復10分前に通知します", Toast.LENGTH_SHORT).show();
        }
        }
		}catch (Exception e){
			Toast.makeText(getApplicationContext(), "ん？", Toast.LENGTH_SHORT).show();
			stop(null);
		}
		saveLP();
	}
	
	//アラームキャンセル
	public void stop(View v){
		cancel1();
		cancel2();
		cancel3();
		
		//プリファレンスの準備
		SharedPreferences pref2 = getSharedPreferences("saveTIME", MODE_PRIVATE);
		//全削除
		pref2.edit().clear().commit();
				
		TextView time = (TextView)findViewById(R.id.textView3);
		time.setText("");
		//EditTextを入力可能にする
		YesEditText();
		//設定項目変更OK
		EnableCheckBox();
		Toast.makeText(getApplicationContext(), "キャンセルしました", Toast.LENGTH_SHORT).show();
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
	    String tmp = "目標LP回復時刻は" + "\n" + "　　" + (cal.get(Calendar.MONTH) + 1) + "月" + cal.get(Calendar.DATE)
	               + "日" + cal.get(Calendar.HOUR_OF_DAY) + "時" + cal.get(Calendar.MINUTE) + "分";
	    time.setText(tmp);
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
	
	
	public void loadTIME(){
		TextView time = (TextView)findViewById(R.id.textView3);
		//プリファレンスの準備
		SharedPreferences pref = getSharedPreferences("saveTIME", MODE_PRIVATE);
		//TIMEというキーで保存されている値を読み出す
		String Time = pref.getString("TIME", "");
		time.setText(Time);
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
		//設定項目変更OK
		EnableCheckBox();
		
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
		String SpinnerItem = String.valueOf(SpiItem);
		
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
		.putString("SpinnerItem", SpinnerItem);
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
		String SpinnerItem = pref.getString("SpinnerItem", "0");
		
		int SPINNERITEM = Integer.valueOf(SpinnerItem);
		
		MaxLP.setEnabled(MaxEna);
		NowLP.setEnabled(NowEna);
		MaxLP.setFocusable(MaxFocus);
		NowLP.setFocusable(NowFocus);
		Start.setEnabled(StartEna);
		Check.setEnabled(CheckEna);
		spi.setEnabled(Spinner);
		spi.setSelection(SPINNERITEM);
	}
	
	public void DisableCheckBox(){
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
		Editor edit = pref.edit()
		.putBoolean("DisCheckBox", false);
		edit.commit();
	}
	public void EnableCheckBox(){
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
		Editor edit = pref.edit()
		.putBoolean("DisCheckBox", true);
		edit.commit();
	}

	public void NotificationEnable(){
		SharedPreferences NotiPref = PreferenceManager.getDefaultSharedPreferences(this);
		boolean NotiEnable = NotiPref.getBoolean("NotiEnable", false);
		if(NotiEnable == true){
		YesEditText();
		EnableCheckBox();
		
		//trueだったものをfalseに書き換える
		Editor edit = NotiPref.edit()
				.putBoolean("NotiEnable", false);
		edit.commit();
		}
	}
	
	public void Once(){
		//デフォPreference
		SharedPreferences DefPre = PreferenceManager.getDefaultSharedPreferences(this);
		//saveEditText
		SharedPreferences saveEditText = getSharedPreferences("saveEditText", MODE_PRIVATE);
		Boolean Once = DefPre.getBoolean("OneStart", false);
		if(Once == false){
			saveEditText.edit().clear().commit();
			DefPre.edit().putBoolean("OneStart", true).commit();
		}
	}
	
	
	
	
	public void background(View v){
		//背景タップでキーボードを隠す
		InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
	}
	
	private final int Menu1 = Menu.FIRST;
	private final int Menu2 = Menu.FIRST + 1;
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		menu.add(Menu.NONE, Menu1, Menu.NONE, "設定");
		menu.add(Menu.NONE, Menu2, Menu.NONE, "よくあるかもしれない質問など");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()){
		case Menu1:
			Intent menu = new Intent(this, MenuActivity.class);
			startActivity(menu);
			break;
		case Menu2:
			Intent menu2 = new Intent(this, FAQ.class);
			startActivity(menu2);
			return true;
		}
		return false;
	}
}
