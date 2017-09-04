package com.coderwjq.shop.module.movie_rank.favourable_rank;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coderwjq.shop.R;
import com.coderwjq.shop.module.movie_detail.MovieDetailActivity;
import com.coderwjq.shop.utils.GlideManager;
import com.coderwjq.shop.utils.ImgSizeUtil;

public class FavourableMovieAdapter extends BaseQuickAdapter<FavourableMovieBean.DataBean.MoviesBean, BaseViewHolder> {

    public FavourableMovieAdapter() {
        super(R.layout.item_hot_good_comment, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, final FavourableMovieBean.DataBean.MoviesBean item) {
        String imgUrl = ImgSizeUtil.processUrl(item.getImg(), 224, 315);
        GlideManager.loadImage(mContext, imgUrl, (ImageView) helper.getView(R.id.iv_movie_img));

        helper.setText(R.id.tv_movie_name, item.getNm())
                .setText(R.id.tv_movie_desc, item.getStar())
                .setText(R.id.tv_movie_desc, item.getPubDesc())
                .setText(R.id.tv_movie_score, String.format("%s", item.getSc()))
                .setText(R.id.tv_movie_rank, String.format("%s", helper.getAdapterPosition()));
        if (helper.getAdapterPosition() < 4) {
            ((ImageView) helper.getView(R.id.iv_movie_rank)).setImageResource(R.drawable.ic_yellow_angle_small);
        } else {
            ((ImageView) helper.getView(R.id.iv_movie_rank)).setImageResource(R.drawable.ic_gray_angle);
        }

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieDetailActivity.invoke(mContext, item.getId());
            }
        });
    }
}
