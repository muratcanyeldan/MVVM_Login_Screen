package com.muratcanapps.mvvm_login_screen.model

import com.google.gson.annotations.SerializedName

data class Errors(
    @SerializedName("message")
    var message : String ="",

    @SerializedName("domain")
    var domain : String = "",

    @SerializedName("reason")
    var reason : String = ""

)