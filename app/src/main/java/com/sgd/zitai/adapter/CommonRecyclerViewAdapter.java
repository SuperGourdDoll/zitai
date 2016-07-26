package com.sgd.zitai.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sgd.zitai.adapter.CommonHolder;

import java.util.List;

/**
 * Created by alex on 2016/4/12.
 */
public abstract class CommonRecyclerViewAdapter<T> extends RecyclerView.Adapter<CommonHolder> {
    private List<T> data;
    private LayoutInflater inflater;
    private Context context;
    private int layoutId;

    public CommonRecyclerViewAdapter(Context context, int res, List<T> list) {
        this.context = context;
        layoutId = res;
        data = list;
        if (context != null)
            inflater = LayoutInflater.from(context);

    }

    @Override
    public CommonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(layoutId, parent, false);
        CommonHolder holder = new CommonHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CommonHolder holder, int position) {
        convertViewHolder(holder, position, data.get(position));
    }

    /**
     *
     * @param holder 缓存类 注意初始化控件
     * @param position
     * @param t
     */
    public abstract void convertViewHolder(CommonHolder holder, int position, T t);

    @Override
    public int getItemCount() {
        return data.size();
    }
}