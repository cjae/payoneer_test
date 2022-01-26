package com.expanse.app.payoneer.data;

import com.expanse.app.payoneer.model.ListResult;

import java.util.HashMap;

import io.reactivex.Single;

public interface AppDataSource {
	Single<ListResult> getPaymentMethods();

	Single<String> chargeUser(String url, HashMap<String, String> body);
}
