package com.example.loginapplicationnl.data.repository

import com.example.loginapplicationnl.data.model.LoginDataModel
import com.example.loginapplicationnl.data.service.ApiServices

class LoginRepository(private val apiServices: ApiServices ) {

    fun signIn(request: LoginDataModel) = apiServices.postSignIn(request)

}