package com.muhammadsabeelahmed.entv.Fragments;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.muhammadsabeelahmed.entv.Activities.DashboardActivity;
import com.muhammadsabeelahmed.entv.Global;
import com.muhammadsabeelahmed.entv.R;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.io.IOException;

public class RadioFragment extends Fragment {
    View v;
    ImageView btn_play;
    CarouselView customCarouselView;
    int[] sampleImages = {R.drawable.logo, R.drawable.logo, R.drawable.logo, R.drawable.logo};
    String[] sampleTitles = {"Orange", "Grapes", "Strawberry", "Cherry"};
    String[] sampleNetworkImageURLs = {
            "https://conceptoweb-studio.com/apps/radiogm/ios/images/imgs-09-4.png",
            "https://conceptoweb-studio.com/apps/tvregional/ios/images/imgs-09-5.png",
            "https://conceptoweb-studio.com/apps/radiogm/ios/images/imgs-09-4.png",
            "https://conceptoweb-studio.com/apps/tvregional/ios/images/imgs-09-5.png"
    };

    private final static String stream = "https://streamingcwsradio30.com:7062/";
    MediaPlayer mediaPlayer;
    boolean started = false;
    boolean prepared = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_radio, container, false);
        Global.device_back_tag = "RadioFragment";
        init();
        return v;

    }

    private void init() {
        DashboardActivity.main_text.setText("RADIO EN LINEA");
        btn_play = v.findViewById(R.id.btn_play);
        customCarouselView = (CarouselView) v.findViewById(R.id.radio_slider);
        customCarouselView.setPageCount(4);
        customCarouselView.setSlideInterval(4000);
        customCarouselView.setImageListener(imageListener);
        btn_play.setEnabled(false);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (started) {
                    mediaPlayer.pause();
                    started = false;
                    btn_play.setBackgroundResource(R.drawable.play);
                } else {
                    mediaPlayer.start();
                    started = true;
                    btn_play.setBackgroundResource(R.drawable.pause);
                }
            }
        });
        new PlayTask().execute(stream);
    }


    // To set simple images
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {

            Picasso.with(v.getContext()).load(sampleNetworkImageURLs[position]).placeholder(sampleImages[position]).error(sampleImages[3]).fit().centerCrop().into(imageView);

            //imageView.setImageResource(sampleImages[position]);
        }
    };

    private class PlayTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {

            try {
                mediaPlayer.setDataSource(strings[0]);
                mediaPlayer.prepare();
                prepared = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return prepared;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            btn_play.setEnabled(true);
            btn_play.setBackgroundResource(R.drawable.play);

        }
    }
}