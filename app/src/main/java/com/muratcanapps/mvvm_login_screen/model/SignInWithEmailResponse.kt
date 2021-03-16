package com.muratcanapps.mvvm_login_screen.model

import com.google.gson.annotations.SerializedName

data class SignInWithEmailResponse(
    @SerializedName("kind")
    var kind: String = "",

    @SerializedName("localId")
    var localId: String = "",

    @SerializedName("email")
    var email: String = "",

    @SerializedName("displayName")
    var displayName: String = "",

    @SerializedName("idToken")
    var idToken: String = "",

    @SerializedName("registered")
    var registered: Boolean = false,

    @SerializedName("refreshToken")
    var refreshToken: String = "",

    @SerializedName("expiresIn")
    var expiresIn: String = ""
)