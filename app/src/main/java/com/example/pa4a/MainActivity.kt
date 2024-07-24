package com.example.pa4a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pa4a.Fragment.HeaderFragment
import com.example.pa4a.Fragment.HomeFragment
import com.example.pa4a.Fragment.NavigationFragment
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

        // Load Header and Navigation fragments
        supportFragmentManager.beginTransaction()
            .replace(R.id.header_container, HeaderFragment())
            .replace(R.id.navigation_container, NavigationFragment())
            .commit()

        // Load initial fragment (HomeFragment)
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }
    }

    /*fun replaceFragment(fragment: HomeFragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_content_container, fragment)
            .commit()
    }*/

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_content_container, fragment)
            .commit()
    }

}



