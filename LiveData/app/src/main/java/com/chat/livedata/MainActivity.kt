package com.chat.livedata

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val viewModel: viewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textview =findViewById<TextView>(R.id.textViewCount)
        val button = findViewById<Button>(R.id.buttonIncrease)

        viewModel.count.observe( this){
              currentcount->textview.text="Count: $currentcount"
          }

        button.setOnClickListener {
           viewModel.increaseCount()
        }

    }
}