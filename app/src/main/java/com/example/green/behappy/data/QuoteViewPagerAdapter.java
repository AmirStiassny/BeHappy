package com.example.green.behappy.data;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.green.behappy.QuoteFragment;

import java.util.List;

public class QuoteViewPagerAdapter extends FragmentPagerAdapter {

    private List<QuoteFragment> fragments;

    public QuoteViewPagerAdapter(FragmentManager fm, List<QuoteFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }
}
