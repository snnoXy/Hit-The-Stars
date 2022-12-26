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
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class GameActivity : AppCompatActivity() {
    var skor = 0
    var maxSkor = 0
    var imageArray = ArrayList<ImageView>()
    var handler = Handler()
    var runnable = Runnable{ }
    var mediaPlayer : MediaPlayer ? = null
    var zorluk : Long = 700
    lateinit var sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        var mainIntent = Intent(GameActivity@this,MainActivity::class.java)
        var intent = intent
        zorluk = intent.getLongExtra("Difficulty",700)

        var skorText : TextView =findViewById(R.id.skor)
        skorText.text = "Skor : 0"

        sharedPreferences = this.getSharedPreferences("com.batuhansari.catchthekenny", Context.MODE_PRIVATE)
        hideImages()
        maxSkor = sharedPreferences.getInt("Max",0)
        maxSkorView.text = "Max : $maxSkor"

        imageArray.add(imageView1)
        imageArray.add(imageView2)
        imageArray.add(imageView3)
        imageArray.add(imageView4)
        imageArray.add(imageView5)
        imageArray.add(imageView6)
        imageArray.add(imageView7)
        imageArray.add(imageView8)
        imageArray.add(imageView9)
        imageArray.add(imageView10)
        imageArray.add(imageView11)
        imageArray.add(imageView12)
        imageArray.add(imageView13)
        imageArray.add(imageView14)
        imageArray.add(imageView15)
        imageArray.add(imageView16)


        object : CountDownTimer(27000,1000){
            override fun onFinish() {
                var timeText : TextView =findViewById(R.id.time)
                timeText.text = "Left : 0"

                if ( skor >= maxSkor) {
                    maxSkor=skor
                    sharedPreferences.edit().putInt("Max",maxSkor).apply()
                }

                handler.removeCallbacks(runnable)
                for (image in imageArray) {
                    image.visibility=View.INVISIBLE
                }
               // restart.visibility=View.VISIBLE

                // Alert
                val alert = AlertDialog.Builder(this@GameActivity)
                alert.setTitle("Restart?")
                alert.setMessage("Do you want to keep playing?")
                alert.setPositiveButton("Restart") {dialog,which->
                    //Restart Game
                    finish()
                    startActivity(intent)

                }
                alert.setNegativeButton("Finish") {dialog,which ->
                    finish()
                    startActivity(mainIntent)
                }
                alert.show()
            }

            override fun onTick(millisUntilFinished: Long) {
                var timeText : TextView =findViewById(R.id.time)
                timeText.text = "Left : ${millisUntilFinished/1000}"


            }
        }.start()

    }

    fun hideImages(){

        runnable = object : Runnable {
            override fun run() {
                for (image in imageArray) {
                    image.visibility=View.INVISIBLE
                }
                var random=(0..15).random()
                imageArray[random].visibility=View.VISIBLE
                handler.postDelayed(runnable,zorluk)
            }
        }
        handler.post(runnable)

    }
    fun  increaseScore(View: View){

        mediaPlayer = MediaPlayer.create(this,R.raw.sound)
        var textView : TextView =findViewById(R.id.skor)
        skor = skor + 1
        textView.text = "Score : $skor"
        mediaPlayer!!.start()

    }

    fun  decreaseScore(View: View){
        var textView : TextView =findViewById(R.id.skor)
        skor = skor - 1
        textView.text = "Score : $skor"
        mediaPlayer = MediaPlayer.create(this,R.raw.bad)
        mediaPlayer!!.start()

    }
    fun start (view : View){
        val intent= getIntent()
        finish()
        startActivity(intent)
    }
}

