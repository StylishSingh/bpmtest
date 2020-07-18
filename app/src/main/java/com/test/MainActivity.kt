package com.test

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var remainingMilli: Long = 60000
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var btnPlay: Button
    private lateinit var btnPause: Button
    private lateinit var btnStop: Button
    private lateinit var timer: CountDownTimer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnPlay = findViewById(R.id.btn_play)
        btnPause = findViewById(R.id.btn_pause)
        btnStop = findViewById(R.id.btn_stop)


        btnPlay.setOnClickListener(this)
        btnPause.setOnClickListener(this)
        btnStop.setOnClickListener(this)
        setTimer()
        mediaPlayer = MediaPlayer.create(
            this, R.raw.sound
        )

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_play -> {

                timer.start()
                btnPlay.isEnabled = false
                btnPause.isEnabled = true
                btnStop.isEnabled = true
            }

            R.id.btn_pause -> {
                timer.cancel()
                timer.onFinish()
            }

            R.id.btn_stop -> {
                timer.cancel()
                timer.onFinish()
            }

        }
    }

    private fun setTimer() {
        timer = object : CountDownTimer(remainingMilli, 1000) {
            override fun onFinish() {

                btnStop.isEnabled = false
                btnPause.isEnabled = false
                btnPlay.isEnabled = true
                stopSound()

                println("Finish")
            }

            override fun onTick(millisUntilFinished: Long) {
                playSound()
                println("Time: $millisUntilFinished")
            }


        }
    }

    private fun playSound() {
        mediaPlayer.start()
    }


    private fun stopSound() {

        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.reset()
            mediaPlayer.release()
        }
    }
}