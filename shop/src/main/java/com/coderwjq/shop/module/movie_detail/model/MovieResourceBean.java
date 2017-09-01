package com.coderwjq.shop.module.movie_detail.model;

import java.util.List;

/**
 * Created by coderwjq on 2017/8/31 8:49.
 *
 * @desc 电影资料数据
 */

public class MovieResourceBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * content : 甄子丹扮演“Xiang”，此前这个角色原本是为李连杰设计的，甄子丹取代了后者出演这个反派角色。
         * img : http://p0.meituan.net/mmdb/620a7e33958c1a0dcdf5f068d2d278fc1286.png
         * name : highlights
         * title : 幕后花絮
         */

        private String content;
        private String img;
        private String name;
        private String title;
        private String url;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
