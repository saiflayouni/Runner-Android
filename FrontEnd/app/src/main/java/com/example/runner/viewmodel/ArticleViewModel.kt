package com.example.runner.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.runner.BASE_URL
import com.example.runner.api.ApiInterface
import com.example.runner.model.GenericResp
import com.example.runner.utils.HelperFunctions
import com.example.runner.utils.Resource
import com.example.runner.utils.Resource.Companion.loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


@HiltViewModel
class ArticleViewModel @Inject constructor() : ViewModel() {

    fun loadRssArticles(pageNumber: Int) =
        liveData(Dispatchers.IO) {

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val retrofitInterface = retrofit.create(ApiInterface::class.java)

            emit(loading(data = null))
            try {
                val response = retrofitInterface.downloadArticles(pageNumber)
                if (response.isSuccessful)
                    emit(
                        Resource.success(
                            data = response.body()
                        )
                    )
                else
                    emit(
                        Resource.error(
                            data = null,
                            message = HelperFunctions.ResponseConverter.convert<GenericResp>(
                                response.errorBody()!!.string()
                            ).data?.error!!
                        )
                    )
            } catch (ex: Exception) {
                Log.e("ArticleViewModel", ex.message!!)
                emit(
                    Resource.error(
                        data = null,
                        message = "Unable to retrieve articles at the moment, please try again later."
                    )
                )
            }
        }

}