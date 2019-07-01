package com.example.mymusicplayer.service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;

import java.io.IOException;

public class MyMultimediaService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

    private MediaPlayer mediaPlayerService;

    public MyMultimediaService() {
        mediaPlayerService = new MediaPlayer();
        mediaPlayerService.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayerService.setOnPreparedListener(this);
        mediaPlayerService.setOnCompletionListener(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mediaPlayerService == null) {
            createAndConfigMediaPlayer();
        }
        if (mediaPlayerService.isPlaying()) {
            mediaPlayerService.stop();
        }
        mediaPlayerService.prepareAsync();

        return Service.START_STICKY;
    }

    private void createAndConfigMediaPlayer() {
        mediaPlayerService = new MediaPlayer();
        mediaPlayerService.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayerService.setOnPreparedListener(this);
        mediaPlayerService.setOnCompletionListener(this);
        final Uri mediaPath = Uri.parse("android.resource://" + getPackageName() + "/raw/");
        try {
            mediaPlayerService.setDataSource(getApplicationContext(), mediaPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();

    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mp.stop();
        mp = null;
    }
}
