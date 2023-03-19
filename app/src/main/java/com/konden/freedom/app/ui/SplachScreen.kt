package com.konden.freedom.app.ui


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
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

    }

    override fun onStart() {
        super.onStart()

        val myCar = Animations()
        binding.tvSplach.animation = myCar.a5_FadeIn(this@SplachScreen)
        Handler(Looper.getMainLooper()).postDelayed({
            if (ShardPreferans.getInstance().isFirstTimeOther() == true)
                startActivity(Intent(this@SplachScreen, ViewPagerScrollActivity::class.java))
            else{
                if (ShardPreferans.getInstance().statesLogin == true){
                    startActivity(Intent(this@SplachScreen, HomeActivity::class.java))
                } else {
                    startActivity(Intent(this@SplachScreen, LoginActivity::class.java))

                }

            }

        }, 3200)

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
}