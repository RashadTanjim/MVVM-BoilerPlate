package com.programminghero.mvvm_boilerplate.ui

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.programminghero.mvvm_boilerplate.data.network.Resource
import com.programminghero.mvvm_boilerplate.ui.auth.LoginFragment
import com.programminghero.mvvm_boilerplate.ui.base.BaseFragment

fun <A : Activity> Activity.startNewActivity(activity: Class<A>) {
    Intent(this, activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}

fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View.enable(enabled: Boolean) {
    isEnabled = enabled
    alpha = if (enabled) 1f else 0.5f
}

fun View.snackbar(message: String, action: (() -> Unit)? = null) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    action?.let {
        snackbar.setAction("Retry") {
            it()
        }
    }
}

fun Fragment.handleApiError(
        failure: Resource.Failure,
        retry: ( () -> Unit)? = null
) {
    when{
        failure.isNetworkError -> requireView().snackbar("Please check your internet connection", retry)
//        failure.errorCode = 401 -> {
//            if(this is LoginFragment) {
//                requireView().snackbar("You have entered incorrect email or password")
//            } else {
//                (this as BaseFragment<*,*,*>).logout()
//            }
//        }
        else -> {
            val error = failure.errorBody?.string().toString()
            requireView().snackbar(error)
        }
    }
}