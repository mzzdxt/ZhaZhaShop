package com.coderwjq.shop.module.movieDetail.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coderwjq.shop.R;
import com.coderwjq.shop.base.BaseConstant;
import com.coderwjq.shop.module.movieDetail.model.MoviePhotosBean;
import com.coderwjq.shop.utils.GlideManager;
import com.coderwjq.shop.utils.ImgSizeUtil;
import com.coderwjq.shop.utils.ToastUtil;

/**
 * Created by Cicinnus on 2017/2/11.
 */

public class MoviePhotosAdapter extends BaseMultiItemQuickAdapter<MoviePhotosBean, BaseViewHolder> {
    public MoviePhotosAdapter() {
        super(null);
        addItemType(BaseConstant.TYPE_MOVIE_DETAIL_VEDIO, R.layout.item_movie_vedio);
        addItemType(BaseConstant.TYPE_MOVIE_DETAIL_PHOTO, R.layout.item_movie_photo);
    }

    @Override
    protected void convert(BaseViewHolder helper, final MoviePhotosBean item) {
        switch (helper.getItemViewType()) {
            case BaseConstant.TYPE_MOVIE_DETAIL_VEDIO:
                if (helper.getAdapterPosition() == 0) {
                    helper.setText(R.id.tv_movie_video, "视频");
                }
                helper.setText(R.id.tv_video_num, String.format("%s", item.getVideoNum()));
                GlideManager.loadImage(mContext, item.getVideoImg(), (ImageView) helper.getView(R.id.iv_movie_video_img));

                helper.getView(R.id.iv_movie_video_img)
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ToastUtil.showShort(mContext, item.getMovieTitle());
//                                MovieVideoActivity.start(mContext, item.getMovieId(), 0, item.getMovieTitle(), item.getUrl());
                            }
                        });
                break;
            case BaseConstant.TYPE_MOVIE_DETAIL_PHOTO:
                if (helper.getAdapterPosition() == 1) {
                    helper.setText(R.id.tv_photo, "图片");
                } else {
                    helper.setText(R.id.tv_photo, "");
                }
                String imgUrl = ImgSizeUtil.resetPicUrl(item.getUrl(), "@100w_100h_1e_1c");
                GlideManager.loadImage(mContext, imgUrl, (ImageView) helper.getView(R.id.iv_movie_photo));
                break;
        }
    }
}
