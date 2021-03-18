package com.muratcanapps.mvvm_login_screen.utils

import com.muratcanapps.mvvm_login_screen.extentions.isEmailFormatValid
import com.muratcanapps.mvvm_login_screen.extentions.isNull

fun isEmailValid(email: String?): Boolean {
    return if (!email.isNull()) {
        email!!.isEmailFormatValid()
    } else {
        false
    }
}

fun isPasswordValid(password: String?): Boolean {
    return if (!password.isNull()) {
        password!!.length >= 6
    } else {
        false
    }
}