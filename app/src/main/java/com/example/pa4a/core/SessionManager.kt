package com.example.pa4a.core

import android.content.Context

class SessionManager(context: Context) {
    private val prefs = context.getSharedPreferences("session", Context.MODE_PRIVATE)

    var token: String?
        get() = prefs.getString("token", null)
        set(value) = prefs.edit().putString("token", value).apply()

    var userId: String?
        get()= prefs.getString("user_id", null)
        set(value) = prefs.edit().putString("user_id", value).apply()
}