package com.sgd.zitai.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.sgd.zitai.R;
import com.sgd.zitai.callback.IFlayerFinishListener;


import fm.jiecao.jcvideoplayer_lib.JCMediaManager;
import fm.jiecao.jcvideoplayer_lib.JCUtils;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerManager;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

import static java.security.AccessController.getContext;

/**
 * Created by Allen Liu on 2016/11/3.
 */

public class VideoPlayerView extends JCVideoPlayerStandard {
    public ImageView thumbImageView;

    public VideoPlayerView(Context context) {
        super(context);
    }

    public VideoPlayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

   // @Override
   // public int getLayoutId() {
    //    return R.layout.video_layout;
    //}

    @Override
    public void init(Context context) {
        super.init(context);
        thumbImageView = (ImageView) findViewById(R.id.thumb);
    }

//    @Override
//    public boolean setUp(String url, int screen, Object... objects) {
//        if (super.setUp(url, screen, objects)) {
//            if (currentScreen == SCREEN_WINDOW_FULLSCREEN) {
//                fullscreenButton.setImageResource(fm.jiecao.jcvideoplayer_lib.R.drawable.jc_shrink);
//            } else {
//                fullscreenButton.setImageResource(fm.jiecao.jcvideoplayer_lib.R.drawable.jc_enlarge);
//            }
//            return true;
//        }
//        return false;
//    }

    @Override
    public void setUiWitStateAndScreen(int state) {
        super.setUiWitStateAndScreen(state);
        switch (currentState) {
            case CURRENT_STATE_NORMAL:
//                startButton.setVisibility(View.VISIBLE);
//                thumbImageView.setVisibility(View.VISIBLE);
//                bottomContainer.setVisibility(View.GONE);

                break;
            case CURRENT_STATE_PREPARING:
//                startButton.setVisibility(View.INVISIBLE);
//                thumbImageView.setVisibility(View.GONE);
//                bottomContainer.setVisibility(View.VISIBLE);
                if (listener != null)
                    listener.onStart();
                break;
            case CURRENT_STATE_PLAYING:
//                startButton.setVisibility(View.VISIBLE);
//                thumbImageView.setVisibility(View.GONE);
//                bottomContainer.setVisibility(View.VISIBLE);
                break;
            case CURRENT_STATE_PAUSE:
                break;
            case CURRENT_STATE_ERROR:
                break;
            case CURRENT_STATE_AUTO_COMPLETE:
//                startButton.setVisibility(View.VISIBLE);
//                thumbImageView.setVisibility(View.VISIBLE);
//                bottomContainer.setVisibility(View.GONE);
                setAllControlsVisible(View.INVISIBLE, View.INVISIBLE, View.VISIBLE,
                        View.INVISIBLE, View.VISIBLE, View.INVISIBLE, View.INVISIBLE,
                        View.INVISIBLE);
                onClickUiToggle();
                if (listener != null)
                    listener.onFinish();
                break;
        }
      //  updateStartImages();
    }

    private void updateStartImages() {
        if (currentState == CURRENT_STATE_PLAYING) {
            startButton.setImageResource(fm.jiecao.jcvideoplayer_lib.R.drawable.jc_click_pause_selector);
//            delayHideBtn();
        } else if (currentState == CURRENT_STATE_ERROR) {
            startButton.setImageResource(fm.jiecao.jcvideoplayer_lib.R.drawable.jc_click_error_selector);
        } else {
            startButton.setImageResource(fm.jiecao.jcvideoplayer_lib.R.drawable.jc_click_play_selector);
        }

    }

//    private void delayHideBtn() {
//        postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startButton.setVisibility(View.GONE);
//                bottomContainer.setVisibility(View.GONE);
//            }
//        }, 2000);
//
//    }
//
//    @Override
//    public void onClick(View v) {
//        if (v.getId() == fm.jiecao.jcvideoplayer_lib.R.id.fullscreen && currentState == CURRENT_STATE_NORMAL) {
//            Toast.makeText(getContext(), "Play video first", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        super.onClick(v);
//        if (v.getId() == R.id.surface_container&&currentState!=CURRENT_STATE_NORMAL) {
//            startButton.setVisibility(View.VISIBLE);
//            bottomContainer.setVisibility(View.VISIBLE);
//            delayHideBtn();
//        }
//    }
//
//    @Override
//    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//        if (fromUser) {
//            if (currentState == CURRENT_STATE_NORMAL) {
//                Toast.makeText(getContext(), "Play video first", Toast.LENGTH_SHORT).show();
//                return;
//            }
//        }
//        super.onProgressChanged(seekBar, progress, fromUser);
//    }
//
//    @Override
//    public boolean backToOtherListener() {
//        return false;
//    }
//
//    @Override
//    public void onPrepared() {
//        super.onPrepared();
//
//    }

    @Override
    public void onCompletion() {
        super.onCompletion();
        if (listener != null)
            listener.onFinish();
    }


    IFlayerFinishListener listener;

    public void setOnFlayerFinsh(IFlayerFinishListener listener) {
        this.listener = listener;
    }
}
