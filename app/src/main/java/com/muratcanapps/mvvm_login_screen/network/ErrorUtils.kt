package com.muratcanapps.mvvm_login_screen.network

import com.muratcanapps.mvvm_login_screen.model.SignInWithEmailError
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException


object ErrorUtils {
    fun parseError(response: Response<*>): SignInWithEmailError {
        val converter: Converter<ResponseBody, SignInWithEmailError> = ServiceGenerator.retrofit.responseBodyConverter(SignInWithEmailError::class.java, arrayOfNulls<Annotation>(0))
        return try {
            converter.convert(response.errorBody()!!)!!
        } catch (e: IOException) {
            return SignInWithEmailError()
        }
    }
}