package com.expanse.app.payoneer.data;

import com.expanse.app.payoneer.model.ListResult;
import com.expanse.app.payoneer.utils.DummyData;

import io.reactivex.Single;

public class FakeRepository implements AppDataSource {

//	@Override
//	public Single<ListResult> getPaymentMethods() {
//		return Single.error(new Throwable(errorMessage));
//	}

	@Override
	public Single<ListResult> getPaymentMethods() {
		return Single.just(DummyData.getDummyData());
	}
}