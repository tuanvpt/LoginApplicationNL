package com.example.loginapplicationnl.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.loginapplicationnl.base.BaseViewModel
import com.example.loginapplicationnl.data.model.LoginRequest
import com.example.loginapplicationnl.data.model.LoginResponses
import com.example.loginapplicationnl.data.repository.LoginRepository
import com.example.loginapplicationnl.utils.LogUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val loginRepository: LoginRepository) : BaseViewModel(),
    Callback<LoginResponses> {

    /*var loginResponses: LoginResponses =
        LoginResponses(id = "", email = "", false, firstName = "", "", "", "", "");*/

    private val _login = MutableLiveData<LoginResponses>()
    val login: LiveData<LoginResponses>
        get() = _login

    fun loginUser(email: String, pwd: String) {
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val loginRequest = LoginRequest(
                    password = pwd,
                    email = email
                )
                loginRepository.postLogin(loginRequest);
            } catch (ex: Exception) {
                /* loginResult.value = BaseResponse.Error(ex.message)*/
                ex.message?.let { LogUtil.e(it) }
            }
        }
    }

    override fun onResponse(
        call: retrofit2.Call<LoginResponses>,
        response: Response<LoginResponses>
    ) {
        if (response.isSuccessful) {
            _login.value = response.body()
        } else {
            LogUtil.e("Loi roi")
        }
    }

    override fun onFailure(call: retrofit2.Call<LoginResponses>, t: Throwable) {
        LogUtil.e(t.message.toString())
    }
}