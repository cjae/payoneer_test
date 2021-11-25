package com.expanse.app.payoneer.ui.viewholder;

import androidx.recyclerview.widget.RecyclerView;

import com.expanse.app.payoneer.databinding.NetworkItemBinding;
import com.expanse.app.payoneer.model.ApplicableNetwork;

public class NetworkViewHolder extends RecyclerView.ViewHolder {

	private final NetworkItemBinding binding;

	public NetworkViewHolder(NetworkItemBinding binding) {
		super(binding.getRoot());
		this.binding = binding;
	}

	public void bindTo(ApplicableNetwork item) {
		binding.setItem(item);
	}
}