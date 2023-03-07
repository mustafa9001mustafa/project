package com.konden.freedom.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.ktx.Firebase
import com.konden.freedom.databinding.ActivitySplachScreenBinding
import com.konden.storonline.animations.Animations

class SplachScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplachScreenBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplachScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    override fun onStart() {
        super.onStart()

        val myCar = Animations()

        Handler(Looper.getMainLooper()).postDelayed({
            val mainIntent = Intent(this, ViewPagerScrollActivity::class.java)
            startActivity(mainIntent)
            binding.imageSplach.animation = myCar.a3_FromTheLeft(this@SplachScreen)
        }, 2000)



//        val database = Firebase.
//        val myRef = database.getReference("message")

//        myRef.setValue("Hello, World!")
    }



    override fun onResume() {
        super.onResume()

    }

    override fun onPause() {
        super.onPause()

    }


    override fun onRestart() {
        super.onRestart()
    }

    override fun onStop() {
        super.onStop()

    }


    override fun onDestroy() {
        super.onDestroy()
        finish()

    }
}