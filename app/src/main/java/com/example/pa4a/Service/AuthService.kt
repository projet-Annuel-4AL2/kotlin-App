package com.example.pa4a.service

import com.example.pa4a.dataModel.LoginRequest
import com.example.pa4a.dataModel.LoginResponse
import com.example.pa4a.dataModel.RegisterRequest
import com.example.pa4a.dataModel.RegisterResponse
import com.example.pa4a.dataModel.UserIdRequest
import com.example.pa4a.dataModel.UserIdResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/auth/")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("api/register/")
    fun subscribe(@Body subscriptionRequest: RegisterRequest): Call<RegisterResponse>

    @POST("api/device/")
    fun userId(@Body userIdRequest: UserIdRequest): Call<UserIdResponse>
}


