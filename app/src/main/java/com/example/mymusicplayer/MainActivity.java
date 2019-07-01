package com.example.mymusicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.mymusicplayer.R.id.action_settings;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MediaPlayer.OnPreparedListener {

    private MediaPlayer mediaPlayer;
    private ArrayList<Integer> playlist;
    private Timer timer;
    int i = 0;
    int pause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnPlay = findViewById(R.id.activity_main__btn__play);
        btnPlay.setOnClickListener(this);

        final Button btnStop = findViewById(R.id.activity_main__btn__stop);
        btnStop.setOnClickListener(this);

        final Button btnPause = findViewById(R.id.activity_main__btn__pause);
        btnPause.setOnClickListener(this);

        final Button btnForward = findViewById(R.id.activity_main__btn__forward);
        btnForward.setOnClickListener(this);

        final Button btnPrevious = findViewById(R.id.activity_main__btn__previous);
        btnPrevious.setOnClickListener(this);

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        playlist = new ArrayList<>();
        playlist.add(R.raw.bensoundbrazilsamba);
        playlist.add(R.raw.bensoundcountryboy);
        playlist.add(R.raw.bensoundindia);
        playlist.add(R.raw.bensoundlittleplanet);
        playlist.add(R.raw.bensoundpsychedelic);
        playlist.add(R.raw.bensoundrelaxing);
        playlist.add(R.raw.bensoundtheelevatorbossanova);

        mediaPlayer = MediaPlayer.create(this,playlist.get(i));
        mediaPlayer.start();
        timer = new Timer();
        if (playlist.size()>1) playNext();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void playNext() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mediaPlayer.reset();
                mediaPlayer = MediaPlayer.create(MainActivity.this,playlist.get(++i));
                mediaPlayer.start();
                if (playlist.size() > i+1) {
                    playNext();
                }
            }
        },mediaPlayer.getDuration()+100);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main__btn__play:
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                }
                break;
            case R.id.activity_main__btn__pause:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    pause = mediaPlayer.getCurrentPosition();
                }
                break;
            case R.id.activity_main__btn__stop:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                break;
            case R.id.activity_main__btn__forward:
                if (mediaPlayer.isPlaying()) {
                    if (i != playlist.size() -1){
                        i++;
                        mediaPlayer.stop();
                        mediaPlayer = MediaPlayer.create(this,playlist.get(i));
                        mediaPlayer.start();
                    }

                }
                break;
            case R.id.activity_main__btn__previous:
                if(mediaPlayer.isPlaying()){
                    if( i != 0){
                        i--;
                        mediaPlayer.stop();
                        mediaPlayer = MediaPlayer.create(this,playlist.get(i));
                        mediaPlayer.start();
                    }
                }

        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }

    @Override
    public void onDestroy() {
        if (mediaPlayer.isPlaying())
            mediaPlayer.stop();
        timer.cancel();
        super.onDestroy();
    }
}
