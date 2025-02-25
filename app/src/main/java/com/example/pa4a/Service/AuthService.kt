package com.example.pa4a.service

import com.example.pa4a.dataModel.LoginRequest
import com.example.pa4a.dataModel.LoginResponse
import com.example.pa4a.dataModel.RegisterRequest
import com.example.pa4a.dataModel.RegisterResponse
import com.example.pa4a.dataModel.UserIdRequest
import com.example.pa4a.dataModel.UserIdResponse
import com.example.pa4a.dataModel.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.DELETE
import retrofit2.http.GET

interface AuthService {
    @POST("auth/")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("register/")
    fun subscribe(@Body subscriptionRequest: RegisterRequest): Call<RegisterResponse>

    @POST("device/")
    fun userId(@Body userIdRequest: UserIdRequest): Call<UserIdResponse>

    @GET("users/{id}")
    fun getUser(@Path("id") id: String): Call<UserResponse>

}


