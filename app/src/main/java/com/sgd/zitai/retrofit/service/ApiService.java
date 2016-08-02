package com.sgd.zitai.retrofit.service;
import com.sgd.zitai.bean.IpBean;

import java.util.Map;

import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.http.QueryMap;
import rx.Observable;

/**
 * Created by maomao on 2016/4/12.
 */
public interface    ApiService {
    @GET("/service/getIpInfo.php")
    Observable<IpBean>getIp(@Query("ip")String ip);

    Observable<IpBean>getIp(@QueryMap Map<String,String> map);
}
