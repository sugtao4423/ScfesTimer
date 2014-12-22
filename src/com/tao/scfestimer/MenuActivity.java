package com.tao.scfestimer;

import java.util.Locale;

import com.google.analytics.tracking.android.EasyTracker;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.widget.EditText;


public class MenuActivity extends PreferenceActivity {
	
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		getFragmentManager().beginTransaction().replace(android.R.id.content,  new MyPreferencesFragment()).commit();
		ActionBar actionbar = getActionBar();
		actionbar.setHomeButtonEnabled(true);
		//アナリティクスstart
		EasyTracker.getInstance(this).activityStart(this);
	}
	
	public static class MyPreferencesFragment extends PreferenceFragment {
		public void onCreate(Bundle savedInstanceState){
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.preference);
			
			//作者Twitter垢飛ばす
			Preference assyente = findPreference("assyente");
			assyente.setOnPreferenceClickListener(new OnPreferenceClickListener(){

				@Override
				public boolean onPreferenceClick(Preference preference) {
					Uri uri = Uri.parse("https://twitter.com/sugtao4423");
					Intent intent = new Intent(Intent.ACTION_VIEW, uri);
					startActivity(intent);
					return false;
				}
			});
			//アイコン作者Twitter垢飛ばす
			Preference nhi_ = findPreference("nhi_");
			nhi_.setOnPreferenceClickListener(new OnPreferenceClickListener(){

				@Override
				public boolean onPreferenceClick(Preference preference) {
					Uri uri = Uri.parse("https://twitter.com/tsubasaneko83");
					Intent intent = new Intent(Intent.ACTION_VIEW, uri);
					startActivity(intent);
					return false;
				}
				
			});
			//FAQに飛ばす
			Preference update = findPreference("FAQ");
			update.setOnPreferenceClickListener(new OnPreferenceClickListener(){

				@Override
				public boolean onPreferenceClick(Preference preference) {
					Intent intent = new Intent(getActivity(), FAQ.class);
					startActivity(intent);
					return false;
				}
				
			});
			//ご協力お願います
			Preference coop = findPreference("coop");
			coop.setOnPreferenceClickListener(new OnPreferenceClickListener() {
				@Override
				public boolean onPreferenceClick(Preference preference) {
					Intent intent = new Intent(getActivity(), Coop.class);
					startActivity(intent);
					return false;
				}
			});
			//シェア
			Preference share = findPreference("share");
			share.setOnPreferenceClickListener(new OnPreferenceClickListener(){

				@Override
				public boolean onPreferenceClick(Preference preference) {
					final EditText EditTweet = new EditText(getActivity());
					String Comment, tweet, cancel;
					if(Locale.getDefault().toString().startsWith("ja")){
						Comment = "コメントオナシャス！！";
						tweet = "ツイート";
						cancel = "キャンセル";
					}else{
						Comment = "Please Comment!";
						tweet = "Tweet";
						cancel = "Cancel";
					}
					AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
					.setTitle(Comment)
					.setView(EditTweet)
					.setPositiveButton(tweet, new OnClickListener(){

						@Override
						public void onClick(DialogInterface dialog, int which) {
							//ツイートボタン処理
							String Tweet = EditTweet.getText().toString();
							if(Tweet.matches(".*上上下下左右左右BA.*")){
								startActivity(new Intent(getActivity(), HideFunction.class));
								}else{
							String URL = "http://twitter.com/share?text=" + Tweet + " " + "http://bit.ly/1BfxIDl";
							Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
							startActivity(intent);
						}
						}
					});
					builder.setNegativeButton(cancel, new OnClickListener(){

						@Override
						public void onClick(DialogInterface dialog, int which) {
							return;
						}
					});
					builder.create().show();
					
					return false;
				}
			});
		}
	}
	//アナリティクスstop
	public void onStop(){
		super.onStop();
		EasyTracker.getInstance(this).activityStop(this);
	}
}