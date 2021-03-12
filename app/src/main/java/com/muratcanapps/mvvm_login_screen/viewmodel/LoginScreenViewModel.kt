package com.muratcanapps.mvvm_login_screen.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.muratcanapps.mvvm_login_screen.extentions.toast
import com.muratcanapps.mvvm_login_screen.model.SignInWithEmailRequest
import com.muratcanapps.mvvm_login_screen.model.SignInWithEmailResponse
import com.muratcanapps.mvvm_login_screen.network.LoginService
import com.muratcanapps.mvvm_login_screen.network.ServiceGenerator
import com.muratcanapps.mvvm_login_screen.utils.isEmailValid
import com.muratcanapps.mvvm_login_screen.utils.isOnline
import com.muratcanapps.mvvm_login_screen.utils.isPasswordValid
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginScreenViewModel(application: Application) : AndroidViewModel(application) {
    val loginSuccessLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun showMessage(context: Context, success: Boolean) {
        if (success) {
            context.toast("Login Successful")
        } else {
            context.toast("Login Failed")
        }
    }

    fun login(context: Context, email: String, password: String) {
        if (isOnline(context)) {
            if (isEmailValid(email) && isPasswordValid(password)) {
                loginAuth(context, email, password)
            } else {
                loginSuccessLiveData.postValue(false)
            }
        } else {
            loginSuccessLiveData.postValue(false)
        }
    }

    private fun loginAuth(context: Context, username: String, password: String) {

        val loginService =
            ServiceGenerator.createService(LoginService::class.java, username, password)
        val call: Call<SignInWithEmailResponse> =
            loginService.loginWithEmail(SignInWithEmailRequest(username, password))

        call.enqueue(object : Callback<SignInWithEmailResponse> {
            override fun onResponse(
                call: Call<SignInWithEmailResponse>,
                response: Response<SignInWithEmailResponse?>
            ) {
                loginSuccessLiveData.postValue(response.isSuccessful)

            }

            override fun onFailure(call: Call<SignInWithEmailResponse>, t: Throwable) {
                context.toast("Error " + t.localizedMessage)
            }
        })
    }
}