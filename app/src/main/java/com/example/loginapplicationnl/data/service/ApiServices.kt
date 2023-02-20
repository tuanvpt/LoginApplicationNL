package com.example.loginapplicationnl.data.service

import com.example.loginapplicationnl.data.model.LoginDataModel
import com.example.loginapplicationnl.data.model.LoginResponses
import com.example.loginapplicationnl.utils.Constant
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServices {
    @POST("/${Constant.BASE_URL_SIGNIN}")
    suspend fun postSignIn(@Body request:LoginDataModel ) : Response<LoginResponses>

}