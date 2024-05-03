package com.lotusdev.lotusmusic

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.lotusdev.lotusmusic.Service.SongStateService

class MainActivity : AppCompatActivity() {
    lateinit var lotus: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        lotus = findViewById(R.id.lotus)
        Handler().postDelayed(object : Runnable{
            override fun run() {
                    var Intent = Intent(applicationContext,MusicPlayer::class.java)
                setAnimation(lotus)
                    finish()
                    startActivity(Intent)
            }

        },2000)


    }
    private fun setAnimation(viewanimat: View) {
        val anim = AnimationUtils.loadAnimation(applicationContext, android.R.anim.fade_out)
        viewanimat.setAnimation(anim)
    }
}