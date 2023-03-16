package com.konden.freedom.app.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.konden.freedom.R
import com.konden.freedom.databinding.ActivityHomeBinding
import com.konden.freedom.app.fragment.DangersFragment
import com.konden.freedom.app.fragment.HomeFragment
import com.konden.freedom.app.fragment.ProfileFragment


class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        allmethod()

    }

    override fun onStart() {
        super.onStart()
    }

    private fun allmethod() {

        //add menu items to bottom nav
        binding.bottomNav.add(MeowBottomNavigation.Model(1, R.drawable.danger))
        binding.bottomNav.add(MeowBottomNavigation.Model(2, R.drawable.home_icon))
        binding.bottomNav.add(MeowBottomNavigation.Model(3, R.drawable.baseline_account_circle_24))

        //set bottom nav on show listener

        binding.bottomNav.setOnShowListener {
            if (it.id == 1) {
                val fragment: DangersFragment = DangersFragment.newInstance("", "")
                supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_fragment, fragment, null).commit()


            } else if (it.id == 2) {

                val fragment: HomeFragment = HomeFragment.newInstance("", "")
                supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_fragment, fragment, null).commit()


            } else if (it.id == 3) {
                val fragment: ProfileFragment = ProfileFragment.newInstance("", "")
                supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_fragment, fragment, null).commit()
            }
        }

        //set the initial fragment to show


        //set the initial fragment to show
        Handler(Looper.getMainLooper()).postDelayed({
            binding.bottomNav.show(2, true)

        }, 50)

        //set menu item on click listener

        //set menu item on click listener
//        binding.bottomNav.setOnClickMenuListener(object : ClickListener() {
//            fun onClickItem(item: Model?) {
//                //display a toast
////                Toast.makeText(getApplicationContext(), " You clicked " + item.getId(), Toast.LENGTH_SHORT).show();
//            }
//        })

        //set on reselect listener

        //set on reselect listener
//        binding.bottomNav.setOnReselectListener(object :  {
//            fun onReselectItem(item: MeowBottomNavigation.Model?) {
//                //display a toast
////                Toast.makeText(getApplicationContext(), " You reselected " + item.getId(), Toast.LENGTH_SHORT).show();
//            }
//        })

        //set count to dashboard item

        //set count to dashboard item
//        binding.bottomNav.setCount(1, "*")

        binding.bottomNav.setBackgroundColor(Color.red(Color.BLUE))
        binding.bottomNav.countBackgroundColor = (Color.red(Color.BLUE))
//         binding.bottomNav.setCountBackgroundColor(Color.blue(Color.RED));

    }
}

