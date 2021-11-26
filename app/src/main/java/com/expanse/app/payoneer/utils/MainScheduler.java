package com.expanse.app.payoneer.utils;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainScheduler implements BaseSchedulerProvider {

	@Override
	public Scheduler io() {
		return Schedulers.io();
	}

	@Override
	public Scheduler computation() {
		return Schedulers.computation();
	}

	@Override
	public Scheduler ui() {
		return AndroidSchedulers.mainThread();
	}
}