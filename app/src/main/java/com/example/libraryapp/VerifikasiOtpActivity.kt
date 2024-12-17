package com.example.libraryapp

import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class VerifikasiOtpActivity : AppCompatActivity() {

    private lateinit var timer: CountDownTimer
    private lateinit var btnConfirm: Button
    private lateinit var tvResendCode: TextView
    private lateinit var otpFields: Array<EditText>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verifikasi_otp)

        val ivBack = findViewById<ImageButton>(R.id.ivBack)
        btnConfirm = findViewById(R.id.btnConfirm)
        tvResendCode = findViewById(R.id.tvResendCode)

        otpFields = arrayOf(
            findViewById(R.id.etOtp1),
            findViewById(R.id.etOtp2),
            findViewById(R.id.etOtp3),
            findViewById(R.id.etOtp4),
            findViewById(R.id.etOtp5),
            findViewById(R.id.etOtp6)
        )

        // Back Button
        ivBack.setOnClickListener { finish() }

        // Start Timer
        startTimer()

        // Add OTP TextWatcher
        setupOtpInputs()

        // Confirm Button Action
        btnConfirm.setOnClickListener {
            val otpCode = otpFields.joinToString("") { it.text.toString() }
            if (otpCode.length == 6) {

            }
        }
    }

    private fun startTimer() {
        timer = object : CountDownTimer(50000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                tvResendCode.text = "RESEND CODE IN 00:${if (seconds < 10) "0$seconds" else seconds}"
            }

            override fun onFinish() {
                tvResendCode.text = "Resend Code"
            }
        }.start()
    }

    private fun setupOtpInputs() {
        for (i in otpFields.indices) {
            otpFields[i].addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s?.length == 1 && i < otpFields.size - 1) {
                        otpFields[i + 1].requestFocus()
                    }
                }

                override fun afterTextChanged(s: Editable?) {}
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }
}