package com.example.timepie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.parse.ParseUser

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager: FragmentManager = supportFragmentManager
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNav.setOnItemSelectedListener{ item ->
            var fragmentToShow: Fragment? = null
            when (item.itemId) {
                R.id.action_home -> {
                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                    fragmentToShow = HomeFragment()
                    // TODO: change to home fragment
                }

                R.id.action_calendar -> {
                    Toast.makeText(this, "Calendar", Toast.LENGTH_SHORT).show()
                    fragmentToShow = CalendarFragment()
                    // TODO: work on calendar fragment (completely new)
                }

                R.id.action_supplement -> {
                    Toast.makeText(this, "Compose", Toast.LENGTH_SHORT).show()
                    fragmentToShow = ViewSupplementsFragment()
                    // TODO: work on AddSupplement, SupplementDetail (then mod VSF to click in)
                }

                R.id.action_profile -> {
                    Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
                    fragmentToShow = ProfileFragment()
                    // TODO: change to profile fragment
                }
            }

            if (fragmentToShow != null) {
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragmentToShow).commit()
            }
            true
        }
        bottomNav.selectedItemId = R.id.action_supplement
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logout) {
            ParseUser.logOut()
            val intent = Intent(this@MainActivity, LaunchActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Logout Successful!!", Toast.LENGTH_SHORT).show()
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

}