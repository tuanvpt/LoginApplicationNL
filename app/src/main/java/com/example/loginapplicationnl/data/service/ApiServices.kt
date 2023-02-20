package com.example.loginapplicationnl.data.service

import com.example.loginapplicationnl.data.model.LoginDataModel
import com.example.loginapplicationnl.data.model.LoginResponses
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServices {
    @POST("auth/signup")
    fun postSignIn(@Body request: LoginDataModel): Call<LoginResponses>

}