package com.sgd.zitai.retrofit.service;
import com.sgd.zitai.bean.IpBean;
import com.sgd.zitai.bean.VideoListBean;

import java.util.Map;

import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.http.QueryMap;
import rx.Observable;

/**
 * Created by maomao on 2016/4/12.
 */
public interface    ApiService {
    @GET("videos/vlist")
Observable<VideoListBean>getVideoList(@Query("page")int page);
}
