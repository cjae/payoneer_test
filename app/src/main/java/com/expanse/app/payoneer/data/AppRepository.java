package com.expanse.app.payoneer.data;

import com.expanse.app.payoneer.di.Injector;
import com.expanse.app.payoneer.model.ListResult;

import java.util.HashMap;

import io.reactivex.Single;

public class AppRepository implements AppDataSource {

	private final ServiceGenerator mServiceGenerator;

	public AppRepository() {
		mServiceGenerator = Injector.getServiceGenerator();
	}

	@Override
	public Single<ListResult> getPaymentMethods() {
		return Single.defer(mServiceGenerator::getPaymentMethods);
	}

	@Override
	public Single<String> chargeUser(String url, HashMap<String, String> body) {
		return Single.defer(() -> mServiceGenerator.chargeUser(url, body));
	}
}
