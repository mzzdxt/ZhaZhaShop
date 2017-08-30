package com.coderwjq.shop.module.movie.movie_find.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coderwjq.shop.R;
import com.coderwjq.shop.module.movie.movie_find.model.MovieTypeBean;
import com.coderwjq.shop.utils.ToastUtil;

/**
 * Created by coderwjq on 2017/8/29 11:54.
 */

public class FindMoviePeriodAdapter extends BaseQuickAdapter<MovieTypeBean.DataBean.TagListBean, BaseViewHolder> {
    public FindMoviePeriodAdapter() {
        super(R.layout.item_movie_type, null);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final MovieTypeBean.DataBean.TagListBean item) {
        helper.setText(R.id.tv_movie_type, item.getTagName());

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 根据类别打开分类查找界面
                ToastUtil.showShort(mContext, item.getTagName() + ":" + item.getTagId());
            }
        });
    }
}
