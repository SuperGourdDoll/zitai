package com.sgd.zitai.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sgd.zitai.R;
import com.sgd.zitai.adapter.TextListAdapter;
import com.sgd.zitai.bean.TextEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TextFragment extends Fragment implements TextListAdapter.OnItemClickListener {


    private List <TextEntity> mDatas;
/*    @BindView(R.id.fragment_text_multistatus)
    MultipleStatusView mMultiStatus;*/
    @BindView(R.id.fragment_text_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.fragment_text_swipe)
    SwipeRefreshLayout mSwipeRefresh;
    private TextListAdapter mAdapter;
    private LinearLayoutManager mManager;
    boolean isLoading;
    private Handler handler = new Handler();


    public TextFragment() {

    }

    public static TextFragment newInstance() {
        Bundle args = new Bundle();
        TextFragment fragment = new TextFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mDatas = new ArrayList<>();
        generateData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //mSwipeRefresh.setProgressBackgroundColorSchemeResource(R.color.colorPrimary);
        View view = inflater.inflate(R.layout.fragment_text, container, false);
        ButterKnife.bind(this,view);
        mSwipeRefresh.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mAdapter = new TextListAdapter(mDatas,getActivity());
        mAdapter.setOnItemClickListener(this);
        mManager = new LinearLayoutManager(getActivity());
        mManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mManager);
        mRecyclerView.setAdapter(mAdapter);
        initListener();
        //mMultiStatus.showLoading();
        return view;
    }

    /*  初始化监听事件  */
    private void initListener() {
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                //tv.setText("正在刷新");
                // TODO Auto-generated method stub
                mSwipeRefresh.setRefreshing(true);
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        //tv.setText("刷新完成");
                        mSwipeRefresh.setRefreshing(false);
                    }
                }, 3000);
            }
        });
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisiablePosition = mManager.findLastVisibleItemPosition();
                if (lastVisiablePosition + 1 == mAdapter.getItemCount()) {
                    Log.d("test", "loading executed");
                    boolean isRefreshing = mSwipeRefresh.isRefreshing();
                    if (isRefreshing) {
                        mAdapter.notifyItemRemoved(mAdapter.getItemCount());
                        return;
                    }
                    if (!isLoading) {
                        isLoading = true;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                getData();
                                Log.d("test", "load more completed");
                                isLoading = false;
                            }
                        }, 1000);
                    }
                }
            }
        });
    }

    public void generateData(){
        for (int i = 0; i < 15; i++) {
            if(mDatas == null){
                mDatas = new ArrayList<>();
            }
            mDatas.add(new TextEntity());
        }
    }

    /**
     * 获取测试数据
     */
    private void getData() {
        for (int i = 0; i < 6; i++) {
            if(mDatas == null){
                mDatas = new ArrayList<>();
            }
            mDatas.add(new TextEntity());
        }
        mAdapter.notifyDataSetChanged();
        mSwipeRefresh.setRefreshing(false);
        mAdapter.notifyItemRemoved(mAdapter.getItemCount());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onItemClick(View view, int position) {
        Snackbar.make(mRecyclerView,"itemclick",Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onUserHeadClick(View view, int position) {
        Snackbar.make(mRecyclerView,"userHeadClick",Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(View view, int position) {
        Snackbar.make(mRecyclerView,"itemlongclick",Snackbar.LENGTH_SHORT).show();
    }
}
