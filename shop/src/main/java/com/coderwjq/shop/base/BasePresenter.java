package com.coderwjq.shop.base;

import android.app.Activity;

import com.litesuits.common.assist.Toastor;

/**
 * @Created by coderwjq on 2017/5/26 9:34.
 * @Desc
 */

public class BasePresenter<T> implements IPresenter {
    private final Toastor mToastor;
    protected Activity mActivity;
    protected T mView;

    public BasePresenter(Activity activity, T view) {
        mActivity = activity;
        mView = view;
        mToastor = new Toastor(activity);
    }

    @Override
    public void detachView() {
        mView = null;
    }

    protected void showToast(String msg) {
        mToastor.showSingletonToast(msg);
    }
}
