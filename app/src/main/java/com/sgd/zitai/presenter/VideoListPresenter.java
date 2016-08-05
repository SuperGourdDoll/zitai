package com.sgd.zitai.presenter;

import android.util.Log;

import com.sgd.zitai.bean.VideoListBean;
import com.sgd.zitai.contract.VideoListContract;
import com.sgd.zitai.retrofit.MyRetrofit;
import com.sgd.zitai.retrofit.ThreadTransformer;
import com.sgd.zitai.retrofit.service.ApiService;

import java.util.ArrayList;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Allen Liu on 2016/8/4.
 */
public class VideoListPresenter implements VideoListContract.Presenter {
    VideoListContract.View view;

    public VideoListPresenter(VideoListContract.View view) {
        this.view = view;
        view.bindPresenter(this);
    }

    @Override
    public synchronized void loadData() {
        synchronized (this) {
            int page = view.getCurrentPage();
            MyRetrofit.getInstance()
                    .create(ApiService.class)
                    .getVideoList(page)
                    .compose(new ThreadTransformer<>())
                    .filter(videoListBean1 -> (videoListBean1 != null && videoListBean1.getCode() == 0))
                    .doOnSubscribe(() -> {
                        view.getLoadingView().setRefreshing(true);
                    })
                    .doOnTerminate(() -> {
                        view.getLoadingView().setRefreshing(false);
                    })
                    .subscribe(videoListBean -> {
                        view.refreshData((ArrayList<VideoListBean.DataBean>) videoListBean.getData());
                    });
        }
    }

    @Override
    public void initialize() {

    }
}
