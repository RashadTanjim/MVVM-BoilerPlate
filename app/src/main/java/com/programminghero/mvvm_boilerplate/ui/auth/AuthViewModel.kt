package com.programminghero.mvvm_boilerplate.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.programminghero.mvvm_boilerplate.data.network.Resource
import com.programminghero.mvvm_boilerplate.data.repository.AuthRepository
import com.programminghero.mvvm_boilerplate.data.responses.LoginResponse
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
) : ViewModel() {

    private val _loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse

    fun login(
        email: String,
        password: String
    ) = viewModelScope.launch {
        _loginResponse.value = repository.login(email, password)
    }

    fun saveAuthToken(token: String) = viewModelScope.launch {
        repository.saveAuthToken(token)
    }
}