package com.lotusdev.lotusmusic

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Adapter
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.lotusdev.lotusmusic.API.SongFatchApi
import com.lotusdev.lotusmusic.Adpater.ViewPagerAdpater
import com.lotusdev.lotusmusic.ConstantObjects.Companion.REQUEST_CODE
import com.lotusdev.lotusmusic.ConstantObjects.Companion.list
import com.lotusdev.lotusmusic.Fragments.MusicPLayerFragment
import com.lotusdev.lotusmusic.Fragments.SongsListFragment

class MusicPlayer : AppCompatActivity() {
    lateinit var pager : ViewPager2
    lateinit var adapter: ViewPagerAdpater
    lateinit var namingTab : TabLayout
    lateinit var gif : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_music_player)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        pager = findViewById(R.id.pager)
        namingTab = findViewById(R.id.nameTab)
        adapter = ViewPagerAdpater(this)
        adapter.addFragment(MusicPLayerFragment(),"Player")
        adapter.addFragment(SongsListFragment(),"Library")
        pager.adapter = adapter
        TabLayoutMediator(namingTab,pager){tab,position -> tab.text = adapter.gettitle(position)}.attach()
        Handler().postDelayed(object : Runnable{
            override fun run() {
                Log.e("11111",namingTab.isSelected.toString())
            }

        },0)
        permission()


    }








    fun permission (){
        var collection : String
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            collection =  Manifest.permission.READ_MEDIA_AUDIO
        } else {
            collection =  Manifest.permission.READ_EXTERNAL_STORAGE
        }
        if (ContextCompat.checkSelfPermission(
                applicationContext,
                collection
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf<String>(collection),
                REQUEST_CODE
            )


        } else {
            list = SongFatchApi().getallAudio(applicationContext)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(applicationContext, "Permission Granted !", Toast.LENGTH_SHORT).show()
                list = SongFatchApi().getallAudio(applicationContext)
                // Now you can use 'list' here
            } else {
                Toast.makeText(applicationContext, "Permission Denied !", Toast.LENGTH_SHORT).show()
            }
        }
    }
}