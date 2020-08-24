package com.muhammadsabeelahmed.entv;

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

import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;
import com.synnapps.carouselview.ViewListener;

public class HomeFragment extends Fragment {
    int width, height;
    View v;
    CarouselView customCarouselView;
    TextView customCarouselLabel;
    Button pauseButton;
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
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;
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
}