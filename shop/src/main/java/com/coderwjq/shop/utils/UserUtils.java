package com.coderwjq.shop.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.coderwjq.shop.R;


/**
 * Created by Administrator on 2017/2/17.
 */

public class UserUtils {

    public static Drawable getUserLevelLable(Context context, int level) {
        Drawable icon = null;
        switch (level) {
            case 1:
                icon = context.getResources().getDrawable(R.drawable.ic_lv1, null);
                break;
            case 2:
                icon = context.getResources().getDrawable(R.drawable.ic_lv2, null);
                break;
            case 3:
                icon = context.getResources().getDrawable(R.drawable.ic_lv3, null);
                break;
            case 4:
                icon = context.getResources().getDrawable(R.drawable.ic_lv4, null);
                break;
            case 5:
                icon = context.getResources().getDrawable(R.drawable.ic_lv5, null);
                break;
            default:
                icon = context.getResources().getDrawable(R.drawable.icon_default, null);
                break;
        }
        return icon;
    }
}
