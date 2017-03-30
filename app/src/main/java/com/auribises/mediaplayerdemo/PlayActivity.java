package com.auribises.mediaplayerdemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener{

    TextView txtSongName;
    Button btnPlay, btnStop;
    String songName;
    MyBindMusicService service;

    void initViews(){
        txtSongName = (TextView)findViewById(R.id.textViewTitle);
        btnPlay = (Button)findViewById(R.id.buttonPlay);
        btnStop = (Button)findViewById(R.id.buttonStop);

        Intent rcv = getIntent();
        songName = rcv.getStringExtra("keySong");
        txtSongName.setText(songName);

        btnPlay.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        initViews();

        Intent intent = new Intent(PlayActivity.this,MyBindMusicService.class);
        intent.putExtra("keySong",songName);
        //startService(intent);
        bindService(intent,connection,BIND_AUTO_CREATE);

    }

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            // Downcasting
            MyBindMusicService.MyBinder myBinder = (MyBindMusicService.MyBinder)iBinder;
            service = myBinder.getServiceReference();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.buttonPlay){
            //Intent intent = new Intent(PlayActivity.this,MyMusicService.class);
            //intent.putExtra("keySong",songName);
            //startService(intent);
            service.playMusic();
        }else{
            //Intent intent = new Intent(PlayActivity.this,MyMusicService.class);
            //stopService(intent);
            service.pauseMusic();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        service.stopMusic();
        unbindService(connection);
    }
}
