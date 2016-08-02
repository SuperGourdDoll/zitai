package com.sgd.zitai.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sgd.zitai.bean.VideoListBean;

import java.util.List;

/**
 * Created by Allen Liu on 2016/8/2.
 */
public class VideoListAdapter extends BaseQuickAdapter<VideoListBean> {

    public VideoListAdapter(int layoutResId, List<VideoListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, VideoListBean videoListBean) {

    }
}
