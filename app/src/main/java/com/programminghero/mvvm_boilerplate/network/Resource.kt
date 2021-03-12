package com.programminghero.mvvm_boilerplate.network

import okhttp3.ResponseBody

sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCOde: Int?,
        val errorBody: ResponseBody?
    )
}