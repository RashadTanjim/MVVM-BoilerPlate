package com.programminghero.mvvm_boilerplate.ui.base

import androidx.lifecycle.ViewModel
import com.programminghero.mvvm_boilerplate.data.network.UserApi
import com.programminghero.mvvm_boilerplate.data.repository.BaseRepository

abstract class BaseViewModel(
    private val repository: BaseRepository
) : ViewModel() {

    suspend fun logout(api: UserApi) = repository.logout(api)
}