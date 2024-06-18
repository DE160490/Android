package com.example.exerciseseventeen;

import static android.content.ContentValues.TAG;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    MediaPlayer mediaPlayer;
    public MyService() {
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this,R.raw.bh);
        mediaPlayer.setLooping(true); // Set looping
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();
        if (action != null) {
            switch (action) {
                case "START":
                    startMusic();
                    break;
                case "STOP":
                    stopMusic();
                    break;
            }
        }
        return START_NOT_STICKY;
    }
    private void startMusic() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            Log.d(TAG, "Music started");
        }
    }
    private void stopMusic() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer = MediaPlayer.create(this, R.raw.bh);
            Log.d(TAG, "Music stopped");
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}