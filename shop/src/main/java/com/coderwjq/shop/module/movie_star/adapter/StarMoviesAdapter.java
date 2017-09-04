package com.coderwjq.shop.module.movie_star.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coderwjq.shop.R;
import com.coderwjq.shop.module.movie_detail.MovieDetailActivity;
import com.coderwjq.shop.module.movie_star.model.StarMoviesBean;
import com.coderwjq.shop.utils.GlideManager;
import com.coderwjq.shop.utils.ImgSizeUtil;

public class StarMoviesAdapter extends BaseQuickAdapter<StarMoviesBean.DataBean.MoviesBean, BaseViewHolder> {
    public StarMoviesAdapter() {
        super(R.layout.item_star_movies, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, final StarMoviesBean.DataBean.MoviesBean item) {
        helper.setText(R.id.tv_movie_title, item.getTitle())
                .setText(R.id.tv_movie_name, item.getName())
                .setText(R.id.tv_movie_role, item.getMultiroles() == null || item.getMultiroles().equals("") ?
                        item.getMutlidutys() : item.getMultiroles());

        String imgUrl = ImgSizeUtil.resetPicUrl(item.getAvatar(), ".webp@210w_285h_1e_1c_1l");
        GlideManager.loadImage(mContext, imgUrl, (ImageView) helper.getView(R.id.iv_movie_img));

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieDetailActivity.invoke(mContext, item.getId());
            }
        });

    }
}
