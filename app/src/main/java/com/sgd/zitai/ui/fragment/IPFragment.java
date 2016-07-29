package com.sgd.zitai.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.jakewharton.rxbinding.view.RxView;
import com.sgd.zitai.R;
import com.sgd.zitai.bean.IpBean;
import com.sgd.zitai.contract.IpInfoContract;
import com.sgd.zitai.presenter.IpPresenter;
import com.sgd.zitai.ui.BaseFragment;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Allen Liu on 2016/6/2.
 */
public class IPFragment extends BaseFragment implements IpInfoContract.View {
    @BindView(R.id.et)
    EditText et;
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.linear)
    LinearLayout linear;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.pb)
    ProgressBar pb;
    IpInfoContract.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ip, null);
        ButterKnife.bind(this, view);
        initialize();
        return view;
    }

    public static IPFragment newInstance() {
        
        Bundle args = new Bundle();
        
        IPFragment fragment = new IPFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public ProgressBar getLoadingView() {
        return pb;
    }

    @Override
    public void dimissLoading() {
        pb.setVisibility(View.GONE);
    }

    @Override
    public void refreshIpInfo(IpBean ipBean) {
        tv.setText(ipBean.toString());
    }

    @Override
    public String getIpNum() {
        return et.getText().toString().trim();
    }

    @Override
    public void bindPresenter(IpInfoContract.Presenter preenter) {
        this.presenter = preenter;
    }

    @Override
    protected void initialize() {
        //将TestPresenter与此view绑定
        new IpPresenter(this);
        //可以做一些初始化操作
        presenter.initialize();
        RxView.clicks(btn).throttleFirst(1, TimeUnit.SECONDS).subscribe(aVoid -> {
            String ip = et.getText().toString().trim();
            if (!TextUtils.isEmpty(ip)) {
                presenter.loadData();
            }
        });
    }
}
