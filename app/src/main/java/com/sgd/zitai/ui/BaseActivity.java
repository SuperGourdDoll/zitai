package com.sgd.zitai.ui;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sgd.zitai.utils.ToastUtils;


/**
 * Created by Allen Liu on 2016/6/2.
 */
public class BaseActivity extends AppCompatActivity {
    public Context that=this;

    protected void Snack(String content,View view){
     Snackbar.make(view,content,Snackbar.LENGTH_SHORT).show();
    }

    protected void T(String content){
        ToastUtils.showToast(this,content);
    }

}
