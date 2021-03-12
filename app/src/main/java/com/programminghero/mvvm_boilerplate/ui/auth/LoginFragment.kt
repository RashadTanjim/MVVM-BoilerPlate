package com.programminghero.mvvm_boilerplate.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.programminghero.mvvm_boilerplate.R
import com.programminghero.mvvm_boilerplate.databinding.FragmentLoginBinding
import com.programminghero.mvvm_boilerplate.network.AuthApi
import com.programminghero.mvvm_boilerplate.repository.AuthRepository
import com.programminghero.mvvm_boilerplate.ui.base.BaseFragment


class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) =  FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = AuthRepository(remoteDataSource.buildApi(AuthApi::class.java))
}