package com.example.gameapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gameapp.databinding.ActivityListGamesBinding

class ListGames : AppCompatActivity() {
    lateinit var binding: ActivityListGamesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListGamesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.gameSub.setOnClickListener {
            val intent = Intent(this, gameSubsequence::class.java )
            startActivity(intent)
        }
        binding.gameDif.setOnClickListener {
            val intent = Intent(this, Differences::class.java )
            startActivity(intent)
        }
        binding.gamePt.setOnClickListener {
            val intent = Intent(this, tag::class.java )
            startActivity(intent)
        }
        binding.gamePic.setOnClickListener {
            val intent = Intent(this, imgRem::class.java )
            startActivity(intent)
        }
    }
}