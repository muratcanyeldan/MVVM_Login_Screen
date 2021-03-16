package com.muratcanapps.mvvm_login_screen.model

import com.google.gson.annotations.SerializedName

data class Errors(
    @SerializedName("domain")
    var domain : String = "",

    @SerializedName("reason")
    var reason : String = "",

    @SerializedName("message")
    var message : String =""

)
