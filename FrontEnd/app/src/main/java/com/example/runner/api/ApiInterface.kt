package com.example.runner.api

import com.example.runner.BASE_URL
import com.example.runner.model.PagedArticlesData
import com.example.runner.model.User
import com.example.runner.model.modelRequest.LoginRequest
import com.example.runner.model.modelResponse.LoginResponse
import com.example.runner.model.modelResponse.SignupResponse
import com.example.runner.view.LoginActivity
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.HashMap

interface ApiInterface {

     @Headers("Content-Type:application/json")
     @POST("/login")
     fun executeLogin(@Body map: HashMap<String?, String?>?): Call<LoginResponse>?

     @Headers("Content-Type:application/json")
     @POST("/Signup")
     fun executeSignup(@Body map: HashMap<String?, String?>?): Call<SignupResponse>?


     @GET("articles")
     suspend fun downloadArticles(@Query("page") pageNumber: Int? = 1): Response<PagedArticlesData>
}