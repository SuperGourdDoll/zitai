package com.sgd.zitai.retrofit;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.sgd.zitai.ui.ZiTaiApplication;
import com.sgd.zitai.utils.ToastUtils;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Allen Liu on 2016/5/27.
 * 将一些同样的操作封装到一起
 */
public class ThreadTransformer<S> implements Observable.Transformer<S, S> {
    Object refreshview;

    public ThreadTransformer(Object refreshview) {
        this.refreshview = refreshview;
    }

    @Override
    public Observable<S> call(Observable<S> tObservable) {
        //封装公用的线程操作和错误处理
        return tObservable.subscribeOn(Schedulers.io())
                //指定线程
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(throwable -> {
                    //这里可以多加else判断 用于错误的时候加载匡处理
                    if (refreshview instanceof SwipeRefreshLayout) {
                        ((SwipeRefreshLayout) refreshview).setRefreshing(false);
                    }
                    //直接弹toast
                    if (ZiTaiApplication.context != null)
                        ToastUtils.showToast(ZiTaiApplication.context, throwable.getMessage());
                    return null;
                });
    }
}
