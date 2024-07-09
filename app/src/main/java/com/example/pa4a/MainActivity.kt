package com.example.pa4a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.pa4a.core.SessionManager
import com.example.pa4a.view.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sessionManager = SessionManager(this)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        //afficher tout ce qui est dans la session
        println("${sessionManager}")

        val user = sessionManager.userInfo
        val users = sessionManager.users
        println("User info: $user")
        println("User all: $users")

    }
//recuperer le token en session et ensuite utiliser la function userId
    //pour recuperer l'id de l'utilisateur
    //et ensuite utiliser getUser pour recuperer les informations de l'utilisateur

}



