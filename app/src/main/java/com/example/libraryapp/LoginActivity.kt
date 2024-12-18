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
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

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
    private lateinit var googleSignInClient: GoogleSignInClient



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        setupGoogleSignIn()


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
            signInWithGoogle()
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
                val user = auth.currentUser
                if (user != null && user.isEmailVerified) {
                    Toast.makeText(this, "Successfully Logged In", Toast.LENGTH_SHORT).show()
                    // Navigasi ke halaman utama
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    // Jika email belum diverifikasi
                    auth.signOut() // Logout pengguna
                    Toast.makeText(
                        this,
                        "Email belum diverifikasi. Silakan periksa email Anda untuk verifikasi.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } else {
                val errorMessage = task.exception?.message ?: "Unknown error"
                Toast.makeText(this, "Log In failed: $errorMessage", Toast.LENGTH_SHORT).show()
                Log.e("LoginActivity", "Login failed: $errorMessage")
            }
        }
    }
    private fun setupGoogleSignIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)) // ID dari Firebase Console
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private val googleSignInLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Log.e("LoginActivity", "Google Sign-In failed", e)
                Toast.makeText(this, "Google Sign-In failed", Toast.LENGTH_SHORT).show()
            }
        }

    private fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        googleSignInLauncher.launch(signInIntent)
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Toast.makeText(this, "Welcome ${user?.displayName}", Toast.LENGTH_SHORT).show()
                    // Navigasi ke halaman utama
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Log.e("LoginActivity", "Authentication failed: ${task.exception?.message}")
                    Toast.makeText(this, "Authentication Failed", Toast.LENGTH_SHORT).show()
                }
            }
    }



}