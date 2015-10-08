package com.liferay.events;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.liferay.mobile.screens.auth.login.LoginListener;
import com.liferay.mobile.screens.auth.login.LoginScreenlet;
import com.liferay.mobile.screens.context.User;

public class MainActivity extends AppCompatActivity implements LoginListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		LoginScreenlet loginScreenlet = (LoginScreenlet) findViewById(R.id.login);
		loginScreenlet.setListener(this);
	}

	@Override
	public void onLoginSuccess(User user) {
		startActivity(new Intent(this, AgendaActivity.class));
	}

	@Override
	public void onLoginFailure(Exception e) {
		View view = findViewById(android.R.id.content);

		Snackbar.make(view, "Fail :(", Snackbar.LENGTH_LONG).show();
	}
}
