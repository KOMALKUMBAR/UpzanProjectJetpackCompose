package com.chat.komalkumbarportfolio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.chat.komalkumbarportfolio.ui.theme.KomalKumbarPortfolioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KomalKumbarPortfolioTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    PortfolioApp()
                }
            }
        }

    }
}
