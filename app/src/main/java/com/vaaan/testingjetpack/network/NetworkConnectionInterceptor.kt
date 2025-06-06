package com.vaaan.testingjetpack.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.vaaaninfra.iptmsmobileapp.utils.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(context: Context) : Interceptor {

    private val applicationContext = context.applicationContext
    override fun intercept(chain: Interceptor.Chain): Response {

        if (!isInternetAvailable()){
            throw NoInternetException("Make sure you have an active data connection")
        }
        return chain.proceed(chain.request())
    }


    private fun isInternetAvailable(): Boolean {
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        connectivityManager?.let {
            val network = it.activeNetwork ?: return false

            val networkCapabilities = it.getNetworkCapabilities(network) ?: return false

            return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        }
        return false

    }
}