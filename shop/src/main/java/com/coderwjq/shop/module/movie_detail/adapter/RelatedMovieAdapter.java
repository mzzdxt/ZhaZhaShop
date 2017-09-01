package com.coderwjq.shop.module.movie_detail.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coderwjq.shop.R;
import com.coderwjq.shop.module.movie_detail.MovieDetailActivity;
import com.coderwjq.shop.module.movie_detail.model.RelatedMovieBean;
import com.coderwjq.shop.utils.GlideManager;
import com.coderwjq.shop.utils.ImgSizeUtil;

/**
 * Created by coderwjq on 2017/9/1 11:12.
 */

public class RelatedMovieAdapter extends BaseQuickAdapter<RelatedMovieBean.DataBean.ItemsBean, BaseViewHolder> {
    public RelatedMovieAdapter() {
        super(R.layout.item_related_movie, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, final RelatedMovieBean.DataBean.ItemsBean item) {
        //255*234
        helper.setText(R.id.tv_movie_name, item.getTitle())
                .setText(R.id.tv_movie_score, String.format("%s", item.getSc() == 0 ? "暂无评分" : item.getSc()));
        String imgUrl = ImgSizeUtil.processUrl(item.getImg(), 255, 345);
        GlideManager.loadImage(mContext, imgUrl, (ImageView) helper.getView(R.id.iv_movie_img));
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieDetailActivity.invoke(mContext, Integer.parseInt(item.getDesc()));
            }
        });
    }
}
