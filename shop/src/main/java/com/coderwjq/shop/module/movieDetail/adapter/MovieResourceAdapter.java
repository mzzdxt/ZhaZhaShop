package com.coderwjq.shop.module.movieDetail.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coderwjq.shop.R;
import com.coderwjq.shop.module.movieDetail.model.MovieResourceBean;
import com.coderwjq.shop.utils.GlideManager;
import com.coderwjq.shop.utils.ToastUtil;

/**
 * Created by coderwjq on 2017/9/1 9:10.
 */

public class MovieResourceAdapter extends BaseQuickAdapter<MovieResourceBean.DataBean, BaseViewHolder> {

    private IMovieResourceClickListener movieResourceClickListener;

    public MovieResourceAdapter() {
        super(R.layout.item_movie_resource, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, final MovieResourceBean.DataBean item) {
        helper.setText(R.id.tv_movie_resource_title, item.getTitle())
                .setText(R.id.tv_movie_resource_content, item.getContent());

        GlideManager.loadImage(mContext, item.getImg(), (ImageView) helper.getView(R.id.iv_movie_resource));

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (movieResourceClickListener != null) {
                    if (item.getUrl() == null) {
                        movieResourceClickListener.onClick(item.getName());
                    } else {
                        String url = item.getUrl();
                        String id = url.substring(url.indexOf("id=") + 3, url.indexOf("&"));
                        String realUrl = "http://m.maoyan.com/information/" + id + "?_v_=yes";
                        ToastUtil.showShort(mContext, realUrl);
//                        BaseWebViewActivity.start(mContext, realUrl);
                    }
                }
            }
        });
    }

    public void setMovieResourceClickListener(IMovieResourceClickListener movieResourceClickListener) {
        this.movieResourceClickListener = movieResourceClickListener;
    }

    public interface IMovieResourceClickListener {
        void onClick(String type);
    }
}
