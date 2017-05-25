package com.coderwjq.shop.module.movie.movie_hot;

import com.coderwjq.shop.R;
import com.coderwjq.shop.base.BaseFragment;

/**
 * @Created by coderwjq on 2017/5/25 16:01.
 * @Desc
 */

public class FragmentMovieHot extends BaseFragment {

    public static FragmentMovieHot getInstance() {
        return new FragmentMovieHot();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot_movie;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
