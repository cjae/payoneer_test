package com.expanse.app.payoneer.utils;

import io.reactivex.Scheduler;

public interface BaseSchedulerProvider {

	/**
	 * IO thread pool scheduler
	 */
	Scheduler io();

	/**
	 * Computation thread pool scheduler
	 */
	Scheduler computation();

	/**
	 * Main Thread scheduler
	 */
	Scheduler ui();
}