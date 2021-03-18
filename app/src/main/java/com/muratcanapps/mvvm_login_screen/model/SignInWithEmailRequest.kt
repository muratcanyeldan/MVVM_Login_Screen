package com.muratcanapps.mvvm_login_screen.model

import com.google.gson.annotations.SerializedName

data class SignInWithEmailRequest(
    /*
    Alternative = " ... " kullanılabiliyor
    Bu sayede field'ın birden fazla ismi olabiliyorsa
    username , email gibi ikisinin aynı değişkene
    atılması sağlanabiliyor.
     */
    @SerializedName("email")
    var email: String,

    @SerializedName("password")
    var password: String,

    @SerializedName("returnSecureToken")
    var returnSecureToken: Boolean = true

)