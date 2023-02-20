package com.example.loginapplicationnl.ui.login

import androidx.lifecycle.MutableLiveData
import com.example.loginapplicationnl.BuildConfig
import com.example.loginapplicationnl.base.BaseViewModel
import com.example.loginapplicationnl.data.model.LoginDataModel
import com.example.loginapplicationnl.data.model.LoginResponses
import com.example.loginapplicationnl.data.repository.LoginRepository
import com.example.loginapplicationnl.data.service.ApiServices
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class LoginViewModel(private val loginRepository: LoginRepository) : BaseViewModel(),
    Callback<LoginResponses> {

    val signIn: MutableLiveData<LoginResponses>
        get() = signIn

    override val isLoading: MutableLiveData<Boolean>
        get() = super.isLoading

    fun signInMethod(email: String, password: String) {
        val request = LoginDataModel(email, password)
        isLoading.value = true
        loginRepository.signIn(request).enqueue(this)
    }

    override fun onResponse(call: Call<LoginResponses>, response: Response<LoginResponses>) {
        if(response.isSuccessful) {
            signIn.value = response.body()
        }else{

        }
        isLoading.value = false
    }

    override fun onFailure(call: Call<LoginResponses>, t: Throwable) {
        isLoading.value = false
    }

    // Create viewModel module
    val signInViewModel = module {
        viewModel {
            LoginViewModel(get())
        }
    }

    // create repository module
    val signInRepository = module {
        single {
            LoginRepository(get())
        }
    }

    // create api module
    val signInApi = module {
        fun provideSignInApi(retrofit: Retrofit) : ApiServices {
            return retrofit.create(ApiServices::class.java)
        }

        single {
            provideSignInApi(get())
        }
    }

    // create retrofit model
    val retrofitModule = module {

        fun provideGson(): Gson {
            return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
        }

        fun provideHttpClient(): OkHttpClient {
            val okHttpClientBuilder = OkHttpClient.Builder()

            return okHttpClientBuilder.build()
        }

        fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(factory))
                .client(client)
                .build()
        }

        single { provideGson() }
        single { provideHttpClient() }
        single { provideRetrofit(get(), get()) }
    }
}