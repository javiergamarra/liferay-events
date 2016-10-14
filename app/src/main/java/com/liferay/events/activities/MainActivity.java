package com.liferay.events.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import com.liferay.events.R;
import com.liferay.events.mainfragments.AddTalkFragment;
import com.liferay.events.mainfragments.AgendaFragment;
import com.liferay.events.mainfragments.BlogsFragment;
import com.liferay.events.mainfragments.LoginFragment;
import com.liferay.events.mainfragments.SponsorsFragment;
import com.liferay.mobile.android.auth.basic.BasicAuthentication;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.service.SessionImpl;
import com.liferay.mobile.screens.auth.BasicAuthMethod;
import com.liferay.mobile.screens.auth.login.LoginListener;
import com.liferay.mobile.screens.auth.login.interactor.LoginBasicInteractor;
import com.liferay.mobile.screens.context.User;
import com.liferay.mobile.screens.push.PushScreensActivity;
import com.liferay.mobile.screens.util.LiferayLogger;
import org.json.JSONObject;

public class MainActivity extends PushScreensActivity
	implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, LoginListener {

	private NavigationView navigationView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		doDefaultLogin();

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
					.setAction("Action", null)
					.show();
			}
		});

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
			R.string.navigation_drawer_close);
		drawer.setDrawerListener(toggle);
		toggle.syncState();

		navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);
		navigationView.setCheckedItem(R.id.agenda);
		navigationView.getMenu().performIdentifierAction(R.id.agenda, 0);

		_content = findViewById(android.R.id.content);
	}

	private void doDefaultLogin() {
		LoginBasicInteractor loginInteractor = new LoginBasicInteractor();
		loginInteractor.onScreenletAttached(this);

		loginInteractor.start("test@liferay.com", "test", BasicAuthMethod.EMAIL);
	}

	@Override
	protected void onResume() {
		super.onResume();

		_drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
	}

	@Override
	public void onBackPressed() {
		if (_drawer.isDrawerOpen(GravityCompat.START)) {
			_drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {

		int menuId = item.getItemId();
		item.setChecked(true);

		Fragment fragment = fragmentForMenuEntry(menuId);
		getSupportFragmentManager().
			beginTransaction().
			replace(R.id.main_fragment, fragment).
			commit();

		_drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		_drawer.closeDrawer(GravityCompat.START);

		return true;
	}

	@Override
	public void onClick(View v) {
		getSupportFragmentManager().
			beginTransaction().
			replace(R.id.main_fragment, LoginFragment.newInstance()).
			commit();

		_drawer.closeDrawer(GravityCompat.START);
	}

	@Override
	public void onLoginSuccess(User user) {
		navigationView.getHeaderView(0).findViewById(R.id.user_portrait).setOnClickListener(this);
	}

	@Override
	public void onLoginFailure(Exception e) {

	}

	@Override
	protected Session getDefaultSession() {
		return new SessionImpl(getString(R.string.liferay_server),
			new BasicAuthentication(getString(R.string.default_user), getString(R.string.default_password)));
	}

	@Override
	protected void onPushNotificationReceived(JSONObject jsonObject) {
		View view = findViewById(android.R.id.content);
		Snackbar.make(view, jsonObject.toString(), Snackbar.LENGTH_LONG).show();
	}

	@Override
	protected void onErrorRegisteringPush(String message, Exception e) {
		LiferayLogger.e(message, e);
	}

	@Override
	protected String getSenderId() {
		return getString(R.string.push_id);
	}

	private Fragment fragmentForMenuEntry(int menuId) {
		switch (menuId) {
			case R.id.sponsors:
				return SponsorsFragment.newInstance();
			case R.id.add_talk:
				return AddTalkFragment.newInstance(null);
			case R.id.blogs:
				return BlogsFragment.newInstance();
			default:
				return AgendaFragment.newInstance();
		}
	}

	private DrawerLayout _drawer;
	private View _content;
}
