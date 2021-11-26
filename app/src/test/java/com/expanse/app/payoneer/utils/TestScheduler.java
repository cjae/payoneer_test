package com.expanse.app.payoneer.utils;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class TestScheduler implements BaseSchedulerProvider {

	@Override
	public Scheduler io() {
		return Schedulers.trampoline();
	}

	@Override
	public Scheduler computation() {
		return Schedulers.trampoline();
	}

	@Override
	public Scheduler ui() {
		return Schedulers.trampoline();
	}
}