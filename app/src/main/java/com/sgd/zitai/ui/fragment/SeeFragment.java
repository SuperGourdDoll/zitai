package com.sgd.zitai.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sgd.zitai.R;
import com.sgd.zitai.adapter.MainViewPagerAdapter;
import com.sgd.zitai.ui.BaseFragment;
import com.sgd.zitai.ui.activity.MainActivity;
import com.sgd.zitai.ui.fragment.see.VideoFragment;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Allen Liu on 2016/8/2.
 */
public class SeeFragment extends BaseFragment {
    @BindView(R.id.vp)
    ViewPager mViewPager;
    @BindView(R.id.tl)
    TabLayout mTabLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_see,null);
        ButterKnife.bind(this,view);
        initialize();
        return view;
    }

    @Override
    protected void initialize() {
        initViewPager();
    }
    private void initViewPager() {
        List<Fragment> fragments=new LinkedList<>();
        for (int i=0;i<3;i++) {
            fragments.add(new VideoFragment());
        }
        MainViewPagerAdapter adapter=new MainViewPagerAdapter(getFragmentManager(),getActivity(),fragments);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
