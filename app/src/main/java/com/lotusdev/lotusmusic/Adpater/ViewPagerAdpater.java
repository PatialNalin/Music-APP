package com.lotusdev.lotusmusic.Adpater;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class ViewPagerAdpater extends FragmentStateAdapter {
    ArrayList<Fragment> fragment = new ArrayList<>();
    ArrayList<String> title = new ArrayList<>();
    public ViewPagerAdpater(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public void addFragment(Fragment frag,String name){
        fragment.add(frag);
        title.add(name);
    }
    public String gettitle(int position){
        return title.get(position);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragment.get(position);
    }

    @Override
    public int getItemCount() {
        return fragment.size();
    }
}
