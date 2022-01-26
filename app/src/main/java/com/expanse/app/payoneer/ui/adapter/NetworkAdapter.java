package com.expanse.app.payoneer.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.expanse.app.payoneer.databinding.NetworkItemBinding;
import com.expanse.app.payoneer.model.ApplicableNetwork;
import com.expanse.app.payoneer.ui.listener.NetworkClickListener;
import com.expanse.app.payoneer.ui.viewholder.NetworkViewHolder;

public class NetworkAdapter extends ListAdapter<ApplicableNetwork, NetworkViewHolder> {

	NetworkClickListener listener;

	public NetworkAdapter(NetworkClickListener listener) {
		super(DIFF_CALLBACK);
		this.listener = listener;
	}

	@NonNull
	@Override
	public NetworkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		NetworkItemBinding binding = NetworkItemBinding.inflate(LayoutInflater.from(parent.getContext()),
				parent, false);
		return new NetworkViewHolder(binding);
	}

	@Override
	public void onBindViewHolder(@NonNull NetworkViewHolder holder, int position) {
		holder.bindTo(getItem(position));
		holder.itemView.setOnClickListener(view -> listener.networkItemClicked(getItem(position)));
	}

	public static final DiffUtil.ItemCallback<ApplicableNetwork> DIFF_CALLBACK =
			new DiffUtil.ItemCallback<ApplicableNetwork>() {

		@Override
		public boolean areItemsTheSame(@NonNull ApplicableNetwork oldUser, @NonNull ApplicableNetwork newUser) {
			return oldUser.getCode().equals(newUser.getCode());
		}

		@Override
		public boolean areContentsTheSame(@NonNull ApplicableNetwork oldUser, @NonNull ApplicableNetwork newUser) {
			return oldUser.equals(newUser);
		}
	};
}