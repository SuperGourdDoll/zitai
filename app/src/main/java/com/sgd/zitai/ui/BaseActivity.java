package com.sgd.zitai.ui;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sgd.zitai.utils.ToastUtils;

import butterknife.ButterKnife;


/**
 * Created by Allen Liu on 2016/6/2.
 */
public abstract class BaseActivity extends AppCompatActivity {
    public Context that = this;

    protected abstract void initialize();

    protected void S(String content, View view) {
        Snackbar.make(view, content, Snackbar.LENGTH_SHORT).show();
    }

    protected void T(String content) {
        ToastUtils.showToast(this, content);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        initialize();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
        initialize();
    }
}
