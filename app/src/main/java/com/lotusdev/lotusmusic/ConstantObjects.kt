package com.lotusdev.lotusmusic

import android.app.Application
import android.app.ProgressDialog.show
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.GradientDrawable
import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.net.Uri
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.net.toUri
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.lotusdev.lotusmusic.Fragments.MusicPLayerFragment
import com.lotusdev.lotusmusic.Fragments.MusicPLayerFragment.Companion.artist
import com.lotusdev.lotusmusic.Fragments.MusicPLayerFragment.Companion.finalTime
import com.lotusdev.lotusmusic.Fragments.MusicPLayerFragment.Companion.liveTime
import com.lotusdev.lotusmusic.Fragments.MusicPLayerFragment.Companion.playerimage
import com.lotusdev.lotusmusic.Fragments.MusicPLayerFragment.Companion.playpause
import com.lotusdev.lotusmusic.Fragments.MusicPLayerFragment.Companion.seekBar
import com.lotusdev.lotusmusic.Fragments.MusicPLayerFragment.Companion.song
import com.lotusdev.lotusmusic.Fragments.SongsListFragment
import com.lotusdev.lotusmusic.Fragments.SongsListFragment.Companion.button
import com.lotusdev.lotusmusic.Fragments.SongsListFragment.Companion.card
import com.lotusdev.lotusmusic.Fragments.SongsListFragment.Companion.smallArtist
import com.lotusdev.lotusmusic.Fragments.SongsListFragment.Companion.smallimage
import com.lotusdev.lotusmusic.Fragments.SongsListFragment.Companion.smallsong
import com.lotusdev.lotusmusic.Models.SongsData
import kotlin.concurrent.thread
import kotlin.random.Random

class ConstantObjects : Application() {

    companion object {
        lateinit var list: ArrayList<SongsData>
        val REQUEST_CODE = 1
        var mediaPlayer: MediaPlayer? = null
        var img: ByteArray? = null
        var pos: Int = -1
        lateinit var uri: Uri

        //convert art to image
        fun art(uri: String): ByteArray? {
            var retriever = MediaMetadataRetriever()
            retriever.setDataSource(uri)
            var art = retriever.embeddedPicture
            retriever.release();
            return art
        }
        fun convertDurationToMinutes(durationInMillis: Long): String {
            // Convert milliseconds to minutes and seconds
            val minutes = durationInMillis / (1000 * 60)
            val seconds = (durationInMillis % (1000 * 60)) / 1000
            return String.format("%02d:%02d", minutes, seconds)
        }

    }
}
