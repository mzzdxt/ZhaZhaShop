package com.coderwjq.shop.module.movie_star.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coderwjq.shop.R;
import com.coderwjq.shop.utils.GlideManager;
import com.coderwjq.shop.utils.ImgSizeUtil;

public class StarPhotosAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public StarPhotosAdapter() {
        super(R.layout.item_star_photos, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        String imgUrl = ImgSizeUtil.resetPicUrl(item, "");
        GlideManager.loadImage(mContext, imgUrl, (ImageView) helper.getView(R.id.iv_star_photo));
    }
}
