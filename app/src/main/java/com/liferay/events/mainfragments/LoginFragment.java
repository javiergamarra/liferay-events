package com.liferay.events.mainfragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liferay.events.R;
import com.liferay.mobile.screens.auth.login.LoginListener;
import com.liferay.mobile.screens.auth.login.LoginScreenlet;
import com.liferay.mobile.screens.context.SessionContext;
import com.liferay.mobile.screens.context.User;
import com.liferay.mobile.screens.userportrait.UserPortraitScreenlet;

/**
 * @author Javier Gamarra
 */
public class LoginFragment extends Fragment implements LoginListener {

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View inflate = inflater.inflate(R.layout.content_login, container, false);

		LoginScreenlet loginScreenlet = (LoginScreenlet) inflate.findViewById(R.id.login);
		loginScreenlet.setListener(this);

		return inflate;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		_view = getActivity().findViewById(android.R.id.content);
	}

	public static Fragment newInstance() {
		return new LoginFragment();
	}

	@Override
	public void onLoginSuccess(User user) {
		UserPortraitScreenlet userPortraitScreenlet = (UserPortraitScreenlet) getActivity().findViewById(R.id.user_portrait);
		userPortraitScreenlet.setUserId(SessionContext.getLoggedUser().getId());
		userPortraitScreenlet.load();
	}

	@Override
	public void onLoginFailure(Exception e) {
		Snackbar.make(_view, "Login failed! :(", Snackbar.LENGTH_SHORT).show();
	}

	private View _view;
}
