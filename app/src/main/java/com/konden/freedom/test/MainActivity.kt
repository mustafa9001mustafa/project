package com.konden.freedom.test

import android.annotation.TargetApi
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.jawnnypoo.physicslayout.Physics
import com.jawnnypoo.physicslayout.PhysicsConfig
import com.jawnnypoo.physicslayout.Shape
import com.konden.freedom.R
import com.konden.freedom.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

//    var firebaseDatabase: FirebaseDatabase? = null
//    var databaseReference: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        // Hide bar when you want. For example hide bar in landscape only
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            hideStatusBar_AllVersions();
        }
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)





//        binding.physicsLayout.physics.setOnCollisionListener(object : Physics.OnCollisionListener {
//            override fun onCollisionEntered(i: Int, i1: Int) {
//                if (i ==  R.id.ima && i1 ==  R.id.img)
//                    Toast.makeText(this@MainActivity, "test", Toast.LENGTH_SHORT).show()
//                Log.e("TAG", "onCollisionEntered: ", )
//            }
//            override fun onCollisionExited(i: Int, i1: Int) {
//
//
//            }
//        })
//        binding.physicsLayout.physics.setOnCollisionListener(object =)
//        val config = PhysicsConfig(
//            shape = Shape.CIRCLE
////            fixtureDef = fixtureDef,
////            bodyDef = bodyDef
//        )
//        Physics.setPhysicsConfig(binding.physicsLayout, config)


    }


    fun hideStatusBar_Deprecated() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {  // < 16
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        } else {  // 16...29
            val decorView: View = window.decorView
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN)
            val ab: ActionBar? = supportActionBar
            if (ab != null) {
                ab.hide()
            }
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }

    @TargetApi(Build.VERSION_CODES.R)
    fun  // >= 30
            hideStatusBar_Actual() {
        val decorView: View = window.decorView
        decorView.getWindowInsetsController()?.hide(WindowInsets.Type.statusBars())
    }

    fun hideStatusBar_AllVersions() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            hideStatusBar_Deprecated()
        } else {
            hideStatusBar_Actual()
        }
    }

}