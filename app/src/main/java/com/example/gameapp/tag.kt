package com.example.gameapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import com.example.gameapp.databinding.ActivityGameSubsequenceBinding
import com.example.gameapp.databinding.ActivityTagBinding

class tag : AppCompatActivity() {
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var binding: ActivityTagBinding
    private var level = ""
    var currentRes = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTagBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {
            val intent = Intent(this, Differences::class.java)
            startActivity(intent)
        }

        binding.otherGame.setOnClickListener {
            val intent = Intent(this, ListGames::class.java)
            startActivity(intent)
        }

        binding.startGame.setOnClickListener {
            binding.first.visibility = View.GONE
            binding.level.visibility = View.VISIBLE
        }

        // Устанавливаем слушатели для кнопок с уровнями сложности
        val arrLevel = arrayOf(binding.easyLevel, binding.mediumLevel, binding.hardLevel)
        arrLevel.forEach { l ->
            l.setOnClickListener {
                level = when (l.id) {
                    R.id.easy_level -> "e"
                    R.id.medium_level -> "m"
                    R.id.hard_level -> "h"
                    else -> throw IllegalArgumentException("Ошибка уровня")
                }

                // Установка видимости элементов интерфейса и запуск игры
                binding.level.visibility = View.GONE
                binding.game1.visibility = View.VISIBLE

                // Генерация случайной строки и запуск таймера
                val randomString = genCode(level)
                binding.textRemember.text = randomString
                startTimer()

                binding.btnStart.setOnClickListener {
                    binding.game1.visibility = View.GONE
                    binding.editQ.visibility = View.VISIBLE
                }

                // Обработка ответа пользователя
                binding.checkAnswer.setOnClickListener {
                    val userAnswer = binding.editAnswer.text.toString()
                    if (userAnswer == randomString) {
                        binding.textVal.text = "Правильно"
                        binding.textVal.setTextColor(resources.getColor(android.R.color.holo_green_dark))
                        currentRes += 1
                    } else {
                        binding.textVal.text = "Не правильно"
                        binding.textVal.setTextColor(resources.getColor(android.R.color.holo_red_dark))
                    }
                }

                // Установка слушателей для кнопок "Next" и "Other Game"
                binding.btnNext2.setOnClickListener {
                    val intent = Intent(this, Differences::class.java)
                    intent.putExtra("count", currentRes)
                    startActivity(intent)
                }

                binding.otherGame2.setOnClickListener {
                    val intent = Intent(this, ListGames::class.java)
                    startActivity(intent)
                }
            }
        }
    }

   private fun genCode(level : String): String {
       var str = ""
       when(level){
           "e" -> {
               str="println(\"Hello, world!\")"
           }
           "m" -> {
                str = "for (number in numbers) {\n" +
                        "        sum += number\n" +
                        "    }"
           }
           "h" -> {
                str = "fun calculateAverage(numbers: List<Int>): Double {\n" +
                        "    var sum = 0.0\n" +
                        "    for (number in numbers) {\n" +
                        "        sum += number\n" +
                        "    }\n" +
                        "    return sum / numbers.size\n" +
                        "}"
           }
       }
       return str
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
                binding.game1.visibility = View.GONE
                binding.editQ.visibility = View.VISIBLE
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