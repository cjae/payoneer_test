package com.expanse.app.payoneer.utils;

import java.net.ConnectException;
import java.util.Objects;

import retrofit2.HttpException;

public class RequestErrorHelper {

	private static final String AUTHENTICATION_ERROR_MESSAGE = "Unable to complete request, Request not authenticated.";
	private static final String NETWORK_ERROR_MESSAGE = "Error communicating with the server. Please try again.";
	private static final String NETWORK_LOST_ERROR_MESSAGE = "Network connection lost, could not process request. Please check your connection and try again.";
	private static final String DEFAULT_ERROR_MESSAGE = "Unable to connect to the server at this moment, Please try again.";

	public static String getErrorMessage(Throwable throwable) {
		if (throwable instanceof HttpException) {
			HttpException error = (HttpException) throwable;

			if (error.code() == 401) {
				return AUTHENTICATION_ERROR_MESSAGE;
			}

			if (error.code() == 500) {
				return NETWORK_ERROR_MESSAGE;
			}

			if (Objects.requireNonNull(error.response()).errorBody() == null) {
				return NETWORK_ERROR_MESSAGE;
			}

			return DEFAULT_ERROR_MESSAGE;
		}

		if (throwable.getCause() instanceof ConnectException) {
			return NETWORK_LOST_ERROR_MESSAGE;
		}

		if (throwable.getCause() instanceof java.net.SocketTimeoutException) {
			return NETWORK_ERROR_MESSAGE;
		}

		if (throwable.getCause() instanceof java.net.UnknownHostException) {
			return NETWORK_ERROR_MESSAGE;
		}

		return DEFAULT_ERROR_MESSAGE;
	}
}
