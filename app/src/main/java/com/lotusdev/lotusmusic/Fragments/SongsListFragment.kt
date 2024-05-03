package com.lotusdev.lotusmusic.Fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lotusdev.lotusmusic.Adpater.SongsRecyclerViewAdpater
import com.lotusdev.lotusmusic.ConstantObjects.Companion.list
import com.lotusdev.lotusmusic.ConstantObjects.Companion.mediaPlayer
import com.lotusdev.lotusmusic.ConstantObjects.Companion.pos
import com.lotusdev.lotusmusic.Fragments.MusicPLayerFragment.Companion.playpause
import com.lotusdev.lotusmusic.R
import com.lotusdev.lotusmusic.Service.SongStateService


class SongsListFragment : Fragment() {
        lateinit var recyclerView: RecyclerView
        lateinit var adapter : SongsRecyclerViewAdpater
        companion object {
            lateinit var card : CardView
            lateinit var button: ImageView
            lateinit var smallimage : ImageView
            lateinit var smallsong : TextView
            lateinit var smallArtist : TextView
            lateinit var next : ImageView
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(activity,"Create Fragment",Toast.LENGTH_SHORT)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_songs_list, container, false)
        recyclerView = v.findViewById(R.id.songContainer)
        button = v.findViewById(R.id.imagebutton1)
        next  = v.findViewById(R.id.imagebutton2)
        smallimage = v.findViewById(R.id.songimg1)
        smallsong = v.findViewById(R.id.songname1)
        card = v.findViewById(R.id.card2)
        adapter = SongsRecyclerViewAdpater(context,list)
        recyclerView.layoutManager = GridLayoutManager(context,2)
        recyclerView.adapter = adapter



        button.setOnClickListener {
            if(mediaPlayer != null && mediaPlayer!!.isPlaying){
                mediaPlayer!!.pause()
                playpause.setImageResource(R.drawable.play)
                button.setImageResource(R.drawable.play2)
            }else if(mediaPlayer == null){
                context?.startService(Intent(context, SongStateService::class.java))
            }
            else{
                mediaPlayer!!.start()
                playpause.setImageResource(R.drawable.pause)
                button.setImageResource(R.drawable.pause2)
            }
        }

        next.setOnClickListener {
            setAnimation(next)
            if (pos == list.size-1) {
                pos = 0
                context?.startService(Intent(context,SongStateService::class.java))
            }else
            {
                pos++
                context?.startService(Intent(context,SongStateService::class.java))
            }
        }



        return v
    }
    private fun setAnimation(viewanimat: View) {
        val anim = AnimationUtils.loadAnimation(context, R.anim.slide_right)
        viewanimat.setAnimation(anim)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

}