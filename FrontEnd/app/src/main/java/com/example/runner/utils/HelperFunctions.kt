package com.example.runner.utils

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import com.google.gson.Gson
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso


object HelperFunctions {

    fun usePicasso(
        url: String,
        placeholder: Int,
        view: ImageView
    ) =
        Picasso.get()
            .load(url)
            .networkPolicy(NetworkPolicy.NO_STORE, NetworkPolicy.NO_CACHE)
            .placeholder(placeholder)
            .into(view)

    fun launchURL(context: Context, url: String) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(context, Uri.parse(url))
    }

    fun toastMsg(context: Context, message: String) =
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()


    data class ResponseConverter<out T>(val json: String, val data: T?) {

        companion object {

            inline fun <reified T> convert(json: String): ResponseConverter<T> =
                ResponseConverter(json, Gson().fromJson(json, T::class.java))
        }
    }
}