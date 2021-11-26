package com.expanse.app.payoneer.ui.main;

import static org.junit.Assert.assertEquals;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.expanse.app.payoneer.data.FakeRepository;
import com.expanse.app.payoneer.model.ListResult;
import com.expanse.app.payoneer.model.Response;
import com.expanse.app.payoneer.utils.Status;
import com.expanse.app.payoneer.utils.TestScheduler;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

public class MainViewModelTest {

	private MainViewModel viewModel;

	@Rule
	public TestRule rule = new InstantTaskExecutorRule();

	@Before
	public void setupViewModel() {
		viewModel = new MainViewModel(new FakeRepository(), new TestScheduler());
	}

	@Test
	public void getPaymentMethods_checkSuccess() {
		viewModel.getPaymentMethods();

		Response response = viewModel.response.getValue();
		assert response != null;

		assertEquals(Status.SUCCESS, response.status);

		ListResult actualValue = ((ListResult) response.data);
		assert actualValue != null;

		assertEquals(1, actualValue.getNetworks().getApplicable().size());
	}

	@Test
	public void getPaymentMethods_checkError() {
		viewModel.getPaymentMethods();

		Response response = viewModel.response.getValue();
		assert response != null;

		assertEquals(Status.ERROR, response.status);
	}
}