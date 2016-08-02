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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sgd.zitai.R;
import com.sgd.zitai.adapter.VideoListAdapter;
import com.sgd.zitai.bean.VideoListBean;
import com.sgd.zitai.ui.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Allen Liu on 2016/8/2.
 */
public class VideoFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.OnRecyclerViewItemClickListener{
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    private VideoListAdapter adapter;
    private ArrayList<VideoListBean>data;

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
        swipeRefresh.setColorSchemeResources(R.color.main_theme_color);
        swipeRefresh.setOnRefreshListener(this);
        swipeRefresh.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        data=new ArrayList<>();
        adapter=new VideoListAdapter(R.layout.layout_videolist_item,data);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        for (int i = 0; i < 40; i++) {
            data.add(new VideoListBean());
        }
        recyclerview.setAdapter(adapter);
        //点击事件
        adapter.setOnRecyclerViewItemClickListener(this);
        //设置动画
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);


    }

    @Override
    public void onRefresh() {
        data.clear();
        for (int i = 0; i < 40; i++) {
            data.add(new VideoListBean());
        }
     //   adapter.notifyItemInserted(0);
        swipeRefresh.setRefreshing(false);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View view, int i) {
         T("click");
    }
}
