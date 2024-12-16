package com.example.libraryapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class SignUpActivity : AppCompatActivity() {

    // Deklarasi variabel
    private lateinit var backButton: ImageButton
    private lateinit var etNama: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var etUlangiPassword: TextInputEditText
    private lateinit var btnSignUp: Button
    private lateinit var ivGmail: ImageButton
    private lateinit var ivFacebook: ImageButton
    private lateinit var tvLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up) // Sesuaikan dengan nama layout XML Anda

        // Inisialisasi elemen UI
        backButton = findViewById(R.id.ivBack)
        etNama = findViewById(R.id.etNama)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etUlangiPassword = findViewById(R.id.etUlangiPassword)
        btnSignUp = findViewById(R.id.btnSignUp)
        ivGmail = findViewById(R.id.ivGmail)
        ivFacebook = findViewById(R.id.ivFacebook)
        tvLogin = findViewById(R.id.tvLogin)

        // Event Listener untuk Back Button
        backButton.setOnClickListener {
            onBackPressed() // Kembali ke aktivitas sebelumnya
        }

        // Event Listener untuk tombol Sign Up
        btnSignUp.setOnClickListener {
            val nama = etNama.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString()
            val ulangiPassword = etUlangiPassword.text.toString()

            if (nama.isEmpty() || email.isEmpty() || password.isEmpty() || ulangiPassword.isEmpty()) {
                Toast.makeText(this, "Harap isi semua kolom!", Toast.LENGTH_SHORT).show()
            } else if (password != ulangiPassword) {
                Toast.makeText(this, "Password tidak cocok!", Toast.LENGTH_SHORT).show()
            } else {
                // Proses Sign Up (contoh)
                Toast.makeText(this, "Pendaftaran berhasil!\nNama: $nama\nEmail: $email", Toast.LENGTH_LONG).show()
            }
        }

        // Event Listener untuk tombol Gmail Login
        ivGmail.setOnClickListener {
            Toast.makeText(this, "Login dengan Gmail", Toast.LENGTH_SHORT).show()
            // Tambahkan integrasi Google Sign-In di sini
        }

        // Event Listener untuk tombol Facebook Login
        ivFacebook.setOnClickListener {
            Toast.makeText(this, "Login dengan Facebook", Toast.LENGTH_SHORT).show()
            // Tambahkan integrasi Facebook Login di sini
        }

        // Event Listener untuk Login TextView
        setupLoginTextView()
    }
    private fun setupLoginTextView() {
        val text = "Sudah punya akun? Login!"
        val spannableString = SpannableString(text)

        // Indeks teks "Sign up!"
        val startIndex = text.indexOf("Login!")
        val endIndex = startIndex + "Login!".length

        // Membuat bagian "Sign up!" dapat diklik
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                // Navigasi ke halaman Sign Up
//                Toast.makeText(this@LoginActivity, "Navigasi ke Halaman Sign Up", Toast.LENGTH_SHORT).show()

                // Gunakan intent untuk membuka halaman SignUpActivity jika ada
                val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        }
        // Tambahkan warna hijau pada "Sign up!"
        spannableString.setSpan(ForegroundColorSpan(Color.GREEN), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        tvLogin.text = spannableString
        tvLogin.movementMethod = LinkMovementMethod.getInstance() // Mengaktifkan klik pada teks

    }
}