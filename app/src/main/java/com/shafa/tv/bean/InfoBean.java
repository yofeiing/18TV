package com.shafa.tv.bean;

import java.util.List;

/**
 * Created by fuyifang on 2016/7/30.
 */
public class InfoBean {


    private List<RecommendBean> recommend;


    private List<TvLiveBean> tvLive;


    private List<MovieBean> movie;

    public List<RecommendBean> getRecommend() {
        return recommend;
    }

    public void setRecommend(List<RecommendBean> recommend) {
        this.recommend = recommend;
    }

    public List<TvLiveBean> getTvLive() {
        return tvLive;
    }

    public void setTvLive(List<TvLiveBean> tvLive) {
        this.tvLive = tvLive;
    }

    public List<MovieBean> getMovie() {
        return movie;
    }

    public void setMovie(List<MovieBean> movie) {
        this.movie = movie;
    }

    public static class RecommendBean {
        private int id;
        private String className;
        private String name;
        private String details;
        private String url;
        private Object ico;
        private Object image;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Object getIco() {
            return ico;
        }

        public void setIco(Object ico) {
            this.ico = ico;
        }

        public Object getImage() {
            return image;
        }

        public void setImage(Object image) {
            this.image = image;
        }
    }

    public static class TvLiveBean {
        private int id;
        private String className;
        private String ico;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getIco() {
            return ico;
        }

        public void setIco(String ico) {
            this.ico = ico;
        }
    }

    public static class MovieBean {
        private int id;
        private String className;
        private String ico;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getIco() {
            return ico;
        }

        public void setIco(String ico) {
            this.ico = ico;
        }
    }
}
