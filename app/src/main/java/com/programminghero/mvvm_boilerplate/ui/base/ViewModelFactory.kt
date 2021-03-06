package com.programminghero.mvvm_boilerplate.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.programminghero.mvvm_boilerplate.data.repository.AuthRepository
import com.programminghero.mvvm_boilerplate.data.repository.BaseRepository
import com.programminghero.mvvm_boilerplate.data.repository.UserRepository
import com.programminghero.mvvm_boilerplate.ui.auth.AuthViewModel
import com.programminghero.mvvm_boilerplate.ui.home.HomeViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
        private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository as UserRepository) as T
            else -> throw IllegalArgumentException("ViewModelClass is Not Found")
        }
    }
}