package com.example.pa4a.service

import com.example.pa4a.dataModel.GroupResponse
import com.example.pa4a.dataModel.LoginRequest
import com.example.pa4a.dataModel.LoginResponse
import com.example.pa4a.dataModel.RegisterRequest
import com.example.pa4a.dataModel.RegisterResponse
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

interface GroupService {
    @GET("groupe/")
    fun getGroups(): Call<List<GroupResponse>>

}