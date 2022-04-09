package com.example.timepie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LaunchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        findViewById<Button>(R.id.btnLogin).setOnClickListener{
            val intent = Intent(this@LaunchActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        findViewById<Button>(R.id.btnSignUp).setOnClickListener{
            val intent = Intent(this@LaunchActivity, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}