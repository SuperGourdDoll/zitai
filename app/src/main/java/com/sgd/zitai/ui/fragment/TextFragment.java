package com.sgd.zitai.ui.fragment;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sgd.zitai.R;
import com.sgd.zitai.bean.TextEntity;
import com.sgd.zitai.ui.widget.MultipleStatusView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TextFragment extends Fragment {


    private List <TextEntity> mDatas;
/*    @BindView(R.id.fragment_text_multistatus)
    MultipleStatusView mMultiStatus;*/
    @BindView(R.id.fragment_text_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.fragment_text_swipe)
    SwipeRefreshLayout mSwipeRefresh;


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
        RecyclerViewAdaprer adaprer = new RecyclerViewAdaprer();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adaprer);

        //mMultiStatus.showLoading();
        return view;
    }

    public void generateData(){
        for (int i = 0; i < 15; i++) {
            if(mDatas == null){
                mDatas = new ArrayList<>();
            }
            mDatas.add(new TextEntity());
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    private class RecyclerViewAdaprer extends RecyclerView.Adapter<ViewHolder>{

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text,parent,false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.itemText.setText(mDatas.get(position).content);
        }

        @Override
        public int getItemCount() {
            return mDatas != null ? mDatas.size():0;
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder{

        TextView itemText;
        public ViewHolder(View itemView) {
            super(itemView);
            itemText = (TextView) itemView.findViewById(R.id.item_text_text);
        }
    }

}
