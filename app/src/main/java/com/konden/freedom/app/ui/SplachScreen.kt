package com.konden.freedom.app.ui


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.konden.freedom.R
import com.konden.freedom.app.shard.ShardPreferans
import com.konden.freedom.databinding.ActivitySplachScreenBinding
import com.konden.storonline.animations.Animations


class SplachScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplachScreenBinding
//    lateinit var viewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplachScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (ShardPreferans.getInstance().IsFirest()){
            ShardPreferans.getInstance().saveLogin(false)
            ShardPreferans.getInstance().GustLogin(false)
            ShardPreferans.getInstance().saveSize(true)
        }
    }


    override fun onStart() {
        super.onStart()

        val myCar = Animations()
        binding.tvSplach.animation = myCar.a5_FadeIn(this@SplachScreen)
        Handler(Looper.getMainLooper()).postDelayed({
            if (ShardPreferans.getInstance().isFirstTimeOther())
                startActivity(Intent(this@SplachScreen, ViewPagerScrollActivity::class.java))
            else{
                if (ShardPreferans.getInstance().statesLogin || ShardPreferans.getInstance().GustLogin){
                    startActivity(Intent(this@SplachScreen, HomeActivity::class.java))
                } else {
                    startActivity(Intent(this@SplachScreen, LoginActivity::class.java))

                }
            }

        }, 3200)




//        val database = Firebase.firestore
//        val myRef = database.getReference("message")
//        myRef.setValue("Hello, World!")
    }


    override fun onStop() {
        super.onStop()
        finish()
//        Handler(Looper.getMainLooper()).postDelayed({
//            viewModel.allWords.observe(this,
//                Observer<List<Any?>?> {
//                    Log.e("TAG", "onCreate: ")
//                })
//        }, 2000)

    }


    override fun recreate() {
        finish()
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        startActivity(intent)
    }

}