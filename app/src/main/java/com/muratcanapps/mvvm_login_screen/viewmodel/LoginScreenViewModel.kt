package com.muratcanapps.mvvm_login_screen.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.muratcanapps.mvvm_login_screen.model.SignInWithEmailRequest
import com.muratcanapps.mvvm_login_screen.model.SignInWithEmailResponse
import com.muratcanapps.mvvm_login_screen.network.LoginService
import com.muratcanapps.mvvm_login_screen.network.ServiceGenerator
import com.muratcanapps.mvvm_login_screen.utils.isEmailValid
import com.muratcanapps.mvvm_login_screen.utils.isPasswordValid
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginScreenViewModel(application: Application) : AndroidViewModel(application) {
    val loginSuccessLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val loginErrorLiveData: MutableLiveData<String> = MutableLiveData()
    val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    var account: SignInWithEmailResponse? = null

    fun login(email: String, password: String) {
        setLoadingStatus(true)
        if (isEmailValid(email) && isPasswordValid(password)) {
            account = loginAuth(email, password)
        } else {
            setLoadingStatus(false)
            transmitResponseToView(
                false,
                "Email or Password Format Invalid"
            )
        }
    }
    //string resource kullan

    fun loginAuth(email: String, password: String): SignInWithEmailResponse? {

        var loggedInAccount: SignInWithEmailResponse? = null
        val loginService =
            ServiceGenerator.createService(LoginService::class.java, email, password)
        val call: Call<Any?> =
            loginService.loginWithEmail(SignInWithEmailRequest(email, password))

        call.enqueue(object : Callback<Any?> {
            override fun onResponse(
                call: Call<Any?>,
                response: Response<Any?>
            ) {

                if (response.isSuccessful) {
                    transmitResponseToView(true)
                    val successfulResponseGson = Gson().toJson(response.body())
                    val successfulResponse = Gson().fromJson(
                        successfulResponseGson,
                        SignInWithEmailResponse::class.java
                    )
                    loggedInAccount = successfulResponse
                    Log.d("responseBu", successfulResponse.email)
                } else {
                    val errorResponseFull = response.errorBody()?.string()
                    val errorResponseMessage = errorResponseFull?.substringAfter(""""message": """")
                        ?.substringBefore("""",""")
                    transmitResponseToView(false, errorResponseMessage)
                }
            }

            override fun onFailure(call: Call<Any?>, t: Throwable) {
                transmitResponseToView(false, "API endpoint can't be reached")
            }
        })
        setLoadingStatus(false)
        return loggedInAccount
    }

    private fun transmitResponseToView(status: Boolean, message: String? = "") {
        loginErrorLiveData.postValue(message ?: "")
        loginSuccessLiveData.postValue(status)
    }

    private fun setLoadingStatus(status: Boolean) {
        loadingLiveData.postValue(status)
    }
}