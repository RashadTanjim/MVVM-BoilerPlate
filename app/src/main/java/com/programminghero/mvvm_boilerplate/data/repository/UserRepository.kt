package com.programminghero.mvvm_boilerplate.data.repository

import com.programminghero.mvvm_boilerplate.data.UserPreferences
import com.programminghero.mvvm_boilerplate.data.network.AuthApi
import com.programminghero.mvvm_boilerplate.data.network.UserApi

class UserRepository(
    private val api: UserApi
) : BaseRepository() {

    suspend fun getUser() = safeApiCall {
        api.getUser()
    }

}