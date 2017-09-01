package com.coderwjq.shop.module.movieDetail.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coderwjq.shop.R;
import com.coderwjq.shop.module.movieDetail.model.MovieProCommentBean;
import com.coderwjq.shop.utils.GlideManager;
import com.coderwjq.shop.utils.TimeUtils;

/**
 * Created by coderwjq on 2017/9/1 10:38.
 */

public class MovieProCommentAdapter extends BaseQuickAdapter<MovieProCommentBean.DataBean, BaseViewHolder> {
    public MovieProCommentAdapter() {
        super(R.layout.item_movie_pro_comment, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, MovieProCommentBean.DataBean item) {
        helper.setText(R.id.tv_author_name, item.getNickName())
                .setText(R.id.tv_author_title, item.getAuthInfo())
                .setText(R.id.tv_comment_content, item.getContent())
                .setText(R.id.tv_createDate, TimeUtils.dateYMD(item.getCreated()))
                .setText(R.id.tv_score, String.format("%s", (int) (item.getScore() * 2)));
        String imgUrl = item.getAvatarurl();
        imgUrl.replace("avatar", "180.180/avatar");
        GlideManager.loadImage(mContext, imgUrl, (ImageView) helper.getView(R.id.civ_author));
    }
}
