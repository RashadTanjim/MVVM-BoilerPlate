package com.programminghero.mvvm_boilerplate.repository

import com.programminghero.mvvm_boilerplate.network.AuthApi

class AuthRepository(
    private val api: AuthApi
) : BaseRepository() {

    suspend fun login(
        email: String,
        password: String
    ) = safeApiCall {
        api.login(email, password)
    }
}