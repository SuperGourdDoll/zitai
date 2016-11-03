package com.sgd.zitai.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.orhanobut.logger.Logger;
import com.sgd.zitai.R;
import com.sgd.zitai.bean.VideoListBean;
import com.sgd.zitai.callback.IFlayerFinishListener;
import com.sgd.zitai.constants.ConstantCode;
import com.sgd.zitai.ui.widget.VideoPlayerView;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCMediaPlayerListener;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerManager;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerSimple;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by Allen Liu on 2016/8/2.
 */
public class VideoListAdapter extends BaseQuickAdapter<VideoListBean.DataBean> {
    Context context;

    public VideoListAdapter(Context context, int layoutResId, List<VideoListBean.DataBean> data) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, VideoListBean.DataBean videoListBean) {
        Logger.e("position:"+baseViewHolder.getLayoutPosition()+"\nid:"+videoListBean.getId()+"");
        TextView tvAuthor = baseViewHolder.getView(R.id.tv_author);
        VideoPlayerView player = baseViewHolder.getView(R.id.player);
        tvAuthor.setText(videoListBean.getAuthor());
        player.setUp(videoListBean.getContent(), JCVideoPlayer.SCREEN_LAYOUT_LIST);
        player.setOnFlayerFinsh(new IFlayerFinishListener() {
            @Override
            public void onFinish() {
                tvAuthor.setVisibility(View.VISIBLE);
            }

            @Override
            public void onStart() {
                tvAuthor.setVisibility(View.GONE);
            }
        });
        Glide.with(context).load(videoListBean.getVideocover()).centerCrop().placeholder(R.mipmap.ceshi).crossFade().into(player.thumbImageView);
    }
}
