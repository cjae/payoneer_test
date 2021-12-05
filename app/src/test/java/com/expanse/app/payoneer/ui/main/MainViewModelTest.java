package com.expanse.app.payoneer.ui.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.expanse.app.payoneer.data.FakeRepository;
import com.expanse.app.payoneer.model.ListResult;
import com.expanse.app.payoneer.model.Response;
import com.expanse.app.payoneer.utils.LiveDataTestUtil;
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
	public void getPaymentMethods_checkSuccess() throws InterruptedException {
		viewModel.getPaymentMethods();

		Response result = LiveDataTestUtil.getOrAwaitValue(viewModel.response);
		assertEquals(Status.SUCCESS, result.status);

		ListResult actualValue = ((ListResult) result.data);
		assert actualValue != null;

		assertFalse(actualValue.getNetworks().getApplicable().isEmpty());
	}

	@Test
	public void getPaymentMethods_checkError() throws InterruptedException {
		viewModel.getPaymentMethods();

		Response result = LiveDataTestUtil.getOrAwaitValue(viewModel.response);
		assertEquals(Status.ERROR, result.status);
	}
}