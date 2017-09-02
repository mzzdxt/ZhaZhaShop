package com.coderwjq.shop.module.player.video_comment;

import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coderwjq.shop.R;
import com.coderwjq.shop.base.BaseConstant;
import com.coderwjq.shop.utils.GlideManager;
import com.coderwjq.shop.utils.UserUtils;

/**
 * Created by coderwjq on 2017/9/1 18:17.
 */

public class VideoCommentAdapter extends BaseMultiItemQuickAdapter<VideoCommentListBean.DataBean.CommentsBean, BaseViewHolder> {

    public VideoCommentAdapter() {
        super(null);
        addItemType(BaseConstant.TYPE_VIDEO_COMMENT_NO_REPLY, R.layout.item_video_comment_no_reply);
        addItemType(BaseConstant.TYPE_VIDEO_COMMENT_REPLY, R.layout.item_video_comment_reply);
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoCommentListBean.DataBean.CommentsBean item) {
        helper.setText(R.id.tv_author_name, item.getNickName())
                .setText(R.id.tv_comment_content, item.getContent())
                .setText(R.id.tv_pub_time, item.getTime());

        if (item.getAvatarUrl().equals("")) {
            helper.setImageResource(R.id.civ_author, R.drawable.icon_default);
        } else {
            GlideManager.loadImage(mContext, item.getAvatarUrl(), (ImageView) helper.getView(R.id.civ_author));
        }
        helper.setImageDrawable(R.id.iv_user_level, UserUtils.getUserLevelLable(mContext, item.getUserLevel()));
        switch (helper.getItemViewType()) {
            case BaseConstant.TYPE_VIDEO_COMMENT_REPLY:
                helper.setText(R.id.tv_reply, String.format("回复%s:", item.getRef().getNickName()));
                ((TextView) helper.getView(R.id.tv_reply_content)).setText(item.getRef().getContent());
                break;
            case BaseConstant.TYPE_VIDEO_COMMENT_NO_REPLY:
                break;
        }
    }
}
