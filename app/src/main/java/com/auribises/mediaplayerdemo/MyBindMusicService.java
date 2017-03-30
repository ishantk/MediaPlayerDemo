package com.auribises.mediaplayerdemo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.widget.Toast;

import java.io.IOException;

public class MyBindMusicService extends Service {

    IBinder ibRef = new MyBinder(); // Polymorphic Statement

    String songToPlay;
    MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this,"Service Created",Toast.LENGTH_LONG).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this,"Service Started",Toast.LENGTH_LONG).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"Service Destroyed",Toast.LENGTH_LONG).show();
    }

    @Override
    public IBinder onBind(Intent intent) {

        mediaPlayer = new MediaPlayer();

        Toast.makeText(this,"Service Bound",Toast.LENGTH_LONG).show();

        songToPlay = intent.getStringExtra("keySong");
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+songToPlay;

        try {
            mediaPlayer.setDataSource(path);
            //mediaPlayer.setDataSource(this, Uri.parse(url));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ibRef;
    }

    @Override
    public boolean onUnbind(Intent intent) {

        Toast.makeText(this,"Service unBound",Toast.LENGTH_LONG).show();



        return super.onUnbind(intent);
    }

    public void playMusic(){
        mediaPlayer.start();
    }

    public void pauseMusic(){
        mediaPlayer.pause();
    }

    public void stopMusic(){
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    // So that we can create a method which shall return us the reference of the Service
    class MyBinder extends Binder{

        MyBindMusicService getServiceReference(){
            return MyBindMusicService.this; // Retrn the reference of the current object of MyBindMusicService
        }

    }
}
