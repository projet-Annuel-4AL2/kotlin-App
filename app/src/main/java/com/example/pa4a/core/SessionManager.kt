package com.example.pa4a.core

import android.content.Context
import com.example.pa4a.dataModel.UserResponse
import com.google.gson.Gson

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

   // override fun toString(): String {
    //    return "SessionManager(token=$token, userId=$userId, userInfo=$userInfo, users=$users)"
    //}
}