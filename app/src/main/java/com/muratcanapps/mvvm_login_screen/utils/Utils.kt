package com.muratcanapps.mvvm_login_screen.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.muratcanapps.mvvm_login_screen.extentions.isEmailFormatValid
import com.muratcanapps.mvvm_login_screen.extentions.isNull

fun isOnline(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capabilities =
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    if (capabilities != null) {
        when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            }
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            }
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
    }
    return false
}

fun isEmailValid(email:String?): Boolean{
    return if(!email.isNull()){
        email!!.isEmailFormatValid()
    }
    else{
        false
    }
}

fun isPasswordValid(password:String?):Boolean{
    return if(!password.isNull()){
        password!!.length >= 6
    }
    else{
        false
    }
}