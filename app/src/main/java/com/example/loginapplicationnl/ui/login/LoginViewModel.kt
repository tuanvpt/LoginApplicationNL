package com.example.loginapplicationnl.ui.login

import androidx.lifecycle.viewModelScope
import com.example.loginapplicationnl.base.BaseViewModel
import com.example.loginapplicationnl.data.model.LoginRequest
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel() {

    /* val userRepo = UserRepository()
     val loginResult: MutableLiveData<BaseResponse<LoginResponse>> = MutableLiveData()*/

    fun loginUser(email: String, pwd: String) {
        isLoading.value = true
        viewModelScope.launch {
            try {
                val loginRequest = LoginRequest(
                    password = pwd,
                    email = email
                )
                /*   val response = userRepo.loginUser(loginRequest = loginRequest)
                   if (response?.code() == 200) {
                       loginResult.value = BaseResponse.Success(response.body())
                   } else {
                       loginResult.value = BaseResponse.Error(response?.message())
                   }*/

            } catch (ex: Exception) {
                /* loginResult.value = BaseResponse.Error(ex.message)*/
            }
        }
    }
}