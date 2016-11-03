package com.sgd.zitai.ui.fragment.see;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orhanobut.logger.Logger;
import com.sgd.zitai.R;
import com.sgd.zitai.adapter.VideoListAdapter;
import com.sgd.zitai.bean.VideoListBean;
import com.sgd.zitai.contract.VideoListContract;
import com.sgd.zitai.presenter.VideoListPresenter;
import com.sgd.zitai.ui.BaseFragment;
import com.sgd.zitai.ui.widget.AutoLoadRecyclerView;
import com.sgd.zitai.utils.Rx.RxUtils;

import java.util.ArrayList;


import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerManager;
import rx.Observable;
import rx.Statement;
import rx.android.schedulers.AndroidSchedulers;

import static android.media.CamcorderProfile.get;


/**
 * Created by Allen Liu on 2016/8/2.
 */
public class VideoFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnRecyclerViewItemClickListener, VideoListContract.View {
    @BindView(R.id.recyclerview)
    AutoLoadRecyclerView recyclerview;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    private VideoListAdapter adapter;
    private ArrayList<VideoListBean.DataBean> data;
    private int page = 1;
    private VideoListContract.Presenter presenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, null);
        ButterKnife.bind(this, view);
        initialize();
        return view;
    }

    @Override
    protected void initialize() {
        //绑定presenter
        new VideoListPresenter(this);
        initRecyclerView();
    }

    private void initRecyclerView() {
        swipeRefresh.setColorSchemeResources(R.color.main_theme_color);
        swipeRefresh.setOnRefreshListener(this);
        data = new ArrayList<>();
        adapter = new VideoListAdapter(getActivity(), R.layout.layout_videolist_item, data);
        //点击事件
        adapter.setOnRecyclerViewItemClickListener(this);
        //设置动画
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setAdapter(adapter);
        recyclerview.setLoadMoreListener(() -> {
            //判断是否正在刷新
            Observable.just(swipeRefresh.isRefreshing())
                    .filter(aBoolean1 -> {
                        return !aBoolean1;
                    })
                    .subscribe(aBoolean -> {
                        presenter.loadData();
                    });
        });
        recyclerview.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {

            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
//                if (JCVideoPlayerManager.getFirst() != null) {
//                    JCVideoPlayer videoPlayer = (JCVideoPlayer) JCVideoPlayerManager.getFirst();
//                    if (((ViewGroup) view).indexOfChild(videoPlayer) != -1 && videoPlayer.currentState == JCVideoPlayer.CURRENT_STATE_PLAYING) {
                        JCVideoPlayer.releaseAllVideos();
//                    }
//                }
            }
        });
        swipeRefresh.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        onRefresh();
    }

    /**
     * 刷新操作
     */
    @Override
    public void onRefresh() {
        page = 1;
        presenter.loadData();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onItemClick(View view, int i) {

    }

    /**
     * presenter刷新数据
     *
     * @param list
     */
    @Override
    public void refreshData(ArrayList<VideoListBean.DataBean> list) {
//        Observable<VideoListBean.DataBean>dataBeanObservable=Observable.from(list).compose(RxUtils.rxSchedulerHelper());
//        Observable empty=Observable.empty();
//        Statement.ifThen(()->{return list!=null&&list.size()>0;},dataBeanObservable,empty);
//        dataBeanObservable.subscribe(dataBean -> {
//            if (page == 1) {
//                data.clear();
//                data.addAll(list);
//                adapter.notifyDataSetChanged();
//            } else {
//                int position = data.size();
//                data.addAll(list);
//                adapter.notifyItemInserted(position);
//            }
//            page++;
//        });
//        empty.subscribe(o -> {},throwable -> {},() -> {
//            if (page == 1) {
//                S(getString(R.string.empty_data), recyclerview);
//                adapter.notifyDataSetChanged();
//            } else {
//                S(getString(R.string.no_more_data) + data.size(), recyclerview);
//            }
//        });


        if (list != null && list.size() > 0) {
            if (page == 1) {
                data.clear();
                data.addAll(list);
                adapter.notifyDataSetChanged();
            } else {
                int position = data.size();
                data.addAll(list);
                adapter.notifyItemInserted(position);
            }
            page++;
            //数据为空
        } else {
            if (page == 1) {
                S(getString(R.string.empty_data), recyclerview);
                data.clear();
                adapter.notifyDataSetChanged();
            } else {
                S(getString(R.string.no_more_data) + data.size(), recyclerview);
            }
        }

    }

    @Override
    public int getCurrentPage() {
        return page;
    }

    @Override
    public void bindPresenter(VideoListContract.Presenter preenter) {
        this.presenter = preenter;
    }

    @Override
    public SwipeRefreshLayout getLoadingView() {
        return swipeRefresh;
    }


}
