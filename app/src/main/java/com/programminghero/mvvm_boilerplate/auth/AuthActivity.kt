package com.programminghero.mvvm_boilerplate.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.programminghero.mvvm_boilerplate.R

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }
}