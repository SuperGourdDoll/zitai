package com.sgd.zitai.ui;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;

import com.sgd.zitai.utils.ToastUtils;

/**
 * Created by Allen Liu on 2016/6/2.
 */
public abstract  class BaseFragment extends Fragment {
    protected abstract void initialize();
    protected void T(String content) {
        ToastUtils.showToast(getActivity(), content);
    }
    protected void S(String content, View view) {
        Snackbar.make(view, content, Snackbar.LENGTH_SHORT).show();
    }
}
