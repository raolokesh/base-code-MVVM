package com.vaaan.testingjetpack.network

import android.util.Log
import com.vaaaninfra.iptmsmobileapp.utils.ApiException
import com.vaaaninfra.iptmsmobileapp.utils.NoInternetException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

abstract class SafeApiRequest {

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        try {
            val response = call.invoke()
            if (response.isSuccessful) {
                return response.body()!!
            } else {
                val error = response.errorBody()?.string()
                val message = StringBuilder()
                error?.let {
                    try {
                        message.append(JSONObject(it).getString("message"))
                    } catch (e: JSONException) {
                        message.append("\n")
                    }

                }
                message.append("Error Code: ${response.code()}")
                throw ApiException(message.toString())
            }
        } catch (e: Exception) {
            Log.d("Error", e.message.toString())
            throw NoInternetException(e.message.toString())
        }

    }
}