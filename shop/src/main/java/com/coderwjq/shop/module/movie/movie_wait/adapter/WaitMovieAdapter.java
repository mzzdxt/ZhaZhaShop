package com.coderwjq.shop.module.movie.movie_wait.adapter;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coderwjq.shop.R;
import com.coderwjq.shop.module.movie.movie_wait.bean.WaitMovieBean;
import com.coderwjq.shop.module.movie_detail.MovieDetailActivity;
import com.coderwjq.shop.utils.GlideManager;
import com.coderwjq.shop.utils.ToastUtil;

/**
 * @Created by coderwjq on 2017/5/25 16:01.
 * @Desc
 */

public class WaitMovieAdapter extends BaseQuickAdapter<WaitMovieBean.DataBean.ComingBean, BaseViewHolder> {

    public WaitMovieAdapter() {
        super(R.layout.item_wait_movie, null);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final WaitMovieBean.DataBean.ComingBean item) {
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.showShort(mContext, helper.getAdapterPosition() + "");
            }
        });

        // 图片地址不能直接使用，需要进行转换
        String originUrl = item.getImg();
        String imgUrl = originUrl.replace("/w.h/", "/") + "@171w_240h_1e_1c_1l";//后缀为图片大小
        GlideManager.loadImage(mContext, imgUrl, (ImageView) helper.getView(R.id.iv_wait_movie));

        helper.setText(R.id.tv_wait_movie_name, item.getNm())
                .setText(R.id.tv_wait_movie_desc, item.getScm())
                .setText(R.id.tv_wait_movie_wish, String.format("%s人想看", item.getWish()))
                .setText(R.id.tv_wait_movie_major, String.format("主演:%s", item.getStar()));

        TextView tv_wish = helper.getView(R.id.tv_wait_movie_wish);
        Spannable spannable = new SpannableString(tv_wish.getText());
        spannable.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.text_yellow, null)), 0, tv_wish.getText().toString().indexOf("人想看"), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_wish.setText(spannable);

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieDetailActivity.invoke(mContext, item.getId());
            }
        });
    }
}
