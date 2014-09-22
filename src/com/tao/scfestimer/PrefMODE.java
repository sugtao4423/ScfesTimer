package com.tao.scfestimer;

import com.google.analytics.tracking.android.EasyTracker;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class PrefMODE extends Activity {

	private String PreText;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pref_mode);
		//アナリティクスstart
		EasyTracker.getInstance(this).activityStart(this);
		
		final ListView listview = (ListView)findViewById(R.id.ListView);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				
				String item = (String) listview.getItemAtPosition(position);
				if(item.matches(".*scfestimer.*")){
					setTitle("com.tao.scfestimer_preferences.xml");
					SharedPreferences Default = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
					Boolean sound_preference = Default.getBoolean("sound_preference", true);
					Boolean vibrate_preference = Default.getBoolean("vibrate_preference", true);
					Boolean led_preference = Default.getBoolean("led_preference", true);
					Boolean DisCheckBox = Default.getBoolean("DisCheckBox", true);
					Boolean NotiEnable = Default.getBoolean("NotiEnable", false);
					String share = Default.getString("share", "");
					Boolean OneStart = Default.getBoolean("OneStart", true);

					PreText = "<?xml version='1.0' encoding='utf-8' standalone='yes' ?>\n<map>\n"
							+ "     " + "<boolean name=\"sound_preference\" value=\"" + sound_preference + "\" />\n" +
							"     " + "<boolean name=\"vibrate_preference\" value=\"" + vibrate_preference + "\" />\n" +
							"     " + "<boolean name=\"led_preference\" value=\"" + led_preference + "\" />\n" +
							"     " + "<boolean name=\"DisCheckBox\" value=\"" + DisCheckBox + "\" />\n" +
							"     " + "<boolean name=\"NotiEnable\" value=\"" + NotiEnable + "\" />\n" +
							"     " + "<string name=\"share\">" + share + "</string>\n" +
							"     " + "<boolean name=\"OneStart\" value=\"" + OneStart + "\" />\n" + "</map>";
					contentview();
					toast();
				}
				if(item.matches(".*EditText.*")){
					setTitle("saveEdtiText.xml");
					SharedPreferences EditText = getSharedPreferences("saveEditText", MODE_PRIVATE);
					Boolean spinner = EditText.getBoolean("spinner", true);
					Boolean check = EditText.getBoolean("check", true);
					Boolean start = EditText.getBoolean("start", true);
					Boolean MaxLPEnable = EditText.getBoolean("MaxLPEnable", true);
					Boolean NowLPEnable = EditText.getBoolean("NowLPEnable", true);
					Boolean MaxLPFocus = EditText.getBoolean("MaxLPFocus", true);
					Boolean NowLPFocus = EditText.getBoolean("NowLPFocus", true);
					String SpinnerItem = EditText.getString("SpinnerItem", "");
					
					PreText = "<?xml version='1.0' encoding='utf-8' standalone='yes' ?>\n<map>\n"
							+ "     " + "<boolean name=\"spinner\" value=\"" + spinner +  "\" />\n" +
							"     " + "<boolean name=\"check\" value=\"" + check +  "\" />\n" +
							"     " + "<boolean name=\"start\" value=\"" + start +  "\" />\n" +
							"     " + "<boolean name=\"MaxLPEnable\" value=\"" + MaxLPEnable +  "\" />\n" +
							"     " + "<boolean name=\"NowLPEnable\" value=\"" + NowLPEnable +  "\" />\n" +
							"     " + "<boolean name=\"MaxLPFocus\" value=\"" + MaxLPFocus +  "\" />\n" +
							"     " + "<boolean name=\"NowLPFocus\" value=\"" + NowLPFocus +  "\" />\n" + 
							"     " + "<string name=\"SpinnerItem\">" + SpinnerItem + "</string>\n" + "</map>";
					contentview();
					toast();
				}
				if(item.matches(".*LP.*")){
					setTitle("saveLP.xml");
					SharedPreferences LP = getSharedPreferences("saveLP", MODE_PRIVATE);
					String maxLP = LP.getString("maxLP", "");
					String nowLP = LP.getString("nowLP", "");
					
					PreText = "<?xml version='1.0' encoding='utf-8' standalone='yes' ?>\n<map>\n"
							+ "     " + "<string name=\"maxLP\">" + maxLP + "</string>\n" +
							"     " + "<string name=\"nowLP\">" + nowLP + "</string>\n" + "</map>";
					contentview();
					toast();
				}
				if(item.matches(".*TIME.*")){
					setTitle("saveTIME.xml");
					SharedPreferences TIME = getSharedPreferences("saveTIME", MODE_PRIVATE);
					String time = TIME.getString("TIME", "");
					
					PreText = "<?xml version='1.0' encoding='utf-8' standalone='yes' ?>\n<map>\n" +
							"     " + "<string name=\"TIME\">" + time + "</string>\n" + "</map>";
					contentview();
					toast();
				}
			}
		});
	}
	
	public void contentview(){
		TextView text = new TextView(this);
		ScrollView scroll = new ScrollView(this);
		text.setLayoutParams(new ScrollView.LayoutParams(
				  ScrollView.LayoutParams.WRAP_CONTENT,
				  ScrollView.LayoutParams.WRAP_CONTENT));
		scroll.addView(text);
        setContentView(scroll);
        text.setText(PreText);
		text.setTextSize(16);
		text.setTextColor(Color.BLACK);
	}
	
	public void toast(){
		Toast.makeText(getApplicationContext(), "この画面ではBACKキーを押すと設定画面に戻ります", Toast.LENGTH_LONG).show();
	}
	
	//アナリティクスstop
	public void onStop(){
		super.onStop();
		EasyTracker.getInstance(this).activityStop(this);
	}
	
