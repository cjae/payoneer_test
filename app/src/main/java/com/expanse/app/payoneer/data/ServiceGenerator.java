package com.expanse.app.payoneer.data;

import com.expanse.app.payoneer.model.ListResult;

import java.util.HashMap;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ServiceGenerator {

	@GET("checkout-android/develop/shared-test/lists/listresult.json")
	Single<ListResult> getPaymentMethods();

	@POST
	Single<String> chargeUser(@Url String url, @Body HashMap<String, String> body);
}
