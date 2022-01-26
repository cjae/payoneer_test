package com.expanse.app.payoneer.data;

import com.expanse.app.payoneer.model.ListResult;
import com.expanse.app.payoneer.utils.DummyData;

import java.util.HashMap;

import io.reactivex.Single;

public class FakeRepository implements AppDataSource {

	@Override
	public Single<ListResult> getPaymentMethods() {
		return Single.just(DummyData.getDummyData());
	}

	@Override
	public Single<String> chargeUser(String url, HashMap<String, String> body) {
		return null;
	}
}