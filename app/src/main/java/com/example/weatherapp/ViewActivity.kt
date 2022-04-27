package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherapp.databinding.ActivityViewBinding

class ViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

       // binding.tvMessage.text = intent.getStringExtra("title") ?: ""

    }
}