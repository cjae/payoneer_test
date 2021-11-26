package com.expanse.app.payoneer.ui.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.expanse.app.payoneer.data.AppRepository;
import com.expanse.app.payoneer.utils.MainScheduler;

public class MainViewModelFactory implements ViewModelProvider.Factory {

	private final AppRepository repository;
	private final MainScheduler scheduler;

	public MainViewModelFactory(AppRepository repository, MainScheduler scheduler) {
		this.repository = repository;
		this.scheduler = scheduler;
	}

	@NonNull
	@Override
	public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
		if (modelClass.isAssignableFrom(MainViewModel.class)) {
			return (T) new MainViewModel(repository, scheduler);
		}

		throw new IllegalArgumentException("Unable to construct viewmodel");
	}
}
