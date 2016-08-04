package com.sgd.zitai.presenter;


import com.sgd.zitai.bean.IpBean;
import com.sgd.zitai.contract.IpInfoContract;
import com.sgd.zitai.retrofit.MyRetrofit;
import com.sgd.zitai.retrofit.ThreadTransformer;
import com.sgd.zitai.retrofit.service.ApiService;

/**
 * Created by Allen Liu on 2016/7/4.
 */
public class IpPresenter implements IpInfoContract.Presenter {
    IpInfoContract.View view;
    public IpPresenter(IpInfoContract.View view) {
        this.view=view;
        view.bindPresenter(this);
    }

    @Override
    public void loadData() {
        //这里可以封装成一个model，这里只关注回调就可以了
//        MyRetrofit.getInstance().create(ApiService.class).getIp(view.getIpNum()).compose(new ThreadTransformer<>(view.getLoadingView()))
//                .filter((IpBean bean) -> bean != null&&bean.getCode() == 0).subscribe((IpBean bean) -> {
//            view.dimissLoading();
//            view.refreshIpInfo(bean);
//        });
    }

    @Override
    public void initialize() {

    }
}
