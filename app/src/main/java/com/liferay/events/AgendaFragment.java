package com.liferay.events;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Javier Gamarra
 */
public class AgendaFragment extends Fragment{

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.content_agenda, container, false);
	}

	public static Fragment newInstance() {
		return new AgendaFragment();
	}
}
