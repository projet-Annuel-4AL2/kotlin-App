package com.example.pa4a.core

import android.content.Context
import com.example.pa4a.dataModel.GroupResponse
import com.example.pa4a.dataModel.UserFollowersResponse
import com.example.pa4a.dataModel.UserFollowingsResponse
import com.example.pa4a.dataModel.UserPostResponse
import com.example.pa4a.dataModel.UserPostsResponse
import com.example.pa4a.dataModel.UserResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SessionManager(context: Context) {
    private val prefs = context.getSharedPreferences("session", Context.MODE_PRIVATE)

    var token: String?
        get() = prefs.getString("token", null)
        set(value) = prefs.edit().putString("token", value).apply()

    var userId: String?
        get()= prefs.getString("user_id", null)
        set(value) = prefs.edit().putString("user_id", value).apply()

    fun saveUserInfo(user: UserResponse) {
        val editor = prefs.edit()
        editor.putString("USER_INFO", Gson().toJson(user))
        editor.apply()
    }

    val userInfo: UserResponse?
        get() {
            val json = prefs.getString("USER_INFO", null)
            return Gson().fromJson(json, UserResponse::class.java)
        }


    fun saveUsers(users: List<UserResponse>) {
        val editor = prefs.edit()
        editor.putString("USERS", Gson().toJson(users))
        editor.apply()
    }


    val users: List<UserResponse>?
        get() {
            val json = prefs.getString("USERS", null)
            return if (json != null) {
                Gson().fromJson(json, Array<UserResponse>::class.java).toList()
            } else {
                emptyList()
            }
        }


    fun saveGroups(groups: List<GroupResponse>) {
        val editor = prefs.edit()
        editor.putString("GROUPS", Gson().toJson(groups))
        editor.apply()
    }

    val groups: List<GroupResponse>?
        get() {
            val json = prefs.getString("GROUPS", null)
            return if (json != null) {
                Gson().fromJson(json, Array<GroupResponse>::class.java).toList()
            } else {
                emptyList()
            }
        }



    fun saveUserFollowers(followers: UserFollowersResponse) {
        val editor = prefs.edit()
        editor.putString("USER_FOLLOWERS", Gson().toJson(followers))
        editor.apply()
    }

    val userFollowers: List<UserResponse>?
        get() {
            val json = prefs.getString("USER_FOLLOWERS", null)
            return if (json != null) {
                val userFollowersResponse = Gson().fromJson(json, UserFollowersResponse::class.java)
                userFollowersResponse.followers
            } else {
                emptyList()
            }
        }

    fun saveUserFollowings(followings: UserFollowingsResponse) {
        val editor = prefs.edit()
        editor.putString("USER_FOLLOWINGS", Gson().toJson(followings))
        editor.apply()
    }

    val userFollowings: List<UserResponse>?
        get() {
            val json = prefs.getString("USER_FOLLOWINGS", null)
            return if (json != null) {
                val userFollowingsResponse = Gson().fromJson(json, UserFollowingsResponse::class.java)
                userFollowingsResponse.followings
            } else {
                emptyList()
            }
        }



    fun getDataBasics(): List<Any?> {
        val user = userInfo
        val users = users
        val groups = groups
        val userFollowers = userFollowers
        val userFollowings = userFollowings

        return listOf(user, users, groups, userFollowers, userFollowings)
    }

    fun saveUserPost(post: UserResponse) {
        val editor = prefs.edit()
        editor.putString("USER_POST", Gson().toJson(post))
        editor.apply()
    }

    val userPost: UserResponse?
        get() {
            val json = prefs.getString("USER_POST", null)
            return Gson().fromJson(json, UserResponse::class.java)
        }

    fun saveUserPosts(posts: List<UserPostResponse>) {
        val editor = prefs.edit()
        editor.putString("USER_POSTS", Gson().toJson(posts))
        editor.apply()
    }

    val userPosts: List<UserPostResponse>?
        get() {
            val json = prefs.getString("USER_POSTS", null)
            return Gson().fromJson(json, object : TypeToken<List<UserPostResponse>>() {}.type)
        }


    fun saveUserPostsByUsername(posts: List<UserPostResponse>) {
        val editor = prefs.edit()
        editor.putString("USER_POSTS_BY_USERNAME", Gson().toJson(posts))
        editor.apply()
    }

    val userPostsByUsername: List<UserPostResponse>?
        get() {
            val json = prefs.getString("USER_POSTS_BY_USERNAME", null)
            return Gson().fromJson(json, object : TypeToken<List<UserPostResponse>>() {}.type)
        }
}