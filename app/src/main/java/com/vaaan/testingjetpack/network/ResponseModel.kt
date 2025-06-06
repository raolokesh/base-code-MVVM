package com.vaaan.testingjetpack.network


import com.google.gson.annotations.SerializedName

data class ResponseModel<T> (
    @SerializedName("Data")
    var `data`:T? = null,
    @SerializedName("Message")
    val message: String,
    @SerializedName("StatusCode")
    val statusCode: Int
)