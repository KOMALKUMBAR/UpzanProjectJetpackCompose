package com.chat.bottomnavigationapplication

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.BounceInterpolator
import android.view.animation.OvershootInterpolator
import com.google.android.material.bottomnavigation.BottomNavigationView

class CustomBottomNavigationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BottomNavigationView(context, attrs, defStyleAttr) {

    private var animationDuration = 300L
    private var animationScale = 1.2f

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                val touchedView = findViewAt(event.x, event.y)
                touchedView?.let { view ->
                    animatePress(view)
                }
            }
        }
        return super.onTouchEvent(event)
    }

    private fun findViewAt(x: Float, y: Float): View? {
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            if (child.visibility == View.VISIBLE) {
                val childX = child.x
                val childY = child.y
                val childRight = childX + child.width
                val childBottom = childY + child.height

                if (x >= childX && x <= childRight && y >= childY && y <= childBottom) {
                    return child
                }
            }
        }
        return null
    }

    private fun animatePress(view: View) {
        val scaleDownX = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 0.95f)
        val scaleDownY = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 0.95f)
        val scaleUpX = ObjectAnimator.ofFloat(view, "scaleX", 0.95f, animationScale, 1.0f)
        val scaleUpY = ObjectAnimator.ofFloat(view, "scaleY", 0.95f, animationScale, 1.0f)

        val pressAnimator = AnimatorSet()
        pressAnimator.playTogether(scaleDownX, scaleDownY)
        pressAnimator.duration = 100

        val releaseAnimator = AnimatorSet()
        releaseAnimator.playTogether(scaleUpX, scaleUpY)
        releaseAnimator.duration = animationDuration
        releaseAnimator.interpolator = BounceInterpolator()

        val fullAnimator = AnimatorSet()
        fullAnimator.play(releaseAnimator).after(pressAnimator)
        fullAnimator.start()
    }

    /**
     * Public method to trigger popup animation on specific item
     */
    fun animateItem(itemId: Int) {
        val itemView = findViewById<View>(itemId)
        itemView?.let { view ->
            createPopupAnimation(view).start()
        }
    }

    /**
     * Create popup animation animator set
     */
    private fun createPopupAnimation(view: View): AnimatorSet {
        val scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, animationScale, 1.0f)
        val scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, animationScale, 1.0f)
        val alpha = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.8f, 1.0f)

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(scaleX, scaleY, alpha)
        animatorSet.duration = animationDuration
        animatorSet.interpolator = OvershootInterpolator()

        return animatorSet
    }

    /**
     * Set animation properties
     */
    fun setAnimationProperties(duration: Long, scale: Float) {
        this.animationDuration = duration
        this.animationScale = scale
    }

    /**
     * Ripple effect animation
     */
    fun createRippleEffect(view: View) {
        val scaleX1 = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 1.1f)
        val scaleY1 = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 1.1f)
        val scaleX2 = ObjectAnimator.ofFloat(view, "scaleX", 1.1f, 1.0f)
        val scaleY2 = ObjectAnimator.ofFloat(view, "scaleY", 1.1f, 1.0f)

        val firstWave = AnimatorSet()
        firstWave.playTogether(scaleX1, scaleY1)
        firstWave.duration = 150

        val secondWave = AnimatorSet()
        secondWave.playTogether(scaleX2, scaleY2)
        secondWave.duration = 150

        val ripple = AnimatorSet()
        ripple.play(secondWave).after(firstWave)
        ripple.start()
    }
}