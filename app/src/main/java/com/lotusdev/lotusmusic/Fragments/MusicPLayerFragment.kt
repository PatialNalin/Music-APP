package com.lotusdev.lotusmusic.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.lotusdev.lotusmusic.ConstantObjects
import com.lotusdev.lotusmusic.ConstantObjects.Companion.list
import com.lotusdev.lotusmusic.ConstantObjects.Companion.mediaPlayer
import com.lotusdev.lotusmusic.ConstantObjects.Companion.pos
import com.lotusdev.lotusmusic.Fragments.SongsListFragment.Companion.card
import com.lotusdev.lotusmusic.R
import com.lotusdev.lotusmusic.Service.SongStateService


class MusicPLayerFragment : Fragment() {

    companion object{
        lateinit var playpause : ImageView
        lateinit var seekBar: SeekBar
        lateinit var finalTime : TextView
        lateinit var liveTime : TextView
        lateinit var playerimage : ImageView
        lateinit var song : TextView
        lateinit var artist : TextView
        lateinit var lottie : LottieAnimationView
        lateinit var next1 : ImageView
        lateinit var prev :  ImageView
    }

    lateinit var cardimg : CardView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_music_p_layer, container, false)
        playpause = v.findViewById(R.id.imageView)
        seekBar = v.findViewById(R.id.seekbar)
        finalTime = v.findViewById(R.id.timefinal)
        liveTime = v.findViewById(R.id.liveTime)
        playerimage = v.findViewById(R.id.playerimage)
        next1 = v.findViewById(R.id.imageView3)
        prev = v.findViewById(R.id.imageView2)
        song = v.findViewById(R.id.songname)
        artist = v.findViewById(R.id.artistname)
        cardimg = v.findViewById(R.id.cardid)
        lottie = v.findViewById(R.id.lottie)
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(fromUser){
                    mediaPlayer?.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
        playpause.setOnClickListener {
            if(mediaPlayer != null && mediaPlayer!!.isPlaying){
                mediaPlayer!!.pause()
                playpause.setImageResource(R.drawable.play)
                SongsListFragment.button.setImageResource(R.drawable.play2)
            }else if(mediaPlayer == null){
                context?.startService(Intent(context,SongStateService::class.java))
            }
            else{
                mediaPlayer!!.start()
                playpause.setImageResource(R.drawable.pause)
                SongsListFragment.button.setImageResource(R.drawable.pause2)
            }
        }


       next1.setOnClickListener {
           val cardanim = AnimationUtils.loadAnimation(context,R.anim.cardanim)
           cardimg.animation = cardanim
           setAnimation(next1)
           if (pos == list.size-1) {
               pos = 0
               context?.startService(Intent(context,SongStateService::class.java))
           }else
           {
               pos++
               context?.startService(Intent(context,SongStateService::class.java))
           }
       }

        prev.setOnClickListener {
            val anim = AnimationUtils.loadAnimation(context, R.anim.slideleft)
            val cardanim = AnimationUtils.loadAnimation(context,R.anim.cardanim)
            cardimg.animation = cardanim
               prev.setAnimation(anim)
            if (pos == 0) {
                pos = list.size - 1
                context?.startService(Intent(context,SongStateService::class.java))
            }else
            {
                pos--
                context?.startService(Intent(context,SongStateService::class.java))
            }
        }


        return v
    }

    private fun setAnimation(viewanimat: View) {
        val anim = AnimationUtils.loadAnimation(context, R.anim.slide_right)
        viewanimat.setAnimation(anim)
    }

}