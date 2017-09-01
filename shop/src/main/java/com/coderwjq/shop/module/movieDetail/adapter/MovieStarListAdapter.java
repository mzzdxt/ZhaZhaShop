package com.coderwjq.shop.module.movieDetail.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coderwjq.shop.R;
import com.coderwjq.shop.module.movieDetail.model.MovieStarBean;
import com.coderwjq.shop.utils.GlideManager;
import com.coderwjq.shop.utils.ImgSizeUtil;
import com.coderwjq.shop.utils.ToastUtil;

/**
 * Created by coderwjq on 2017/8/31 9:08.
 */

public class MovieStarListAdapter extends BaseQuickAdapter<MovieStarBean.DataBean, BaseViewHolder> {

    public MovieStarListAdapter() {
        super(R.layout.item_movie_star, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, final MovieStarBean.DataBean item) {
        if (helper.getAdapterPosition() == 0) {
            helper.setText(R.id.tv_role, "导演");
        } else if (helper.getAdapterPosition() == 1) {
            helper.setText(R.id.tv_role, "演员");
        } else {
            helper.setText(R.id.tv_role, "");
        }
        helper.setText(R.id.tv_fake_name, item.getRoles());
        helper.setText(R.id.tv_real_name, item.getCnm());

        String imgUrl = ImgSizeUtil.resetPicUrl(item.getAvatar(), ".webp@210w_285h_1e_1c_1l");
        GlideManager.loadImage(mContext, imgUrl, (ImageView) helper.getView(R.id.iv_movie_star));
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showShort(mContext, item.getId() + "");
//                MovieStarActivity.start(mContext, item.getId());
            }
        });
    }
}
