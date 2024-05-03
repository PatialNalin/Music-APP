package com.lotusdev.lotusmusic.Adpater

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lotusdev.lotusmusic.ConstantObjects.Companion.art
import com.lotusdev.lotusmusic.ConstantObjects.Companion.pos
import com.lotusdev.lotusmusic.Fragments.SongsListFragment.Companion.card
import com.lotusdev.lotusmusic.Models.SongsData
import com.lotusdev.lotusmusic.R
import com.lotusdev.lotusmusic.Service.SongStateService


class SongsRecyclerViewAdpater(var context : Context?, var list : ArrayList<SongsData>) :
    RecyclerView.Adapter<SongsRecyclerViewAdpater.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SongsRecyclerViewAdpater.ViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_recycler_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SongsRecyclerViewAdpater.ViewHolder, position: Int) {
        var song : SongsData = list[position]
        holder.text.setText(song.title)
        var image = art(song.path)
        if(image != null) {
           /* val myOptions = RequestOptions()
                .centerCrop() // or centerCrop
                .override(500, 500)*/
            Glide.with(context!!).asBitmap().load(image).into(holder.image)
        }else{
            holder.image.setImageResource(R.drawable.music_b)
        }

        holder.itemView.setOnClickListener {
            pos = position
            context?.startService(Intent(context,SongStateService::class.java))
            val anim = AnimationUtils.loadAnimation(context, R.anim.baranim)
            card.setAnimation(anim)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView
        val text: TextView

        init {
            image = itemView.findViewById(R.id.playlist)
            text = itemView.findViewById(R.id.SongName)
        }
    }

}