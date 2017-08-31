package com.coderwjq.shop.module.movie.movie_hot;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coderwjq.shop.R;
import com.coderwjq.shop.base.BaseConstant;
import com.coderwjq.shop.module.movieDetail.MovieDetailActivity;
import com.coderwjq.shop.utils.GlideManager;
import com.coderwjq.shop.utils.ImgSizeUtil;
import com.litesuits.common.assist.Toastor;

/**
 * @Created by coderwjq on 2017/5/26 10:16.
 * @Desc
 */

public class HotMovieListAdapter extends BaseMultiItemQuickAdapter<HotMovieBean.DataBean.HotBean, BaseViewHolder> {
    private static final String TAG = "HotMovieListAdapter";
    private final int mHighLightColor;
    private final Toastor mToastor;
    private final int mHighLightColorDirector;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     */
    public HotMovieListAdapter(Context context) {
        super(null);
        addItemType(BaseConstant.TYPE_HOT_HEADLINE, R.layout.item_hot_headline);
        addItemType(BaseConstant.TYPE_HOT_NORMAL, R.layout.item_hot_normal);
        mContext = context;
        mHighLightColor = mContext.getResources().getColor(R.color.text_yellow, null);
        mHighLightColorDirector = mContext.getResources().getColor(R.color.colorAccent, null);
        mToastor = new Toastor(mContext);
    }

    @Override
    protected void convert(BaseViewHolder helper, final HotMovieBean.DataBean.HotBean item) {
        // 设置文字内容
        helper.setText(R.id.tv_hot_movie_name, String.format("%s", item.getNm()))
                .setText(R.id.tv_hot_desc, String.format("%s", item.getScm()))
                .setText(R.id.tv_hot_showInfo, String.format("%s", item.getShowInfo()));

        // 设置图片内容
        String imgUrl = ImgSizeUtil.resetPicUrl(item.getImg(), ".webp@171w_240h_1e_1c_1l");
        GlideManager.loadImage(mContext, imgUrl, (ImageView) helper.getView(R.id.iv_hot_img));

        // 设置电影类型：3D/IMAX
        // "ver": "2D/3D/IMAX 3D/中国巨幕/全景声",
        if (item.getVer().contains("IMAX 3D")) {
            helper.setImageResource(R.id.iv_ver, R.drawable.ic_3d_imax);
        } else if (item.getVer().contains("3D")) {
            helper.setImageResource(R.id.iv_ver, R.drawable.ic_3d);
        }

        /**
         * 0-热映电影显示评分
         * 1-预售电影显示想看人数
         */
        switch (item.getPreSale()) {
            case 0:
                if (item.getSc() == 0) {
                    helper.setText(R.id.tv_hot_audience, String.format("暂无评分"));
                } else {
                    helper.setText(R.id.tv_hot_audience, String.format("评分: %s", item.getSc()));
                }
                helper.setText(R.id.tv_buy_ticket, "购票")
                        .setTextColor(R.id.tv_buy_ticket, mContext.getResources().getColor(R.color.colorAccent, null))
                        .setBackgroundRes(R.id.tv_buy_ticket, R.drawable.bg_tv_buy);
                break;
            case 1:
                helper.setText(R.id.tv_hot_audience, String.format("%s人想看", item.getWish()))
                        .setText(R.id.tv_buy_ticket, "预售")
                        .setTextColor(R.id.tv_buy_ticket, mContext.getResources().getColor(R.color.text_blue, null))
                        .setBackgroundRes(R.id.tv_buy_ticket, R.drawable.bg_tv_presale);
                break;
        }

        // 使用富文本，高亮显示评分和想看人数
        TextView tvHotAudience = helper.getView(R.id.tv_hot_audience);
        String content = tvHotAudience.getText().toString();
        SpannableString span = new SpannableString(content);
        if (content.contains("评分: ")) {
            span.setSpan(new ForegroundColorSpan(mHighLightColor), 4, content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else if (content.contains("人想看")) {
            span.setSpan(new ForegroundColorSpan(mHighLightColor), 0, content.indexOf("人"), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        tvHotAudience.setText(span);

        // 根据item类型显示不同的条目
        switch (helper.getItemViewType()) {
            case BaseConstant.TYPE_HOT_HEADLINE:
                helper.setText(R.id.tv_hot_type1, String.format("%s", item.getHeadLinesVO().get(0).getType()))
                        .setText(R.id.tv_hot_type2, String.format("%s", item.getHeadLinesVO().get(1).getType()))
                        .setText(R.id.tv_hot_headline_title1, String.format("%s", item.getHeadLinesVO().get(0).getTitle()))
                        .setText(R.id.tv_hot_headline_title2, String.format("%s", item.getHeadLinesVO().get(1).getTitle()));

                helper.getView(R.id.tv_hot_headline_title1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mToastor.showSingletonToast(item.getHeadLinesVO().get(0).getUrl());
                    }
                });
                helper.getView(R.id.tv_hot_headline_title2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mToastor.showSingletonToast(item.getHeadLinesVO().get(1).getUrl());
                    }
                });
                helper.setText(R.id.tv_hot_director, "导演: 尼古拉斯·喳喳");
                break;
            case BaseConstant.TYPE_HOT_NORMAL:
                if (item.getNm().contains("摔跤吧")) {
                    helper.setText(R.id.tv_hot_director, "编剧: 尼古拉斯·雪晴");
                    TextView tvDirector = helper.getView(R.id.tv_hot_director);
                    String strDirector = tvDirector.getText().toString();
                    SpannableString spannableString = new SpannableString(strDirector);
                    spannableString.setSpan(new ForegroundColorSpan(mHighLightColorDirector), 3, spannableString.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
                    tvDirector.setText(spannableString);
                } else {
                    helper.setText(R.id.tv_hot_director, "");
                }
                break;
        }

        helper.getView(R.id.fl_hot_movie)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mToastor.showSingletonToast(item.getVideoName());
                    }
                });

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieDetailActivity.invoke(mContext, item.getId());
            }
        });
    }
}
