package com.expanse.app.payoneer.app;

import android.app.Application;

import com.expanse.app.payoneer.BuildConfig;

import timber.log.Timber;

public class PayoneerApp extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		initTimber();
	}

	private void initTimber() {
		if (BuildConfig.DEBUG) Timber.plant(new Timber.DebugTree());
		// TODO: Implement Release Tree Logging
	}
}