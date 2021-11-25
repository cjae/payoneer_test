package com.expanse.app.payoneer.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.expanse.app.payoneer.databinding.MainFragmentBinding;
import com.expanse.app.payoneer.model.ListResult;
import com.expanse.app.payoneer.model.Response;
import com.expanse.app.payoneer.ui.adapter.NetworkAdapter;
import com.expanse.app.payoneer.utils.RequestErrorHelper;

import java.math.BigDecimal;

import timber.log.Timber;

public class MainFragment extends Fragment {

	private NetworkAdapter adapter;
	private MainViewModel mViewModel;
	private MainFragmentBinding binding;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		adapter = new NetworkAdapter();
		mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		binding = MainFragmentBinding.inflate(inflater, container, false);
		return binding.getRoot();
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initNetworkRv();
		observeViewModel();
		makeRequest();
	}

	private void initNetworkRv() {
		binding.methodsRv.setItemAnimator(new DefaultItemAnimator());
		binding.methodsRv.setLayoutManager(new LinearLayoutManager(requireContext()));
		binding.methodsRv.setAdapter(adapter);
	}

	private void observeViewModel() {
		mViewModel.response.observe(getViewLifecycleOwner(), this::processResponse);
	}

	private void makeRequest() {
		mViewModel.getPaymentMethods();
	}

	private void processResponse(Response response) {
		switch (response.status) {
			case LOADING:
				renderLoadingState();
				break;
			case SUCCESS:
				parseResponseData(response.data);
				renderDataState();
				break;
			case ERROR:
				renderErrorState();
				showErrorMessage(response.error);
				break;
		}
	}

	private void parseResponseData(Object data) {
		if (data instanceof ListResult) {
			BigDecimal amount = ((ListResult) data).getPayment().getAmount();
			String currency = ((ListResult) data).getPayment().getCurrency();

			binding.amountTv.setText(String.format("%s %s", amount, currency));
			adapter.submitList(((ListResult) data).getNetworks().getApplicable());
		}
	}

	private void renderErrorState() {
		binding.methodsPb.setVisibility(View.GONE);
		binding.methodsRv.setVisibility(View.GONE);
	}

	private void renderDataState() {
		binding.methodsPb.setVisibility(View.GONE);
		binding.methodsRv.setVisibility(View.VISIBLE);
	}

	private void renderLoadingState() {
		binding.methodsPb.setVisibility(View.VISIBLE);
		binding.methodsRv.setVisibility(View.GONE);
	}

	private void showErrorMessage(Throwable throwable) {
		Timber.e(throwable);
		Toast.makeText(
				requireActivity(),
				RequestErrorHelper.getErrorMessage(throwable),
				Toast.LENGTH_SHORT
		).show();
	}
}