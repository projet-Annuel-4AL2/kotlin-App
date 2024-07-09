package com.example.pa4a.api
import com.example.pa4a.service.AuthService
import com.example.pa4a.service.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "http://10.0.2.2:80/api/"

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val authService: AuthService by lazy {
        retrofit.create(AuthService::class.java)
    }

    val userService: UserService by lazy {
        retrofit.create(UserService::class.java)
    }

}