package com.lotusdev.lotusmusic.Service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Handler
import android.os.IBinder
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.lotusdev.lotusmusic.ConstantObjects
import com.lotusdev.lotusmusic.ConstantObjects.Companion.convertDurationToMinutes
import com.lotusdev.lotusmusic.Fragments.MusicPLayerFragment
import com.lotusdev.lotusmusic.Fragments.MusicPLayerFragment.Companion.lottie
import com.lotusdev.lotusmusic.Fragments.MusicPLayerFragment.Companion.next1
import com.lotusdev.lotusmusic.Fragments.MusicPLayerFragment.Companion.playpause
import com.lotusdev.lotusmusic.Fragments.MusicPLayerFragment.Companion.prev
import com.lotusdev.lotusmusic.Fragments.SongsListFragment
import com.lotusdev.lotusmusic.Fragments.SongsListFragment.Companion.card
import com.lotusdev.lotusmusic.R
import kotlin.random.Random


class SongStateService : Service() {
    var i = true

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {


           if(i){
               lottie.visibility = View.INVISIBLE
               MusicPLayerFragment.seekBar.visibility = View.VISIBLE
               playpause.visibility = View.VISIBLE
               next1.visibility = View.VISIBLE
               prev.visibility = View.VISIBLE
           }

            card.visibility = View.VISIBLE
            MusicPLayerFragment.playpause.setImageResource(R.drawable.pause)
            SongsListFragment.button.setImageResource(R.drawable.pause2)
            ConstantObjects.uri = ConstantObjects.list[ConstantObjects.pos].path.toUri()
            ConstantObjects.img =
                ConstantObjects.art(ConstantObjects.list[ConstantObjects.pos].path)

            if (ConstantObjects.mediaPlayer != null) {
                Log.e("4477", "Working")
                ConstantObjects.mediaPlayer!!.stop()
                ConstantObjects.mediaPlayer!!.reset()
                ConstantObjects.mediaPlayer!!.release()
                ConstantObjects.mediaPlayer = MediaPlayer.create(applicationContext,
                    ConstantObjects.uri
                )
                ConstantObjects.mediaPlayer!!.start()

            } else {
                Log.e("4477", "Working....")
                ConstantObjects.mediaPlayer = MediaPlayer.create(applicationContext,
                    ConstantObjects.uri
                )
                ConstantObjects.mediaPlayer!!.start()
            }

            MusicPLayerFragment.seekBar.max = ConstantObjects.mediaPlayer!!.duration
            MusicPLayerFragment.finalTime.text =
                convertDurationToMinutes((ConstantObjects.list[ConstantObjects.pos].duration).toLong())
            val handler = Handler()
            handler.postDelayed(object : Runnable {
                override fun run() {
                    try {
                        MusicPLayerFragment.seekBar.progress = ConstantObjects.mediaPlayer!!.currentPosition
                        MusicPLayerFragment.liveTime.text =
                            convertDurationToMinutes(ConstantObjects.mediaPlayer!!.currentPosition.toLong())
                        handler.postDelayed(this, 1000)

                    } catch (e: Exception) {
                        MusicPLayerFragment.seekBar.progress = 0
                    }

                }

            }, 0)


            // Image Section
            if (ConstantObjects.img != null) {
                Glide.with(applicationContext!!).asBitmap().load(ConstantObjects.img)
                    .into(MusicPLayerFragment.playerimage)
                Glide.with(applicationContext!!).asBitmap().load(ConstantObjects.img)
                    .into(SongsListFragment.smallimage)
            } else {
                var i = Random.nextInt(5)
                MusicPLayerFragment.playerimage.setImageResource(R.drawable.img)
                SongsListFragment.smallimage.setImageResource(R.drawable.img)

            }
            MusicPLayerFragment.song.text = ConstantObjects.list[ConstantObjects.pos].title
            SongsListFragment.smallsong.text = ConstantObjects.list[ConstantObjects.pos].title
            if (ConstantObjects.list[ConstantObjects.pos].artist == "") {
                MusicPLayerFragment.artist.visibility = View.INVISIBLE
            }
            MusicPLayerFragment.artist.text = ConstantObjects.list[ConstantObjects.pos].artist


            // Palette Library for background color changer
            /* var bitmap = BitmapFactory.decodeByteArray(img,0,img!!.size)
                 Palette.from(bitmap).generate { palette ->
                     val swatch: Palette.Swatch? = palette?.dominantSwatch
                     if (swatch != null) {

                         val gradient = GradientDrawable(
                             GradientDrawable.Orientation.BOTTOM_TOP,
                             intArrayOf(swatch.rgb, swatch.rgb)
                         )
                         card.background = gradient

                     }
                 }*/
            ConstantObjects.mediaPlayer!!.setOnCompletionListener {
                if (ConstantObjects.pos > ConstantObjects.list.size - 1){
                    ConstantObjects.pos = 0}
                ConstantObjects.pos++
                startService(Intent(this,SongStateService::class.java))
            }




        return START_REDELIVER_INTENT
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}