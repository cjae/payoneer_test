package com.expanse.app.payoneer.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.expanse.app.payoneer.data.AppRepository;
import com.expanse.app.payoneer.di.Injector;
import com.expanse.app.payoneer.model.Response;
import com.expanse.app.payoneer.utils.SchedulersFacade;

import io.reactivex.disposables.CompositeDisposable;

public class MainViewModel extends ViewModel {

	private final AppRepository repository = Injector.provideRepository();
	private final SchedulersFacade scheduler = Injector.provideScheduler();
	private final CompositeDisposable disposables = new CompositeDisposable();

	MutableLiveData<Response> response = new MutableLiveData<>();

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