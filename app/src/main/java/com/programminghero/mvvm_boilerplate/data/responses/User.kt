package com.programminghero.mvvm_boilerplate.data.responses

data class User(
    val access_token: String?,
    val created_at: String,
    val email: String,
    val email_verified_at: Any,
    val id: Int,
    val name: String,
    val updated_at: String
)