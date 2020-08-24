package com.muhammadsabeelahmed.entv.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muhammadsabeelahmed.entv.Global;
import com.muhammadsabeelahmed.entv.R;

public class VideoFragment extends Fragment {
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_video, container, false);
        Global.device_back_tag = "VideoFragment";
        return v;
    }
}