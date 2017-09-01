package com.coderwjq.shop.module.movie_detail.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coderwjq.shop.R;

/**
 * Created by coderwjq on 2017/9/1 9:10.
 */

public class MovieAwardsAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public MovieAwardsAdapter() {
        super(R.layout.item_movie_awards,null);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_award_name,item);
    }
}
