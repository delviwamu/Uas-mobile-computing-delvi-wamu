package com.example.realtime_database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.realtime_database.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intss = intent
        val nameT = intss.getStringExtra("NAMET")
        val desT = intss.getStringExtra("DESCRIT")
        val imgT = intss.getStringExtra("IMGURI")

        binding.imageTitle.text = nameT
        binding.imageDesc.text = desT
        binding.imageDetail.loadImage(imgT)

        setContentView(R.layout.activity_detail)
    }
}