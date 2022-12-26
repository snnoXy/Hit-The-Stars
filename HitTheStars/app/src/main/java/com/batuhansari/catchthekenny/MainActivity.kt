package com.batuhansari.catchthekenny

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.Image
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.batuhansari.catchthekenny.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var difficulty : Long = 700

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.startt.setOnClickListener{
            val intent = Intent(MainActivity@this,GameActivity::class.java)
            intent.putExtra("Difficulty",difficulty)
            finish()
            startActivity(intent)
        }

    }

    fun easy(View: View) {
        difficulty = 600
        binding.selectedDiff.text = "Easy"
    }
    fun normal(View: View) {
        difficulty = 500
        binding.selectedDiff.text = "Normal"
    }
    fun hard(View: View) {
        difficulty = 380
        binding.selectedDiff.text = "Hard"
    }

}

