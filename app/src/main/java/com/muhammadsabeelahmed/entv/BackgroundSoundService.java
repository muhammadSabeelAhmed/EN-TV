package com.muhammadsabeelahmed.entv;

import android.app.ProgressDialog;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.muhammadsabeelahmed.entv.Fragments.RadioFragment;

import java.io.IOException;

public class BackgroundSoundService extends Service {
    MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        //  mediaPlayer.setLooping(true); // Set looping
        //    mediaPlayer.setVolume(100, 100);
//        try {
//            mediaPlayer.setDataSource(BackgroundSoundService.this, Uri.parse(stream));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        new PlayTask().execute("https://streamingcwsradio30.com:7062/");
        Toast.makeText(getApplicationContext(), "Playing Music in Background", Toast.LENGTH_SHORT).show();
        return startId;
    }

    public void onStart(Intent intent, int startId) {
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    @Override
    public void onLowMemory() {
    }

    private class PlayTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            RadioFragment.dialog.show();
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                mediaPlayer.setDataSource(strings[0]);
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            RadioFragment.dialog.dismiss();
            super.onPostExecute(aBoolean);
        }
    }
}
