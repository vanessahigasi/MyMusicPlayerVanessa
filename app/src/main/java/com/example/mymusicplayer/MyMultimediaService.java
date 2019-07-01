package com.example.mymusicplayer;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;

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
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onPrepared(MediaPlayer mp) {

    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }
}
