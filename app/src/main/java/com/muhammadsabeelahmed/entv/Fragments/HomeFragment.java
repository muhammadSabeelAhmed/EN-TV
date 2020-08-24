package com.muhammadsabeelahmed.entv.Fragments;

import android.graphics.Point;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.muhammadsabeelahmed.entv.Global;
import com.muhammadsabeelahmed.entv.R;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;
import com.synnapps.carouselview.ViewListener;

public class HomeFragment extends Fragment implements View.OnClickListener {
    View v;
    CarouselView customCarouselView;
    TextView customCarouselLabel;
    Button pauseButton;
    ImageView btn_radio, btn_video;
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
        v = inflater.inflate(R.layout.fragment_home, container, false);
        Global.device_back_tag = "";
        init();
        return v;
    }

    private void init() {
        btn_radio = v.findViewById(R.id.btn_radio);
        btn_radio.setOnClickListener(this);
        btn_video = v.findViewById(R.id.btn_video);
        btn_video.setOnClickListener(this);
        customCarouselView = (CarouselView) v.findViewById(R.id.customCarouselView);
        customCarouselView.setPageCount(4);
        customCarouselView.setSlideInterval(4000);
        customCarouselView.setImageListener(imageListener);
    }

    // To set simple images
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {

            Picasso.with(v.getContext()).load(sampleNetworkImageURLs[position]).placeholder(sampleImages[position]).error(sampleImages[3]).fit().centerCrop().into(imageView);

            //imageView.setImageResource(sampleImages[position]);
        }
    };

    @Override
    public void onClick(View view) {
        switch (v.getId()) {
            case R.id.btn_video:
                Global.changeFragment(v.getContext(),new VideoFragment());
                getActivity().overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                break;
            case R.id.btn_radio:
                Global.changeFragment(v.getContext(),new RadioFragment());
                getActivity().overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                break;
        }
    }
}