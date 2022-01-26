package com.expanse.app.payoneer.ui.input;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.expanse.app.payoneer.data.AppDataSource;
import com.expanse.app.payoneer.model.Response;
import com.expanse.app.payoneer.utils.BaseSchedulerProvider;

import java.util.HashMap;

import io.reactivex.disposables.CompositeDisposable;

public class InputViewModel extends ViewModel {

	private final AppDataSource repository;
	private final BaseSchedulerProvider scheduler;
	private final CompositeDisposable disposables = new CompositeDisposable();

	MutableLiveData<Response> response = new MutableLiveData<>();

	public InputViewModel(
			AppDataSource repository,
			BaseSchedulerProvider scheduler
	) {
		this.repository = repository;
		this.scheduler = scheduler;
	}

	void chargeUser(String url, HashMap<String, String> body) {
		disposables.add(
				repository.chargeUser(url, body)
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
