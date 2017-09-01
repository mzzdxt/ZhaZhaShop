package com.coderwjq.shop.module.player.rxbus;

/**
 * Created by Administrator on 2017/2/17.
 */

public class CommentPostBean {
    private int videoId;

    public CommentPostBean(int videoId) {
        this.videoId = videoId;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }
}
