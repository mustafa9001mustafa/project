package com.konden.storonline.animations

import android.content.Context
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.konden.freedom.R

class Animations {
    fun a5_FadeIn(context: Context?): Animation? {
        return AnimationUtils.loadAnimation(context, R.anim.fadein)
    }


    fun a1_Up(context: Context?): Animation? {
        return AnimationUtils.loadAnimation(context, R.anim.slide_out_bottom)
    }


    fun a2_Down(context: Context?): Animation? {
        return AnimationUtils.loadAnimation(context, R.anim.slide_in_bottom)
    }
}