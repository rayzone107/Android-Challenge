package com.rachit.challenge.util

import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.rachit.challenge.R

/**
 * Generic Binding Adapters for binding data directly to the view using DataBinding
 */

/**
 * Binds an imageURI from the assets folder to the imageView using Glide
 */
@BindingAdapter("imageFromUri")
fun bindImageFromAssets(view: ImageView, imageUri: String?) {
    imageUri?.let {
        Glide.with(view)
            .load(Uri.parse("file:/${imageUri}"))
            .into(view)
    }
}

/**
 * Binds the pricing data to a TextView after applying standard conversions
 */
@BindingAdapter("price")
fun bindPriceFromLong(view: TextView, price: Long) {
    view.text = view.resources.getString(R.string.location_price, price.toDouble() / 100)
}

/**
 * Binds the pricing data to a Button after applying standard conversions
 */
@BindingAdapter("buttonPrice")
fun bindPriceForButton(view: TextView, price: Long) {
    view.text = view.resources.getString(R.string.button_location_price, price.toDouble() / 100)
}
