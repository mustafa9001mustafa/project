package com.konden.freedom.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.konden.freedom.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        ALlFUN()
    }

    private fun ALlFUN() {
        CHECK_NUMBER_AND_LOGIN()
        LOGIN_GUEST()
    }

    private fun LOGIN_GUEST() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun CHECK_NUMBER_AND_LOGIN() {
//        TODO
    }
}