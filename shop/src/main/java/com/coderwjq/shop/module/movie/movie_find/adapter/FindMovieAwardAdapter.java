package com.coderwjq.shop.module.movie.movie_find.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coderwjq.shop.R;
import com.coderwjq.shop.module.movie.movie_find.model.AwardsMovieBean;
import com.coderwjq.shop.utils.GlideManager;

/**
 * Created by coderwjq on 2017/8/30 15:07.
 */

public class FindMovieAwardAdapter extends BaseQuickAdapter<AwardsMovieBean.DataBean, BaseViewHolder> {
    public FindMovieAwardAdapter() {
        super(R.layout.item_awards_movie, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, AwardsMovieBean.DataBean item) {
        helper.setText(R.id.tv_award_movie_title, item.getFestivalName())
                .setText(R.id.tv_award_movie_time, item.getHeldDate().substring(5, item.getHeldDate().length()))
                .setText(R.id.tv_award_prize_name, item.getPrizeName())
                .setText(R.id.tv_award_movie_name, item.getMovieName());

        String originUrl = item.getImg();
        final String imgUrl = originUrl.replace("/w.h/", "/") + ".webp@345w_480h_1e_1c_1l";//后缀为图片大小
        GlideManager.loadImage(mContext, imgUrl, (ImageView) helper.getView(R.id.iv_award_movie));

        helper.getView(R.id.rl_movie_img)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        MovieDetailActivity.start(mContext, item.getMovieId());
                    }
                });


        helper.getView(R.id.tv_award_movie_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AwardsMovieActivity.start(mContext, item.getFestivalId(), item.getFestSessionId());
            }
        });
    }
}
