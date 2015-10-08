package com.liferay.events;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.liferay.mobile.push.bus.BusUtil;
import com.liferay.mobile.screens.push.AbstractPushService;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Javier Gamarra
 */
public class PushService extends AbstractPushService {

	@Override
	protected void processJSONNotification(JSONObject json) throws Exception {
		BusUtil.post(json);
		String titleHeader =  "New notification: ";
		String title = titleHeader + getString(json, "body");
		String description = getString(json, "description");

		createGlobalNotification(title, description);
	}

	private void createGlobalNotification(String title, String description) {
		Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

		NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
			.setContentTitle(title)
			.setContentText(description)
			.setAutoCancel(true)
			.setSound(uri)
			.setVibrate(new long[]{2000, 1000, 2000, 1000})
			.setSmallIcon(R.drawable.liferay_glyph);

		Notification notification = builder.build();
		NotificationManager notificationManager =
			(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(1, notification);
	}


	private String getString(final JSONObject json, final String element) throws JSONException {
		return json.has(element) ? json.getString(element) : "";
	}
}
