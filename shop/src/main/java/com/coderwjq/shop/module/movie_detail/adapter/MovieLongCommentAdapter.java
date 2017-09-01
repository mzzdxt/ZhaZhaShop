package com.coderwjq.shop.module.movie_detail.adapter;

import android.graphics.drawable.Drawable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coderwjq.shop.R;
import com.coderwjq.shop.module.movie_detail.model.MovieLongCommentBean;
import com.coderwjq.shop.utils.GlideManager;
import com.coderwjq.shop.utils.TimeUtils;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by coderwjq on 2017/9/1 10:38.
 */

public class MovieLongCommentAdapter extends BaseQuickAdapter<MovieLongCommentBean.DataBean.FilmReviewsBean, BaseViewHolder> {
    public MovieLongCommentAdapter() {
        super(R.layout.item_long_comment, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, MovieLongCommentBean.DataBean.FilmReviewsBean item) {
        helper.setText(R.id.tv_author_name, item.getAuthor().getNickName())
                .setText(R.id.tv_comment_title, item.getTitle())
                .setText(R.id.tv_comment_content, item.getText())
                .setText(R.id.tv_view_count, String.format("%s", item.getViewCount()))
                .setText(R.id.tv_comment_count, String.format("%s", item.getCommentCount()))
                .setText(R.id.tv_pub_time, TimeUtils.dateMD(item.getCreated()));

        Drawable icon = null;
        switch (item.getAuthor().getUserLevel()) {
            case 1:
                icon = mContext.getResources().getDrawable(R.drawable.ic_lv1, null);
                break;
            case 2:
                icon = mContext.getResources().getDrawable(R.drawable.ic_lv2, null);
                break;
            case 3:
                icon = mContext.getResources().getDrawable(R.drawable.ic_lv3, null);
                break;
            case 4:
                icon = mContext.getResources().getDrawable(R.drawable.ic_lv4, null);
                break;
            case 5:
                icon = mContext.getResources().getDrawable(R.drawable.ic_lv5, null);
                break;
        }
        helper.setImageDrawable(R.id.iv_user_level, icon);
        GlideManager.loadImage(mContext, item.getAuthor().getAvatarurl(), (CircleImageView) helper.getView(R.id.civ_author));
    }
}
