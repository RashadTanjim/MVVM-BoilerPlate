package com.programminghero.mvvm_boilerplate.data.network

import com.programminghero.mvvm_boilerplate.data.responses.LoginResponse
import retrofit2.http.GET

interface UserApi {

    @GET("user")
    suspend fun getUser(): LoginResponse
}