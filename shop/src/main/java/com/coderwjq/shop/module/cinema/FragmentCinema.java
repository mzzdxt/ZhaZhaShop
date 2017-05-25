package com.coderwjq.shop.module.cinema;

import com.coderwjq.shop.R;
import com.coderwjq.shop.base.BaseFragment;

/**
 * @Created by coderwjq on 2017/5/25 14:50.
 * @Desc
 */

public class FragmentCinema extends BaseFragment {

    public static FragmentCinema getInstance() {
        return new FragmentCinema();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cinema;
    }
}
