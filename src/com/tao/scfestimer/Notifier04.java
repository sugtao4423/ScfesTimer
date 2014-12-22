package com.tao.scfestimer;

import java.util.Locale;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;

public class Notifier04 extends BroadcastReceiver {

	@Override
	public void onReceive(Context content, Intent intent) {
		//Intent作成
		Intent sendIntent = new Intent(content, MainActivity.class);
		PendingIntent sender = PendingIntent.getActivity(content, 0, sendIntent, 0);
		//スクフェスIntent
		Intent ScFes = new Intent();
		ScFes.setAction(Intent.ACTION_MAIN);
		ComponentName compo = new ComponentName("klb.android.lovelive","klb.android.GameEngine.GameEngineActivity");
		ScFes.setComponent(compo);
		PendingIntent Scfes = PendingIntent.getActivity(content, 12345, ScFes, PendingIntent.FLAG_UPDATE_CURRENT);
		
		//設定からの情報をインポート
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(content);
		int vibrate = 0;
		int sound = 0;
		int led = 0;
		if(pref.getBoolean("vibrate_preference", true)){
			vibrate = vibrate | Notification.DEFAULT_VIBRATE;
		}
		if(pref.getBoolean("sound_preference", true)){
			sound = sound | Notification.DEFAULT_SOUND;
		}
		if(pref.getBoolean("led_preference", true)){
			led = led | Notification.DEFAULT_LIGHTS;
		}
		
		int customLeftTime = pref.getInt("customLeftTime", 0);
		String ticker, title, text, open;
		if(Locale.getDefault().toString().startsWith("ja")){
			ticker = "LP回復"+ customLeftTime + "分前です！";
			title = "スクフェスタイマー";
			text = "LP回復" + customLeftTime + "分前です";
			open = "スクフェスを開く";
		}else{
			ticker = "LP recovery " + customLeftTime + " minutes ago!";
			title = "ScfesTimer";
			text = "LP recovery " + customLeftTime +" minutes ago.";
			open = "Open Scfes.";
		}
		
		Notification noti = new NotificationCompat.Builder(content)
		//ステータスバーに表示されるtext
		.setTicker(ticker)
		//アイコン
		.setSmallIcon(R.drawable.ic_launcher_winter)
		//Notificationを開いた時に表示されるタイトル
		.setContentTitle(title)
		//Notificationを開いた時に表示されるサブタイトル
		.setContentText(text)
		//タップするとキャンセル
		.setAutoCancel(true)
		//スクフェスを起動するボタン追加
		.addAction(R.drawable.scfes, open, Scfes)
		// 通知時の音・バイブ・ライト
		.setDefaults(sound | vibrate | led)

		
		.setContentIntent(sender)
		.build();
		
		//NotificationManagerを取得
		NotificationManager manager = (NotificationManager) content.getSystemService(Service.NOTIFICATION_SERVICE);
		
		//Notificationを作成して通知
		manager.notify(0, noti);
		
		//通知されたときに入力可能にするためのpreference
		Editor edit = pref.edit()
				.putBoolean("NotiEnable", true);
		edit.commit();
	}
}
