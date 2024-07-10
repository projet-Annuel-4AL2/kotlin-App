package com.example.pa4a.dataModel

import com.google.gson.annotations.SerializedName

data class LoginRequest(val username: String, val password: String , val device : String)
data class LoginResponse(val token: String)

data class RegisterRequest(val email: String,  val username: String, val password: String , val  device : String)
data class RegisterResponse(val token: String)


data class UserIdRequest(val token_device: String)
data class UserIdResponse(val user_id: String)


data class UserResponse(
    @SerializedName("id") val id: String,
    @SerializedName("username") val username: String,
    @SerializedName("email") val email: String,
    @SerializedName("is_active") val isActive: Boolean,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("role") val role: String,
    @SerializedName("profile_pic") val profilePic: String?,
    @SerializedName("followers") val followers: List<String>,
    @SerializedName("bio") val bio: String?
)

data class UsersResponse(
    val users: List<UserResponse>
)