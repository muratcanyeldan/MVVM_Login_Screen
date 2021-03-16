package com.muratcanapps.mvvm_login_screen.model

import com.google.gson.annotations.SerializedName

data class SignInWithEmailError(
    @SerializedName("errors")
    var errors: Errors = Errors(),

    @SerializedName("code")
    var code: Int = 0,

    @SerializedName("message")
    var message: String =""

)