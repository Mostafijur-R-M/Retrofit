package com.fendonus.retrofit.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.fendonus.retrofit.fragment.FilesFragment;
import com.fendonus.retrofit.fragment.NotesFragment;
import com.fendonus.retrofit.fragment.VideosFragment;

public class TabAccessorAdapter extends FragmentPagerAdapter {

    public TabAccessorAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                VideosFragment videosFragment = new VideosFragment();
                return videosFragment;
            case 1:
                NotesFragment notesFragment = new NotesFragment();
                return notesFragment;
            case 2:
                FilesFragment filesFragment = new FilesFragment();
                return filesFragment;
                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Videos";
            case 1:
                return "Notes";
            case 2:
                return "Files";
                default:
                    return null;
        }

    }
}
