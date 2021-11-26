package com.expanse.app.payoneer.utils;

import java.io.IOException;
import java.net.ConnectException;
import java.util.Objects;

import retrofit2.HttpException;

public class RequestErrorHelper {

	public static final String AUTHENTICATION_ERROR_MESSAGE = "Unable to complete request, Request not authenticated.";
	public static final String NETWORK_ERROR_MESSAGE = "Error communicating with the server. Please try again.";
	private static final String NETWORK_LOST_ERROR_MESSAGE = "Network connection lost, could not process request. Please check your connection and try again.";
	private static final String DEFAULT_ERROR_MESSAGE = "Unable to connect to the server at this moment, Please try again.";

	/**
	 * Get throwable error
	 * check for error codes
	 * process error message to return
	 *
	 * @param throwable error received from result
	 */
	public static String getErrorMessage(Throwable throwable) {
		if (throwable instanceof HttpException) {
			HttpException error = (HttpException) throwable;

			if (error.code() == 401) {
				return AUTHENTICATION_ERROR_MESSAGE;
			}

			if (error.code() == 500) {
				return NETWORK_ERROR_MESSAGE;
			}

			return DEFAULT_ERROR_MESSAGE;
		}

		if (throwable.getCause() instanceof IOException) {
			return NETWORK_ERROR_MESSAGE;
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
