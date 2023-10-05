package com.vasyancoder.testloading

import android.graphics.drawable.Animatable2.AnimationCallback
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vasyancoder.testloading.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var changeAnimation = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startLoadingAnimation()
        binding.changeAnimBtn.setOnClickListener {
            changeAnimation = true
//            (binding.loadingImageAnim.drawable as AnimatedVectorDrawable).start()
        }
    }

    private fun startLoadingAnimation() {
        val loadingAnimVector = binding.loadingImageAnim.drawable
        if (loadingAnimVector is AnimatedVectorDrawable) {
            loadingAnimVector.start()
            loadingAnimVector.registerAnimationCallback(object : AnimationCallback() {
                override fun onAnimationEnd(drawable: Drawable?) {
                    super.onAnimationEnd(drawable)
                    if (changeAnimation) {
                        loadingAnimVector.stop()
                        startDoneAnimation()
                    } else {
                        loadingAnimVector.start()
                    }
                }
            })
        }
    }

    private fun startDoneAnimation() {
        binding.loadingImageAnim.setImageResource(R.drawable.avd_anim_3_dots_to_check_mark)
        val doneAnimVector = binding.loadingImageAnim.drawable
        if (doneAnimVector is AnimatedVectorDrawable) {
            doneAnimVector.start()
        }
    }
}