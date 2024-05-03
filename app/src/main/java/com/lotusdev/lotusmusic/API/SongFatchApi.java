package com.lotusdev.lotusmusic.API;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;

import com.lotusdev.lotusmusic.Models.SongsData;

import java.util.ArrayList;

public class SongFatchApi {



    public ArrayList<SongsData> getallAudio(Context context){
        ArrayList<SongsData> tempAudio = new ArrayList<>();
        Uri collection;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            collection = MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_EXTERNAL);

        } else {
            collection = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        }
        String[] projection ={
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.ARTIST,
        };

        String sortOrder = MediaStore.Video.Media.DISPLAY_NAME + " ASC" ;

        Cursor cursor = context.getContentResolver().query(collection,projection,null,null,sortOrder);
        if(cursor != null)
        {
            while(cursor.moveToNext())
            {
                String album = cursor.getString(0);
                String title = cursor.getString(1);
                String duration = cursor.getString(2);
                String data = cursor.getString(3);
                String artist = cursor.getString(4);
                SongsData musicFile = new SongsData(data,title,artist,album,duration);
                Log.e("4456",data);
                tempAudio.add(musicFile);
            }
            cursor.close();
        }
        return tempAudio;
    }

}



