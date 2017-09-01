package com.coderwjq.shop.module.movie_detail.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coderwjq.shop.R;
import com.coderwjq.shop.module.movie_detail.model.MovieTipsBean;
import com.coderwjq.shop.utils.GlideManager;

/**
 * Created by coderwjq on 2017/8/31 9:08.
 */

public class MovieTipsAdapter extends BaseQuickAdapter<MovieTipsBean.DataBean.TipsBean, BaseViewHolder> {
    public MovieTipsAdapter() {
        super(R.layout.item_movie_tips, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, MovieTipsBean.DataBean.TipsBean item) {
        helper.setText(R.id.tv_tips, item.getContent());
        GlideManager.loadImage(mContext, item.getTipImg(), (ImageView) helper.getView(R.id.iv_tips));
    }
}
