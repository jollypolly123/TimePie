package com.example.timepie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.parse.ParseUser

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        
        findViewById<Button>(R.id.login).setOnClickListener { 
            val username = findViewById<EditText>(R.id.etUserName).text.toString()
            val password = findViewById<EditText>(R.id.etPassword).text.toString()
            loginUser(username, password)
        }
    }

    private fun loginUser(username: String, password: String) {
        ParseUser.logInInBackground(username, password, ({ user, e ->
            if (user != null) {
                Toast.makeText(this, "Welcome Back!!", Toast.LENGTH_SHORT).show()
                gotoMainActivity()
            } else {
                e.printStackTrace()
                if (username.length == 0 || password.length == 0) {
                    Toast.makeText(this, "Username and Password Required", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Invalid User! Create a User first.", Toast.LENGTH_SHORT).show()
                    val i = Intent(this@LoginActivity, SignupActivity::class.java)
                    startActivity(i)
                    finish()
                }

            }})
        )
    }

    private fun gotoMainActivity() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object {
        const val TAG = "LoginActivity"
    }
}