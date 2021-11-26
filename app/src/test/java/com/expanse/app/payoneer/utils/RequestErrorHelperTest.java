package com.expanse.app.payoneer.utils;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;

public class RequestErrorHelperTest {

	@Test
	public void errorMessage_is401Correct() {
		String expectedMessage = RequestErrorHelper.AUTHENTICATION_ERROR_MESSAGE;

		HttpException mockHttpException = new HttpException(Response.error(401, mock(ResponseBody.class)));
		String errorMessage = RequestErrorHelper.getErrorMessage(mockHttpException);

		assertEquals(expectedMessage, errorMessage);
	}

	@Test
	public void errorMessage_is500Correct() {
		String expectedMessage = RequestErrorHelper.NETWORK_ERROR_MESSAGE;

		HttpException mockHttpException = new HttpException(Response.error(500, mock(ResponseBody.class)));
		String errorMessage = RequestErrorHelper.getErrorMessage(mockHttpException);

		assertEquals(expectedMessage, errorMessage);
	}

	@Test
	public void errorMessage_isIOExceptionCorrect() {
		String expectedMessage = RequestErrorHelper.NETWORK_ERROR_MESSAGE;

		Throwable mockIOException = new Throwable(new IOException());
		String errorMessage = RequestErrorHelper.getErrorMessage(mockIOException);

		assertEquals(expectedMessage, errorMessage);
	}
}