package com.example.timepie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.parse.ParseUser

class LaunchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        if (ParseUser.getCurrentUser() != null) {
            gotoMainActivity()
        }

        findViewById<Button>(R.id.btnLogin).setOnClickListener{
            val intent = Intent(this@LaunchActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnSignUp).setOnClickListener{
            val intent = Intent(this@LaunchActivity, SignupActivity::class.java)
            startActivity(intent)
        }
    }

    private fun gotoMainActivity() {
        val intent = Intent(this@LaunchActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}