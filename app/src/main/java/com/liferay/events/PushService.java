package com.liferay.events;

import com.liferay.mobile.push.bus.BusUtil;
import com.liferay.mobile.screens.push.AbstractPushService;

import org.json.JSONObject;

/**
 * @author Javier Gamarra
 */
public class PushService extends AbstractPushService {

	@Override
	protected void processJSONNotification(JSONObject json) throws Exception {
		BusUtil.post(json);
	}
}
