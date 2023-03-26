package com.konden.storonline.animations

import android.content.Context
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.konden.freedom.R

class Animations {
    fun a5_FadeIn(context: Context?): Animation? {
        return AnimationUtils.loadAnimation(context, R.anim.fadein)
    }
}