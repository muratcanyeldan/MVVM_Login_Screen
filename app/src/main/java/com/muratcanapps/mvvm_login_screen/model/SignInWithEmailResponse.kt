package com.muratcanapps.mvvm_login_screen.model

import com.google.gson.annotations.SerializedName

data class SignInWithEmailResponse(
    @SerializedName("idToken")
    var idToken: String ="",

    @SerializedName("email")
    var email: String = "",

    @SerializedName("refreshToken")
    var refreshToken: String ="",

    @SerializedName("expiresIn")
    var expiresIn: String= "",

    @SerializedName("localId")
    var localId: String ="",

    @SerializedName("registered")
    var registered: Boolean =false
)