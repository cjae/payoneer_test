package com.expanse.app.payoneer.ui.input;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.expanse.app.payoneer.databinding.InputFragmentBinding;
import com.expanse.app.payoneer.di.Injector;
import com.expanse.app.payoneer.model.ApplicableNetwork;
import com.expanse.app.payoneer.model.InputElement;
import com.expanse.app.payoneer.model.Response;
import com.expanse.app.payoneer.ui.main.MainViewModel;
import com.expanse.app.payoneer.ui.main.MainViewModelFactory;
import com.expanse.app.payoneer.utils.AppKeys;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InputFragment extends Fragment {

	private InputViewModel mViewModel;
	private InputFragmentBinding binding;

	private String itemUrl;
	private List<EditText> inputFields = new ArrayList<>();

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mViewModel = new ViewModelProvider(this, new MainViewModelFactory(
				Injector.provideRepository(),
				Injector.provideScheduler())
		).get(InputViewModel.class);
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
							 @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		binding = InputFragmentBinding.inflate(inflater, container, false);
		return binding.getRoot();
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initClickListener();
		observeViewModel();
		readIntentData();
	}

	private void initClickListener() {
		binding.chargeButton.setOnClickListener( view -> {
			HashMap<String, String> dataMap = readInputData();
			mViewModel.chargeUser(itemUrl, dataMap);
		});
	}

	private HashMap<String, String> readInputData() {
		HashMap<String, String> dataMap = new HashMap<>();

		for (EditText field : inputFields) {
			dataMap.put(field.getTag().toString(), field.getText().toString());
		}

		return dataMap;
	}

	private void readIntentData() {
		Intent intent = getActivity().getIntent();
		if (intent == null) return;

		String data = intent.getStringExtra(AppKeys.ITEM_KEY);
		ApplicableNetwork item = new Gson().fromJson(data, ApplicableNetwork.class);
		itemUrl = item.getLinks().get(AppKeys.OPERATION_KEY).toString();

		if (item.getInputElements() == null) return;

		fillOutInputField(item.getInputElements());
	}

	private void fillOutInputField(List<InputElement> inputElements) {
		for (InputElement element : inputElements) {
			inputFields.add(getTextField(binding.fieldContainer, element));
		}
	}

	private EditText getTextField(LinearLayout layout, InputElement element) {
		EditText editText = new EditText(getContext());
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);

		editText.setLayoutParams(layoutParams);
		editText.setHint(element.getName());
		editText.setTag(element.getName());
		layoutParams.setMargins(0, 16, 0, 0);

		layout.addView(editText);
		return editText;
	}

	private void observeViewModel() {
		mViewModel.response.observe(getViewLifecycleOwner(), this::processResponse);
	}

	private void processResponse(Response response) {
		switch (response.status) {
			case LOADING:
				//TODO
				break;
			case SUCCESS:
				//TODO
				break;
			case ERROR:
				// TODO
				break;
		}
	}
}
