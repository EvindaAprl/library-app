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
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

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
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Inisialisasi Firebase Auth
        auth = FirebaseAuth.getInstance()

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

        backButton.setOnClickListener {
            onBackPressed()
        }

        btnSignUp.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString()
            val ulangiPassword = etUlangiPassword.text.toString()

            if (email.isEmpty() || password.isEmpty() || ulangiPassword.isEmpty()) {
                Toast.makeText(this, "Harap isi semua kolom!", Toast.LENGTH_SHORT).show()
            } else if (password != ulangiPassword) {
                Toast.makeText(this, "Password tidak cocok!", Toast.LENGTH_SHORT).show()
            } else {
                signUpWithEmail(email, password)
            }
        }

        setupLoginTextView()
    }

    private fun signUpWithEmail(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.sendEmailVerification()?.addOnCompleteListener { verificationTask ->
                        if (verificationTask.isSuccessful) {
                            Toast.makeText(
                                this,
                                "Pendaftaran berhasil! Silakan cek email untuk verifikasi.",
                                Toast.LENGTH_LONG
                            ).show()
                            // Navigasi ke halaman Login setelah pendaftaran
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(
                                this,
                                "Gagal mengirim email verifikasi: ${verificationTask.exception?.message}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                } else {
                    Toast.makeText(
                        this,
                        "Pendaftaran gagal: ${task.exception?.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
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