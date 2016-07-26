package com.sgd.zitai.retrofit;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Allen Liu on 2016/5/27.
 * 将一些同样的操作封装到一起
 */
public class ThreadTransformer<S>  implements Observable.Transformer<S,S> {
    private  ProgressBar pb;
    public ThreadTransformer(ProgressBar pb) {
        this.pb=pb;

    }

    @Override
    public Observable<S> call(Observable<S> tObservable) {
        return tObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(AndroidSchedulers.mainThread()).onErrorReturn(new Func1<Throwable, S>() {
            @Override
            public S call(Throwable throwable) {
                pb.setVisibility(View.GONE);
                Toast.makeText(pb.getContext(),throwable.getMessage(),Toast.LENGTH_SHORT).show();
                return null;
            }
        }).doOnSubscribe(() -> {
            pb.setVisibility(View.VISIBLE);
        }).subscribeOn(AndroidSchedulers.mainThread());
    }
}
