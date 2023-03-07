package com.konden.storonline.animations

import android.content.Context
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.konden.freedom.R

class Animations {

    fun a1_FromTheTop(context: Context?): Animation? {
        return AnimationUtils.loadAnimation(context, R.anim.fromthetop)
    }


    fun a2_FromTheRight(context: Context?): Animation? {
        return AnimationUtils.loadAnimation(context, R.anim.fortheright)
    }

    fun a3_FromTheLeft(context: Context?): Animation? {
        return AnimationUtils.loadAnimation(context, R.anim.fortheleft)
    }

    fun a4_FromTheBottom(context: Context?): Animation? {
        return AnimationUtils.loadAnimation(context, R.anim.frombottom)
    }

    fun a5_FadeIn(context: Context?): Animation? {
        return AnimationUtils.loadAnimation(context, R.anim.fadein)
    }

    fun a6_anim(context: Context?): Animation? {
        return AnimationUtils.loadAnimation(context, R.anim.amime2)
    }

    fun a7_shake(context: Context?): Animation? {
        return AnimationUtils.loadAnimation(context, R.anim.shake)
    }

    fun a8_loop(context: Context?): Animation? {
        return AnimationUtils.loadAnimation(context, R.anim.spinclockwise)
    }

    fun a9_Small_to_big(context: Context?): Animation? {
        return AnimationUtils.loadAnimation(context, R.anim.smalltobig)
    }

    fun a10_anim4(context: Context?): Animation? {
        return AnimationUtils.loadAnimation(context, R.anim.animeion4)
    }

    fun a11_anim3(context: Context?): Animation? {
        return AnimationUtils.loadAnimation(context, R.anim.animeion3)
    }

    fun a12_anim2(context: Context?): Animation? {
        return AnimationUtils.loadAnimation(context, R.anim.animeion2)
    }

    fun a13_anim1(context: Context?): Animation? {
        return AnimationUtils.loadAnimation(context, R.anim.anim1alqa)
    }

    fun a14_small_big_forth(context: Context?): Animation? {
        return AnimationUtils.loadAnimation(context, R.anim.smallbigforth)
    }

}