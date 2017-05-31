package com.coderwjq.shop.module.movie.movie_wait.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coderwjq.shop.R;
import com.coderwjq.shop.module.movie.movie_wait.bean.TrailerRecommendBean;
import com.coderwjq.shop.utils.GlideManager;

/**
 * @Created by coderwjq on 2017/5/25 16:01.
 * @Desc
 */

public class TrailerRecommendAdapter extends BaseQuickAdapter<TrailerRecommendBean.DataBean, BaseViewHolder> {
    public TrailerRecommendAdapter() {
        super(R.layout.item_wait_movie_trailer_recommend, null);
    }

    @Override
    protected void convert(final BaseViewHolder helper, TrailerRecommendBean.DataBean item) {
        GlideManager.loadImage(mContext, item.getImg() + ".webp@405w_225h_1e_1c_1l", (ImageView) helper.getView(R.id.iv_trailer_recommend));
        helper.setText(R.id.tv_trailer_movie_title, item.getMovieName())
                .setText(R.id.tv_trailer_movie_desc, item.getName());
    }
}
