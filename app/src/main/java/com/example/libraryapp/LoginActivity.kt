package com.example.libraryapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton: Button = findViewById(R.id.login_button)
        loginButton.setOnClickListener {
            val email = findViewById<EditText>(R.id.email_input).text.toString()
            val password = findViewById<EditText>(R.id.password_input).text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Pindah ke halaman Beranda
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Harap isi email dan password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}