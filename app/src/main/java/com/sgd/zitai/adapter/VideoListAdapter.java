package com.sgd.zitai.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sgd.zitai.R;
import com.sgd.zitai.bean.VideoListBean;
import com.sgd.zitai.constants.ConstantCode;

import java.util.List;

/**
 * Created by Allen Liu on 2016/8/2.
 */
public class VideoListAdapter extends BaseQuickAdapter<VideoListBean.DataBean> {
Context context;
    public VideoListAdapter(Context context, int layoutResId, List<VideoListBean.DataBean> data) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, VideoListBean.DataBean videoListBean) {
        baseViewHolder.setText(R.id.tv_author,videoListBean.getAuthor());
        ImageView imageView=baseViewHolder.getView(R.id.iv_img);
        Glide.with(context).load(videoListBean.getVideocover()).centerCrop().placeholder(R.mipmap.ceshi).crossFade().into(imageView);
    }
}
