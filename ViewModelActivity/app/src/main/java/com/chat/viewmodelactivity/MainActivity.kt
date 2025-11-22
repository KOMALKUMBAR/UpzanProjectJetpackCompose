package com.chat.viewmodelactivity

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var textvci =findViewById<TextView>(R.id.textView)
        var button =findViewById<TextView>(R.id.button)
       var viewModel: MainViewModel= ViewModelProvider(this).get(MainViewModel::class.java)
        textvci.text=viewModel.numbar.toString()
       button.setOnClickListener {
           viewModel.add()
           textvci.text=viewModel.numbar.toString()
       }

    }
}