package com.programminghero.mvvm_boilerplate.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.programminghero.mvvm_boilerplate.databinding.FragmentLoginBinding
import com.programminghero.mvvm_boilerplate.data.network.AuthApi
import com.programminghero.mvvm_boilerplate.data.network.Resource
import com.programminghero.mvvm_boilerplate.data.repository.AuthRepository
import com.programminghero.mvvm_boilerplate.ui.base.BaseFragment
import com.programminghero.mvvm_boilerplate.ui.enable
import com.programminghero.mvvm_boilerplate.ui.handleApiError
import com.programminghero.mvvm_boilerplate.ui.home.HomeActivity
import com.programminghero.mvvm_boilerplate.ui.startNewActivity
import com.programminghero.mvvm_boilerplate.ui.visible
import kotlinx.coroutines.launch


class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.progressbar.visible(false)
        binding.buttonLogin.enable(false)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            binding.progressbar.visible(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        viewModel.saveAuthToken(it.value.user.access_token!!)  //TODO .toString should be removed
                        requireActivity().startNewActivity(HomeActivity::class.java)
                    }
                }
                is Resource.Failure -> handleApiError(it)
            }
        })

        binding.editTextTextPassword.addTextChangedListener {
            val email = binding.editTextTextEmailAddress.text.toString().trim()
            binding.buttonLogin.enable(email.isNotEmpty() && it.toString().isNotEmpty())
        }

        binding.buttonLogin.setOnClickListener {
            val email = binding.editTextTextEmailAddress.text.toString().trim()
            val password = binding.editTextTextPassword.text.toString().trim()

            //@TODO ADD input validations
            viewModel.login(email, password)
        }

    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreferences)
}