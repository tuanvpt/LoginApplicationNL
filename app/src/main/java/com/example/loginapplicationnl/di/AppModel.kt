package com.example.loginapplicationnl.di

import com.example.loginapplicationnl.data.repository.LoginRepositoryImp
import com.example.loginapplicationnl.ui.login.LoginViewModel
import com.example.loginapplicationnl.utils.Constant
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { provideGson() }
    single { provideRetrofit(get(), get()) }
    single { createOkHttpClient() }
}

val viewModelModule = module {
    viewModel {
        LoginViewModel(get())
    }
}

val repositoryModule = module {
    single { LoginRepositoryImp(get()) }
}

private const val TIME_OUT = 30L

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}

fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit? {
    return Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

fun provideGson(): Gson? {
    val gsonBuilder = GsonBuilder()
    return gsonBuilder.create()
}