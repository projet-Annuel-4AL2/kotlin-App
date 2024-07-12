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

data class GroupResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("admin") val admin: String,
    @SerializedName("description") val description: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("members") val members: List<String>,
    @SerializedName("group_pic") val groupPic: String?,
    @SerializedName("author") val author: String
)


data class GroupsResponse(
    val groups: List<GroupResponse>
)
data class UserFollowersResponse(
    val followers: List<UserResponse>
)

data class UserFollowingsResponse(
    val followings: List<UserResponse>
)


data class  UserPostResponse(
    @SerializedName("id") val id: String,
    @SerializedName("likes") val likes: List<UserResponse>,
    @SerializedName("comments") val comments: List<UserResponse>,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("content") val content: String,
    @SerializedName("code") val code: String,
    @SerializedName("language") val language: String,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("image") val image: String?,
    @SerializedName("author") var author: String

)

data class UserPostsResponse(
    val posts: List<UserPostResponse>
)