package com.muhammadsabeelahmed.entv.Fragments;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.halilibo.bvpkotlin.BetterVideoPlayer;
import com.muhammadsabeelahmed.entv.Activities.DashboardActivity;
import com.muhammadsabeelahmed.entv.Global;
import com.muhammadsabeelahmed.entv.R;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;


public class VideoFragment extends Fragment {
    View v;
    BetterVideoPlayer bvp;
    String path1 = "https://5e50264bd6766.streamlock.net/tvchihuahua/videotvchihuahua/playlist.m3u8";
    CarouselView customCarouselView;
    int[] sampleImages = {R.drawable.logo, R.drawable.logo, R.drawable.logo, R.drawable.logo};
    String[] sampleTitles = {"Orange", "Grapes", "Strawberry", "Cherry"};
    String[] sampleNetworkImageURLs = {
            "https://conceptoweb-studio.com/apps/radiogm/ios/images/imgs-09-4.png",
            "https://conceptoweb-studio.com/apps/tvregional/ios/images/imgs-09-5.png",
            "https://conceptoweb-studio.com/apps/radiogm/ios/images/imgs-09-4.png",
            "https://conceptoweb-studio.com/apps/tvregional/ios/images/imgs-09-5.png"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_video, container, false);
        Global.device_back_tag = "VideoFragment";
        init();
        return v;
    }

    private void init() {
        DashboardActivity.main_text.setText("TV EN LINEA");
        customCarouselView = (CarouselView) v.findViewById(R.id.video_slider);
        customCarouselView.setPageCount(4);
        customCarouselView.setSlideInterval(4000);
        customCarouselView.setImageListener(imageListener);

        bvp = v.findViewById(R.id.player);
        bvp.setSource(Uri.parse(path1));
        bvp.showControls();

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