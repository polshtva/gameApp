package com.example.gameapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.gameapp.databinding.ActivityImgRemBinding


class imgRem : AppCompatActivity() {
    private lateinit var countDownTimer: CountDownTimer
    lateinit var binding : ActivityImgRemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImgRemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            binding.firstBlockRem.visibility = View.GONE
            binding.blockAnswer.visibility = View.VISIBLE
        }

        binding.otherGame.setOnClickListener {
            val intent = Intent(this, ListGames::class.java)
            startActivity(intent)
        }

        val arrDef = arrayOf(binding.img11, binding.img12, binding.img13, binding.img14, binding.img15,binding.img16, binding.img17)
        val arrRem = arrayOf(binding.img21, binding.img22, binding.img23, binding.img24, binding.img25,binding.img26, binding.img27)

        for (ar1 in arrDef){
            ar1.setOnClickListener {
                changeImgDefault(ar1)
            }
        }
        for (ar2 in arrRem){
            ar2.setOnClickListener {
                changeImgRemember(ar2)
            }
        }
        startTimer()

        binding.checkAnswer.setOnClickListener {
            if(getEditTextValues() == "4627315"){
                Toast.makeText(this, "Ты большой молодец!!!", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this, "Не верно, попробуй ещё раз!", Toast.LENGTH_LONG).show()
                binding.blockAnswer.visibility = View.GONE
                binding.firstBlockRem.visibility = View.VISIBLE
            }

        }

    }

    fun getEditTextValues(): String {
        val stringBuilder = StringBuilder()

        // Идентификаторы всех EditText-ов
        val editTextIds = listOf(
            R.id.dataUser1,
            R.id.dataUser2,
            R.id.dataUser3,
            R.id.dataUser4,
            R.id.dataUser5,
            R.id.dataUser6,
            R.id.dataUser7
        )

        for (editTextId in editTextIds) {
            val editText = findViewById<EditText>(editTextId)
            stringBuilder.append(editText.text.toString())
        }

        return stringBuilder.toString()
    }


    fun changeImgDefault(clickImg : ImageView){
        when(clickImg.id){
            R.id.img11 -> {
                clickImg.setImageResource(R.drawable.dollar)
                Timer(binding.img11)
            }
            R.id.img12 ->{
                clickImg.setImageResource(R.drawable.euro)
                Timer(binding.img12)
            }
            R.id.img13 ->{
                clickImg.setImageResource(R.drawable.indian)
                Timer(binding.img13)
            }
            R.id.img14 ->{
                clickImg.setImageResource(R.drawable.japanese)
                Timer(binding.img14)
            }
            R.id.img15 ->{
                clickImg.setImageResource(R.drawable.pound)
                Timer(binding.img15)
            }
            R.id.img16 ->{
                clickImg.setImageResource(R.drawable.rub)
                Timer(binding.img16)
            }
            R.id.img17 ->{
                clickImg.setImageResource(R.drawable.south_korean)
                Timer(binding.img17)
            }

        }
    }

    fun changeImgRemember(clickImg : ImageView) {
        when (clickImg.id) {
            R.id.img21 -> {
                clickImg.setImageResource(R.drawable.rub)
                Timer(binding.img21)
            }
            R.id.img22 -> {
                clickImg.setImageResource(R.drawable.indian)
                Timer(binding.img22)
            }
            R.id.img23 -> {
                clickImg.setImageResource(R.drawable.pound)
                Timer(binding.img23)
            }
            R.id.img24 -> {
                clickImg.setImageResource(R.drawable.dollar)
                Timer(binding.img24)
            }
            R.id.img25 -> {
                clickImg.setImageResource(R.drawable.south_korean)
                Timer(binding.img25)
            }
            R.id.img26 -> {
                clickImg.setImageResource(R.drawable.euro)
                Timer(binding.img26)
            }
            R.id.img27 -> {
                clickImg.setImageResource(R.drawable.japanese)
                Timer(binding.img27)
            }

        }
    }
    fun Timer(img: ImageView) {
        val handler = Handler()
        handler.postDelayed(
            {
                img.setImageResource(R.drawable.coin)
            }, 2000
        )
    }
    private fun startTimer() {
        val startTimeInMillis: Long = 60000
        countDownTimer = object : CountDownTimer(startTimeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000
                val minutes = secondsRemaining / 60
                val seconds = secondsRemaining % 60

                // Обновляем текст таймера
                binding.timer.text = String.format("%02d:%02d", minutes, seconds)

                // Изменяем цвет текста при достижении 10 секунд
                if (secondsRemaining <= 10) {
                    binding.timer.setTextColor(resources.getColor(android.R.color.holo_red_dark))
                }
            }

            override fun onFinish() {
                // Скрыть игру и отобразить поле для ввода ответа
                binding.firstBlockRem.visibility = View.GONE
                binding.blockAnswer.visibility = View.VISIBLE
            }
        }
        countDownTimer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Отменяем таймер в onDestroy, чтобы избежать утечки ресурсов
        if (::countDownTimer.isInitialized) {
            countDownTimer.cancel()
        }
    }

}

