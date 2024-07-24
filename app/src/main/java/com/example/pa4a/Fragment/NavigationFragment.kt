package com.example.pa4a.Fragment


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pa4a.MainActivity
import com.example.pa4a.R
import com.example.pa4a.UserProfileActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavigationFragment : Fragment() {
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):  View? {
        val view = inflater.inflate(R.layout.fragment_navigation, container, false)
        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_feed -> {
                    (activity as? MainActivity)?.replaceFragment(HomeFragment())
                    true
                }

                R.id.navigation_settings -> {
                    (activity as? MainActivity)?.replaceFragment(UserProfileFragment())
                    true
                }

                R.id.navigation_groups -> {
                    (activity as? MainActivity)?.replaceFragment(GroupFragment())
                    true
                }


                // Add more cases for other menu items if needed
                else -> false
            }
        }

        return view
    }
}
