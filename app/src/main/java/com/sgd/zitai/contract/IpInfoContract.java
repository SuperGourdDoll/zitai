package com.sgd.zitai.contract;

import android.widget.ProgressBar;


import com.sgd.zitai.bean.IpBean;
import com.sgd.zitai.contract.BaseView;
import com.sgd.zitai.presenter.BasePresenter;

/**
 * Created by Allen Liu on 2016/7/4.
 */
public interface IpInfoContract  {
    interface Presenter extends BasePresenter {
       void loadData();
    }
    interface  View extends BaseView<Presenter> {
        ProgressBar getLoadingView();
        void dimissLoading();
        void refreshIpInfo(IpBean ipBean);
        String getIpNum();
    }
}
