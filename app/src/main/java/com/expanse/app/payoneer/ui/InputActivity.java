package com.expanse.app.payoneer.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.expanse.app.payoneer.R;
import com.expanse.app.payoneer.databinding.MainFragmentBinding;
import com.expanse.app.payoneer.model.ApplicableNetwork;
import com.expanse.app.payoneer.model.InputElement;
import com.expanse.app.payoneer.utils.AppKeys;
import com.google.gson.Gson;

import java.util.List;

public class InputActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_input);
	}
}