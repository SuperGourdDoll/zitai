package com.sgd.zitai.bean;

import java.util.List;

/**
 * Created by Allen Liu on 2016/8/2.
 */
public class VideoListBean {


    /**
     * code : 0
     * msg : ok
     * data : [{"id":1,"title":"赶着音乐放牧","content":"/video/cover/dabing.f4v","likenum":0,"sharenum":0,"comments":"","uploadtime":1470151319,"videotime":"04:57","videoclass":"视频","videocover":"/video/cover/dabing.png","playtime":0},{"id":0,"title":"赶着音乐放牧","content":"/video/cover/dabing.f4v","likenum":0,"sharenum":0,"comments":"","uploadtime":1470151311,"videotime":"04:57","videoclass":"视频","videocover":"/video/cover/dabing.png","playtime":0}]
     */

    private int code;
    private String msg;
    /**
     * id : 1
     * title : 赶着音乐放牧
     * content : /video/cover/dabing.f4v
     * likenum : 0
     * sharenum : 0
     * comments :
     * uploadtime : 1470151319
     * videotime : 04:57
     * videoclass : 视频
     * videocover : /video/cover/dabing.png
     * playtime : 0
     */

    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String author;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        private int id;
        private String title;
        private String content;
        private int likenum;
        private int sharenum;
        private String comments;
        private int uploadtime;
        private String videotime;
        private String videoclass;
        private String videocover;
        private int playtime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getLikenum() {
            return likenum;
        }

        public void setLikenum(int likenum) {
            this.likenum = likenum;
        }

        public int getSharenum() {
            return sharenum;
        }

        public void setSharenum(int sharenum) {
            this.sharenum = sharenum;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public int getUploadtime() {
            return uploadtime;
        }

        public void setUploadtime(int uploadtime) {
            this.uploadtime = uploadtime;
        }

        public String getVideotime() {
            return videotime;
        }

        public void setVideotime(String videotime) {
            this.videotime = videotime;
        }

        public String getVideoclass() {
            return videoclass;
        }

        public void setVideoclass(String videoclass) {
            this.videoclass = videoclass;
        }

        public String getVideocover() {
            return videocover;
        }

        public void setVideocover(String videocover) {
            this.videocover = videocover;
        }

        public int getPlaytime() {
            return playtime;
        }

        public void setPlaytime(int playtime) {
            this.playtime = playtime;
        }
    }
}
