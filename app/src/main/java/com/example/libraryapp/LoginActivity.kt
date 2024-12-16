package com.example.libraryapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
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
import com.google.firebase.auth.FirebaseAuth

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
    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()


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

        setupSignUpTextView()
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

    private fun setupSignUpTextView() {
        val text = "Belum punya akun? Sign up!"
        val spannableString = SpannableString(text)

        // Indeks teks "Sign up!"
        val startIndex = text.indexOf("Sign up!")
        val endIndex = startIndex + "Sign up!".length

        // Membuat bagian "Sign up!" dapat diklik
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                // Navigasi ke halaman Sign Up
//                Toast.makeText(this@LoginActivity, "Navigasi ke Halaman Sign Up", Toast.LENGTH_SHORT).show()

                // Gunakan intent untuk membuka halaman SignUpActivity jika ada
                 val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
                 startActivity(intent)
            }
        }

        // Tambahkan warna hijau pada "Sign up!"
        spannableString.setSpan(ForegroundColorSpan(Color.GREEN), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        tvSignUp.text = spannableString
        tvSignUp.movementMethod = LinkMovementMethod.getInstance() // Mengaktifkan klik pada teks
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

        // Firebase Authentication untuk login
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Successfully Logged In", Toast.LENGTH_SHORT).show()
            } else {
                val errorMessage = task.exception?.message ?: "Unknown error"
                Toast.makeText(this, "Log In failed: $errorMessage", Toast.LENGTH_SHORT).show()
                Log.e("LoginActivity", "Login failed: $errorMessage")
            }
        }

    }


}