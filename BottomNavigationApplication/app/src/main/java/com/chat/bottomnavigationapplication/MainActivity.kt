package com.chat.bottomnavigationapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.view.animation.ScaleAnimation
import android.view.Gravity
import android.widget.FrameLayout

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNav = findViewById(R.id.bottomNav)

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_chat -> {
                    animateIcon(item.itemId)
                    showPopup()
                    true
                }
                else -> {
                    true
                }
            }
        }
    }

    private fun animateIcon(itemId: Int) {
        val iconView = bottomNav.findViewById<View>(
            bottomNav.menu.findItem(itemId).itemId
        )
        val scaleAnimation = ScaleAnimation(
            1f, 1.5f, 1f, 1.5f,
            ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
            ScaleAnimation.RELATIVE_TO_SELF, 0.5f
        )
        scaleAnimation.duration = 300
        scaleAnimation.fillAfter = true
        iconView.startAnimation(scaleAnimation)
    }

    private fun showPopup() {
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.popup_chat, null)

        val popupWindow = PopupWindow(
            popupView,
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT,
            true
        )

        popupWindow.showAtLocation(bottomNav, Gravity.CENTER, 0, 0)
    }
}
