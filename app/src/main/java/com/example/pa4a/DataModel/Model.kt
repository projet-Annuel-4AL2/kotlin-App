package com.example.pa4a.dataModel
data class LoginRequest(val username: String, val password: String , val device : String)
data class LoginResponse(val token: String)

data class RegisterRequest(val email: String,  val username: String, val password: String , val  device : String)
data class RegisterResponse(val token: String)


data class UserIdRequest(val token_device: String)
data class UserIdResponse(val user_id: String)