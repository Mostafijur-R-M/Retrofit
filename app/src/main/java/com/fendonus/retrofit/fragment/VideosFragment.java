package com.fendonus.retrofit.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fendonus.retrofit.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideosFragment extends Fragment {

    private List<String> listDataGroup;

    public VideosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_videos, container, false);

        listDataGroup.add(getString(R.string.text_accounts));
        listDataGroup.add(getString(R.string.text_uses_smart_phone_about));
        listDataGroup.add(getString(R.string.text_uses_computer_about));
        listDataGroup.add(getString(R.string.text_basic_mind_qna));
        return view;
    }

}
