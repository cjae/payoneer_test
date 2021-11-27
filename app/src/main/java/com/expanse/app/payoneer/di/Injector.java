package com.expanse.app.payoneer.di;

import com.expanse.app.payoneer.data.AppRepository;
import com.expanse.app.payoneer.data.ServiceGenerator;
import com.expanse.app.payoneer.utils.MainScheduler;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Injector {

	private static final String BASE_URL = "https://raw.githubusercontent.com/optile/";

	private static MainScheduler scheduler;
	private static AppRepository repository;
	private static OkHttpClient okHttpClient;
	private static Retrofit retrofitInstance;
	private static ServiceGenerator serviceGenerator;

	private static Retrofit provideRetrofit() {
		if (retrofitInstance == null) {
			Retrofit.Builder retrofit = new Retrofit.Builder().client(Injector.provideOkHttpClient())
					.baseUrl(BASE_URL)
					.addConverterFactory(GsonConverterFactory.create())
					.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
			retrofitInstance = retrofit.build();

		}
		return retrofitInstance;
	}

	private static OkHttpClient provideOkHttpClient() {
		HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
		logging.setLevel(HttpLoggingInterceptor.Level.BODY);

		if (okHttpClient == null) {
			okHttpClient = new OkHttpClient.Builder()
					.readTimeout(60, TimeUnit.SECONDS)
					.connectTimeout(60, TimeUnit.SECONDS)
					.addInterceptor(logging)
					.build();
		}

		return okHttpClient;
	}

	public static ServiceGenerator getServiceGenerator() {
		if (serviceGenerator == null) {
			serviceGenerator = provideRetrofit().create(ServiceGenerator.class);
		}

		return serviceGenerator;
	}

	public static AppRepository provideRepository() {
		if (repository == null) {
			repository = new AppRepository();
		}

		return repository;
	}

	public static MainScheduler provideScheduler() {
		if (scheduler == null) {
			scheduler = new MainScheduler();
		}

		return scheduler;
	}
}