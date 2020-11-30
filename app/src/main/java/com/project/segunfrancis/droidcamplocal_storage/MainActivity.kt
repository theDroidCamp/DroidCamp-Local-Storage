package com.project.segunfrancis.droidcamplocal_storage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.segunfrancis.droidcamplocal_storage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}