package com.expanse.app.payoneer.data;

import com.expanse.app.payoneer.model.ListResult;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface ServiceGenerator {

	@GET("checkout-android/develop/shared-test/lists/listresult.json")
	Single<ListResult> getPaymentMethods();
}
