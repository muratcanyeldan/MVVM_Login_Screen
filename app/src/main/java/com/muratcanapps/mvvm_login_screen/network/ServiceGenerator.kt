package com.muratcanapps.mvvm_login_screen.network

import android.text.TextUtils
import okhttp3.Credentials
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ServiceGenerator {

    private val httpClient = OkHttpClient.Builder()
    private val builder = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    private var retrofit: Retrofit = builder.build()

    fun <S> createService(
        serviceClass: Class<S>, email: String, password: String
    ): S {
        val authToken: String = Credentials.basic(email, password)
        return createService(serviceClass, authToken)
    }

    private fun <S> createService(
        serviceClass: Class<S>, authToken: String
    ): S {
        if (!TextUtils.isEmpty(authToken)) {
            val interceptor = AuthenticationInterceptor(authToken)
            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor)
                builder.client(httpClient.build())
                builder.addConverterFactory(GsonConverterFactory.create())
                retrofit = builder.build()
            }
        }
        return retrofit.create(serviceClass)
    }
}