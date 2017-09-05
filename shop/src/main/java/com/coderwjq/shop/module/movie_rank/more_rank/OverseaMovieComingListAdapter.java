package com.coderwjq.shop.module.movie_rank.more_rank;


import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coderwjq.shop.R;
import com.coderwjq.shop.base.BaseConstant;
import com.coderwjq.shop.module.movie_detail.MovieDetailActivity;
import com.coderwjq.shop.module.movie_rank.oversea_rank.model.OverseaComingMovieBean;
import com.coderwjq.shop.utils.GlideManager;
import com.coderwjq.shop.utils.ImgSizeUtil;

public class OverseaMovieComingListAdapter extends BaseMultiItemQuickAdapter<OverseaComingMovieBean.DataBean.ComingBean, BaseViewHolder> {

    public OverseaMovieComingListAdapter() {
        super(null);
        addItemType(BaseConstant.TYPE_OVERSEA_NORMAL, R.layout.item_oversea_movie);
        addItemType(BaseConstant.TYPE_OVERSEA_HEAD_LINE, R.layout.item_oversea_movie_headline);
        addItemType(BaseConstant.TYPE_OVERSEA_BUY, R.layout.item_oversea_movie_buy);
        addItemType(BaseConstant.TYPE_OVERSEA_PRESALE, R.layout.item_oversea_movie_presell);
    }

    @Override
    protected void convert(BaseViewHolder helper, final OverseaComingMovieBean.DataBean.ComingBean item) {

        String imgUrl = ImgSizeUtil.resetPicUrl(item.getImg(), ".webp@171w_240h_1e_1c_1l");
        GlideManager.loadImage(mContext, imgUrl, (ImageView) helper.getView(R.id.iv_movie_img));

        switch (helper.getItemViewType()) {
            case BaseConstant.TYPE_OVERSEA_NORMAL:
                helper.setText(R.id.tv_movie_name, item.getNm())
                        .setText(R.id.tv_movie_type, item.getCat())
                        .setText(R.id.tv_movie_desc, item.getStar())
                        .setText(R.id.tv_movie_wish, String.format("%s", item.getWish()));
                break;
            case BaseConstant.TYPE_OVERSEA_HEAD_LINE:
                helper.setText(R.id.tv_movie_name, item.getNm())
                        .setText(R.id.tv_movie_desc, item.getDesc())
                        .setText(R.id.tv_movie_star, item.getStar())
                        .setText(R.id.tv_movie_score, String.format("%s", item.getSc()))
                        .setText(R.id.tv_hot_type1, item.getHeadLinesVO().get(0).getType())
                        .setText(R.id.tv_hot_type2, item.getHeadLinesVO().get(1).getType())
                        .setText(R.id.tv_hot_headline_title1, item.getHeadLinesVO().get(0).getTitle())
                        .setText(R.id.tv_hot_headline_title2, item.getHeadLinesVO().get(1).getTitle());
                break;
            case BaseConstant.TYPE_OVERSEA_PRESALE:
                helper.setText(R.id.tv_movie_name, item.getNm())
                        .setText(R.id.tv_movie_type, item.getCat())
                        .setText(R.id.tv_movie_desc, item.getDesc())
                        .setText(R.id.tv_movie_wish, String.format("%s", item.getWish()));
                break;
        }

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieDetailActivity.invoke(mContext, item.getId());
            }
        });
    }
}
