package com.muhammadsabeelahmed.entv.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
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
import com.muhammadsabeelahmed.entv.BackgroundSoundService;
import com.muhammadsabeelahmed.entv.Global;
import com.muhammadsabeelahmed.entv.PreferencesHandler;
import com.muhammadsabeelahmed.entv.R;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.io.IOException;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressPie;

public class RadioFragment extends Fragment {
    View v;
    ImageView btn_play;
    CarouselView customCarouselView;
    int[] sampleImages = {R.drawable.new_logo, R.drawable.new_logo, R.drawable.new_logo, R.drawable.new_logo};
    String[] sampleTitles = {"Orange", "Grapes", "Strawberry", "Cherry"};
    String[] sampleNetworkImageURLs = {
            "https://conceptoweb-studio.com/apps/radiogm/ios/images/imgs-09-4.png",
            "https://conceptoweb-studio.com/apps/tvregional/ios/images/imgs-09-5.png",
            "https://conceptoweb-studio.com/apps/radiogm/ios/images/imgs-09-4.png",
            "https://conceptoweb-studio.com/apps/tvregional/ios/images/imgs-09-5.png"
    };
    public static ACProgressPie dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_radio, container, false);
        Global.device_back_tag = "RadioFragment";
        //  progressDialog = new ProgressDialog(v.getContext());
        dialog = new ACProgressPie.Builder(v.getContext())
                .ringColor(Color.WHITE)
                .pieColor(Color.WHITE)
                .updateType(ACProgressConstant.PIE_AUTO_UPDATE)
                .build();
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
        //  mediaPlayer = new MediaPlayer();
        //    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        final PreferencesHandler preferencesHandler = new PreferencesHandler(v.getContext());
        if (preferencesHandler.getImg().equals("pause")) {
            btn_play.setBackgroundResource(R.drawable.play);
        } else {
            btn_play.setBackgroundResource(R.drawable.pause);

        }
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferencesHandler.getImg().equals("pause")) {
                    preferencesHandler.setImg("play");
                    Intent intent = new Intent(v.getContext(), BackgroundSoundService.class);
                    getActivity().startService(intent);
                    // mediaPlayer.pause();
                    //  started = false;
                    btn_play.setBackgroundResource(R.drawable.pause);
                } else if (preferencesHandler.getImg().equals("play")) {
                    preferencesHandler.setImg("pause");
                    Intent intent = new Intent(v.getContext(), BackgroundSoundService.class);
                    getActivity().stopService(intent);
                    //  mediaPlayer.start();
                    // started = true;
                    btn_play.setBackgroundResource(R.drawable.play);
                }
            }
        });
        //   new PlayTask().execute(stream);
    }


    // To set simple images
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {

            Picasso.with(v.getContext()).load(sampleNetworkImageURLs[position]).placeholder(sampleImages[position]).error(sampleImages[3]).fit().centerCrop().into(imageView);

            //imageView.setImageResource(sampleImages[position]);
        }
    };

}

