package com.example.gameapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    val handler = Handler()
    val delayMil=3000L

    handler.postDelayed({
        transitionActivity()
        finish()
    }, delayMil)

        val img = findViewById<ImageView>(R.id.imageView)
        img.setOnClickListener {
        transitionActivity()
    }
}
fun transitionActivity(){
    intent = Intent(this, gameSubsequence::class.java);
    startActivity(intent)
}
}