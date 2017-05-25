package com.coderwjq.shop.module.movie.movie_main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.coderwjq.shop.base.BaseFragment;

import java.util.ArrayList;

/**
 * @Created by coderwjq on 2017/5/25 16:11.
 * @Desc
 */

public class MovieMainAdapter extends FragmentPagerAdapter {
    private ArrayList<BaseFragment> mFragmentList = new ArrayList<>();

    public MovieMainAdapter(FragmentManager fm, ArrayList<BaseFragment> fragmentList) {
        super(fm);
        mFragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList == null ? 0 : mFragmentList.size();
    }
}
