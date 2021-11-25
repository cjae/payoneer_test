package com.expanse.app.payoneer.data;

import com.expanse.app.payoneer.model.ListResult;

import io.reactivex.Single;

public interface AppDataSource {
	Single<ListResult> getPaymentMethods();
}
