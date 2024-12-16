package com.example.libraryapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.libraryapp.databinding.ActivityLoginBinding
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {
    // Deklarasi variabel untuk komponen UI
    private lateinit var ivBack: ImageButton
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnLogin: Button
    private lateinit var tvForgotPassword: TextView
    private lateinit var ivGmail: ImageButton
    private lateinit var ivFacebook: ImageButton
    private lateinit var tvSignUp: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inisialisasi komponen UI
        initViews()

        // Contoh penggunaan
        ivBack.setOnClickListener {
            finish() // Misalnya untuk kembali ke activity sebelumnya
        }

        btnLogin.setOnClickListener {
            handleLogin()
        }

        tvForgotPassword.setOnClickListener {
            Toast.makeText(this, "Fitur Lupa Password belum tersedia", Toast.LENGTH_SHORT).show()
        }

        ivGmail.setOnClickListener {
            Toast.makeText(this, "Login dengan Gmail", Toast.LENGTH_SHORT).show()
        }

        ivFacebook.setOnClickListener {
            Toast.makeText(this, "Login dengan Facebook", Toast.LENGTH_SHORT).show()
        }

        tvSignUp.setOnClickListener {
            Toast.makeText(this, "Navigasi ke Halaman Sign Up", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initViews() {
        ivBack = findViewById(R.id.ivBack)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvForgotPassword = findViewById(R.id.tvForgotPassword)
        ivGmail = findViewById(R.id.ivGmail)
        ivFacebook = findViewById(R.id.ivFacebook)
        tvSignUp = findViewById(R.id.tvSignUp)
    }

    private fun handleLogin() {
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()

        if (email.isEmpty()) {
            etEmail.error = "Email harus diisi"
            etEmail.requestFocus()
            return
        }

        if (password.isEmpty()) {
            etPassword.error = "Password harus diisi"
            etPassword.requestFocus()
            return
        }

        if (email == "test@gmail.com" && password == "12345") {
            Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Email atau Password salah", Toast.LENGTH_SHORT).show()
        }
    }
}