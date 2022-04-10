package com.example.timepie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.parse.ParseUser

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        findViewById<Button>(R.id.signup).setOnClickListener {
            val username = findViewById<EditText>(R.id.UserName).text.toString()
            val password = findViewById<EditText>(R.id.Password).text.toString()
            val email = findViewById<EditText>(R.id.etEmail).text.toString()
            val phone = findViewById<EditText>(R.id.etPhone).text.toString()
            signupUser(username, password, email, phone)
        }
    }

    private fun signupUser(username: String, password: String, email: String, phone: String) {
        // Create the ParseUser
        val user = ParseUser()

        // Set fields for the user to be created
        user.setUsername(username)
        user.setPassword(password)
        user.setEmail(email)
        user.put("phone", phone)

        user.signUpInBackground { e ->
            if (e == null) {
                Toast.makeText(this, "SignUp Successful!", Toast.LENGTH_SHORT).show()
                gotoMainActivity()
            } else {
                e.printStackTrace()
                if (username.length == 0 || password.length == 0 || phone.length == 0 || email.length == 0) {
                    Toast.makeText(this, "All Fields Required", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(this, "User Already Exists! Try logging in", Toast.LENGTH_SHORT)
                        .show()
                    val i = Intent(this@SignupActivity, LoginActivity::class.java)
                    startActivity(i)
                    finish()
                }
            }
        }
    }

    private fun gotoMainActivity() {
        val intent = Intent(this@SignupActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}