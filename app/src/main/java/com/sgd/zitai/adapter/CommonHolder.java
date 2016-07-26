package com.sgd.zitai.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by AlexLiu on 2016/4/12.
 */
public class CommonHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> views;
    private View view;

    /**
     * 根据ID获取控件
     *
     * @param res
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int res) {
        View v = views.get(res);
        if (v == null) {
            v = view.findViewById(res);
            views.put(res, v);
        }
        return (T) v;
    }

    public CommonHolder(View itemView) {
        super(itemView);
        view = itemView;
        views=new SparseArray<>();
    }
}