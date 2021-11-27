package com.expanse.app.payoneer.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.expanse.app.payoneer.data.AppDataSource;
import com.expanse.app.payoneer.model.Response;
import com.expanse.app.payoneer.utils.BaseSchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;

public class MainViewModel extends ViewModel {

	private final AppDataSource repository;
	private final BaseSchedulerProvider scheduler;
	private final CompositeDisposable disposables = new CompositeDisposable();

	MutableLiveData<Response> response = new MutableLiveData<>();

	public MainViewModel(
			AppDataSource repository,
			BaseSchedulerProvider scheduler
	) {
		this.repository = repository;
		this.scheduler = scheduler;
	}

	/**
	 * Request method to fetch payment list data
	 * and response live data updated based on result
	 *
	 */
	void getPaymentMethods() {
		disposables.add(
				repository.getPaymentMethods()
						.subscribeOn(scheduler.io())
						.observeOn(scheduler.ui())
						.doOnSubscribe(__ -> response.setValue(Response.loading()))
						.subscribe(
								result -> response.setValue(Response.success(result)),
								throwable -> response.setValue(Response.error(throwable))
						)
		);
	}

	@Override
	protected void onCleared() {
		super.onCleared();
		disposables.clear();
	}
}