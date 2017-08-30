package com.coderwjq.shop.module.discover.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coderwjq.shop.R;
import com.coderwjq.shop.base.BaseConstant;
import com.coderwjq.shop.module.discover.model.DiscoverBean;
import com.coderwjq.shop.utils.GlideManager;
import com.coderwjq.shop.utils.TimeUtils;
import com.coderwjq.shop.utils.ToastUtil;

/**
 * Created by coderwjq on 2017/8/30 17:52.
 */

public class DiscoverAdapter extends BaseMultiItemQuickAdapter<DiscoverBean.DataBean.FeedsBean, BaseViewHolder> {

    public DiscoverAdapter() {
        super(null);
        addItemType(BaseConstant.TYPE_DISCOVER_ONE_IMG, R.layout.item_discover_one_img);
        addItemType(BaseConstant.TYPE_DISCOVER_MULTI_IMG, R.layout.item_discover_muilt_img);
        addItemType(BaseConstant.TYPE_DISCOVER_BIG_IMG, R.layout.item_discover_big_img);
        addItemType(BaseConstant.TYPE_DISCOVER_ONE_BIG_IMG, R.layout.item_discover_one_big_img);
    }

    @Override
    protected void convert(BaseViewHolder helper, final DiscoverBean.DataBean.FeedsBean item) {

        switch (helper.getItemViewType()) {
            case BaseConstant.TYPE_DISCOVER_ONE_IMG:
                helper.setText(R.id.tv_disc_title, item.getTitle())
                        .setText(R.id.tv_viewCount, String.format("%s", item.getViewCount()))
                        .setText(R.id.tv_video_comment, String.format("%s", item.getCommentCount()))
                        .setText(R.id.tv_nickName, String.format("%s", item.getUser().getNickName()))
                        .setText(R.id.tv_time, TimeUtils.dateYMDHM(item.getTime()));
                GlideManager.loadImage(mContext, item.getImages().get(0).getUrl(), (ImageView) helper.getView(R.id.iv_img));
                break;
            case BaseConstant.TYPE_DISCOVER_MULTI_IMG:
                helper.setText(R.id.tv_disc_title, item.getTitle())
                        .setText(R.id.tv_viewCount, String.format("%s", item.getViewCount()))
                        .setText(R.id.tv_video_comment, String.format("%s", item.getCommentCount()))
                        .setText(R.id.tv_nickName, String.format("%s", item.getUser().getNickName()))
                        .setText(R.id.tv_time, TimeUtils.dateYMDHM(item.getTime()));
                helper.itemView
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ToastUtil.showShort(mContext, item.getImages().get(0).getTargetId() + "");
//                                BaseWebViewActivity.start(mContext, item.getImages().get(0).getTargetId());
                            }
                        });
                GlideManager.loadImage(mContext, item.getImages().get(0).getUrl(), (ImageView) helper.getView(R.id.iv_img1));
                GlideManager.loadImage(mContext, item.getImages().get(1).getUrl(), (ImageView) helper.getView(R.id.iv_img2));
                GlideManager.loadImage(mContext, item.getImages().get(2).getUrl(), (ImageView) helper.getView(R.id.iv_img3));
                break;
            case BaseConstant.TYPE_DISCOVER_BIG_IMG:
                helper.setText(R.id.tv_disc_title, item.getTitle())
                        .setText(R.id.tv_viewCount, String.format("%s", item.getViewCount()))
                        .setText(R.id.tv_video_comment, String.format("%s", item.getCommentCount()))
                        .setText(R.id.tv_nickName, String.format("%s", item.getUser().getNickName()))
                        .setText(R.id.tv_time, TimeUtils.dateYMDHM(item.getTime()));
                helper.setText(R.id.tv_imgCount, String.format("%s", item.getImageCount()));
                GlideManager.loadImage(mContext, item.getImages().get(0).getUrl(), (ImageView) helper.getView(R.id.iv_img1));
                GlideManager.loadImage(mContext, item.getImages().get(1).getUrl(), (ImageView) helper.getView(R.id.iv_img2));
                GlideManager.loadImage(mContext, item.getImages().get(2).getUrl(), (ImageView) helper.getView(R.id.iv_img3));
                break;
            case BaseConstant.TYPE_DISCOVER_ONE_BIG_IMG:
                helper.setText(R.id.tv_disc_title, item.getTitle())
                        .setText(R.id.tv_nickName, String.format("%s", item.getUser().getNickName()));
                GlideManager.loadImage(mContext, item.getImages().get(0).getUrl(), (ImageView) helper.getView(R.id.iv_img));
                break;
        }

    }
}
