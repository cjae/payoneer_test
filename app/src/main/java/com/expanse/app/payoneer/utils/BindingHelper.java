package com.expanse.app.payoneer.utils;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

import java.net.URL;

public class BindingHelper {

	@BindingAdapter("imageUrl")
	public static void loadImage(ImageView imageView, URL url) {
		Glide.with(imageView.getContext())
				.load(url.toString())
				.into(imageView);
	}
}