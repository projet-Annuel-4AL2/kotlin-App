package com.example.pa4a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pa4a.core.SessionManager

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val sessionManager = SessionManager(application)
        val token = sessionManager.token
        println("Token: $token")


    }

}