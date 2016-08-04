package com.sgd.zitai.contract;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ProgressBar;

import com.sgd.zitai.bean.IpBean;
import com.sgd.zitai.bean.VideoListBean;
import com.sgd.zitai.presenter.BasePresenter;

import java.util.ArrayList;

/**
 * Created by Allen Liu on 2016/8/4.
 */
public class VideoListContract {
    public interface Presenter extends BasePresenter {
        void  loadData();
    }
    public interface  View extends BaseView<Presenter> {
        SwipeRefreshLayout getLoadingView();
        void dimissLoading();
        void refreshData(ArrayList<VideoListBean.DataBean> list);
       int getCurrentPage();
    }
}
