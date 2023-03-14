package com.konden.freedom.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.konden.freedom.database.WordViewModel
import com.konden.freedom.databinding.ActivitySplachScreenBinding
import com.konden.storonline.animations.Animations


class SplachScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplachScreenBinding
//    lateinit var viewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplachScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        viewModel = ViewModelProvider(this)[WordViewModel::class.java]
        if (Build.VERSION.SDK_INT >= 23) {
            ExternalStoragePermissions.verifyStoragePermissions(this)
        }
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

    override fun onStop() {
        super.onStop()
//        Handler(Looper.getMainLooper()).postDelayed({
//            viewModel.allWords.observe(this,
//                Observer<List<Any?>?> {
//                    Log.e("TAG", "onCreate: ")
//                })
//        }, 2000)
//

    }


    internal abstract class ExternalStoragePermissions(callingActivity: Activity?) {
        companion object {
            private const val REQUEST_EXTERNAL_STORAGE = 1
            private val PERMISSIONS_STORAGE = arrayOf<String>(
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )

            // Note call this method
            fun verifyStoragePermissions(activity: Activity?) {
                val permission = ActivityCompat.checkSelfPermission(
                    activity!!,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                if (permission != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                        activity,
                        PERMISSIONS_STORAGE,
                        REQUEST_EXTERNAL_STORAGE
                    )
                }
            }
        }
    }
}