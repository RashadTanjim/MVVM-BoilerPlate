package com.programminghero.mvvm_boilerplate.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.programminghero.mvvm_boilerplate.data.network.Resource
import com.programminghero.mvvm_boilerplate.data.network.UserApi
import com.programminghero.mvvm_boilerplate.data.repository.UserRepository
import com.programminghero.mvvm_boilerplate.data.responses.User
import com.programminghero.mvvm_boilerplate.databinding.FragmentHomeBinding
import com.programminghero.mvvm_boilerplate.ui.base.BaseFragment
import com.programminghero.mvvm_boilerplate.ui.visible
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding, UserRepository>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressbar.visible(false)

        viewModel.getUser()

        viewModel.user.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    binding.progressbar.visible(false)
                    updateUI(it.value.user)
                }
                is Resource.Loading -> {
                    binding.progressbar.visible(true)
                }
            }
        })
    }

    private fun updateUI(user: User) {
        binding.textViewId

    }

    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(
            inflater: LayoutInflater,
            container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)


    override fun getFragmentRepository(): UserRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(UserApi::class.java, token)
        return UserRepository(api)
    }

}

