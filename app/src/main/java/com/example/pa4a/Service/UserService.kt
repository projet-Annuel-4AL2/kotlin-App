package com.example.pa4a.service

import com.example.pa4a.dataModel.LoginRequest
import com.example.pa4a.dataModel.LoginResponse
import com.example.pa4a.dataModel.RegisterRequest
import com.example.pa4a.dataModel.RegisterResponse
import com.example.pa4a.dataModel.UserFollowersResponse
import com.example.pa4a.dataModel.UserFollowingsResponse
import com.example.pa4a.dataModel.UserIdRequest
import com.example.pa4a.dataModel.UserIdResponse
import com.example.pa4a.dataModel.UserResponse
import com.example.pa4a.dataModel.UsersResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.DELETE
import retrofit2.http.GET

interface UserService {
    @GET("users/")
    fun getUsers(): Call<List<UserResponse>>

    @GET("users/{id}/followers/")
    fun getUserFollowers(@Path("id") id: String): Call<UserFollowersResponse>

    @GET("users/{id}/followings/")
    fun getUserFollowings(@Path("id") id: String): Call<UserFollowingsResponse>


}