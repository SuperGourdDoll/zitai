package com.sgd.zitai.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sgd.zitai.R;

import java.util.List;

/**
 * Created by telixin on 2016/7/29.
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private Context context;

    public MainViewPagerAdapter(FragmentManager fm, Context context, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String ret = null;
        switch (position) {
            case 0:
                ret = context.getResources().getString(R.string.record);
                break;
            case 1:
                ret = context.getResources().getString(R.string.picture);
                break;
            case 2:
                ret = context.getResources().getString(R.string.word);
                break;
            default:
                break;
        }
        return ret;
    }

    @Override
    public int getCount() {
        if (fragments != null) {
            return fragments.size();
        }else{
            return 0;
        }
    }
}