//	public void PrefDefault(){
//		try{
//		SharedPreferences Default = PreferenceManager.getDefaultSharedPreferences(this);
//		
//		Class<?> c = Class.forName("android.app.SharedPreferencesImpl");
//		Field f = c.getDeclaredField("mFile");
//		f.setAccessible(true);
//		File file = (File) f.get(Default);
//		FileReader fr = new FileReader(file);
//		BufferedReader br = new BufferedReader(fr);
//		PreText = br.readLine();
//		} catch (Exception e) {
//			Toast.makeText(this, "？？？", Toast.LENGTH_SHORT).show();
//		}
//	}
//	
//	public void PrefEdit(){
//		try {
//		SharedPreferences saveEditText = getSharedPreferences("saveEditText", MODE_PRIVATE);
//		Class<?> c = Class.forName("android.app.SharedPreferencesImpl");
//		Field f = c.getDeclaredField("mFile");
//		f.setAccessible(true);
//		File file = (File) f.get(saveEditText);
//		FileReader fr = new FileReader(file);
//		BufferedReader br = new BufferedReader(fr);
//		PreText = br.readLine();
//		} catch (Exception e) {
//			Toast.makeText(this, "？？？", Toast.LENGTH_SHORT).show();
//		}
//	}
//	
//	public void PrefLP(){
//		try{
//		SharedPreferences saveLP = getSharedPreferences("saveLP", MODE_PRIVATE);
//		saveLP.getString("nowLP", "");
//		Class<?> c = Class.forName("android.app.SharedPreferencesImpl");
//		Field f = c.getDeclaredField("mFile");
//		f.setAccessible(true);
//		File file = (File) f.get(saveLP);
//		FileReader fr = new FileReader(file);
//		BufferedReader br = new BufferedReader(fr);
//		PreText = br.readLine();
//		}catch (Exception e){
//			Toast.makeText(this, "？？？", Toast.LENGTH_SHORT).show();
//		}
//	}
//	
//	public void PrefTIME(){
//		try{
//		SharedPreferences saveTIME = getSharedPreferences("saveTIME", MODE_PRIVATE);
//		Class<?> c = Class.forName("android.app.SharedPreferencesImpl");
//		Field f = c.getDeclaredField("mFile");
//		f.setAccessible(true);
//		File file = (File) f.get(saveTIME);
//		FileReader fr = new FileReader(file);
//		BufferedReader br = new BufferedReader(fr);
//		PreText = br.readLine();
//		}catch (Exception e){
//			Toast.makeText(this, "？？？", Toast.LENGTH_SHORT).show();
//		}
//	}
}