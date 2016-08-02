package com.sgd.zitai.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.sgd.zitai.R;
import com.sgd.zitai.adapter.MainViewPagerAdapter;
import com.sgd.zitai.ui.BaseActivity;
import com.sgd.zitai.ui.fragment.IPFragment;
import com.sgd.zitai.ui.fragment.TextFragment;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
//    @BindView(R.id.container)
//    FrameLayout container;
    @BindView(R.id.navigationView)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.vp)
    ViewPager mViewPager;
    @BindView(R.id.tl)
    TabLayout mTabLayout;

    private SparseArray<Fragment> fragmentSparseArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        init();
        initViewPager();
//        initData();
    }

    private void initViewPager() {

        List<Fragment> fragments=new LinkedList<>();
        for (int i=0;i<3;i++) {
            if(i != 2) {
                fragments.add(IPFragment.newInstance());
            }else{
                fragments.add(TextFragment.newInstance());
            }
        }
        MainViewPagerAdapter adapter=new MainViewPagerAdapter(getSupportFragmentManager(),MainActivity.this,fragments);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initData() {
        fragmentSparseArray = new SparseArray<>();
        fragmentSparseArray.append(0, new IPFragment());
        for (int i = 0; i < fragmentSparseArray.size(); i++) {
//            getSupportFragmentManager().beginTransaction().add(R.id.container, fragmentSparseArray.get(i)).commit();
        }
        startFragment(IPFragment.class);
    }

    private void init() {
        setSupportActionBar(toolbar);
        final ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        actionBarDrawerToggle.syncState();
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                actionBarDrawerToggle.onDrawerSlide(drawerView, slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                actionBarDrawerToggle.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                actionBarDrawerToggle.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                actionBarDrawerToggle.onDrawerStateChanged(newState);
            }
        });
//        RxNavigationView.itemSelections(navigationView).subscribe(item -> {
//            item.setChecked(true);
//            drawerLayout.closeDrawers();
//            switch (item.getItemId()) {
//                case R.id.item_ip:
//                    startFragment(IPFragment.class);
//                    break;
//
//            }
//
//        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setChecked(true);
                drawerLayout.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.item_today:
                        //startFragment(IPFragment.class);
                        Snackbar.make(navigationView,"today",Snackbar.LENGTH_SHORT).show();
                        break;
                    case R.id.item_see:
                        Snackbar.make(navigationView,"see",Snackbar.LENGTH_SHORT).show();
                        break;
                    case R.id.item_voice:
                        Snackbar.make(navigationView,"voice",Snackbar.LENGTH_SHORT).show();
                        break;
                    case R.id.item_collection:
                        Snackbar.make(navigationView,"collection",Snackbar.LENGTH_SHORT).show();
                        break;
                    case R.id.item_exit:
                        Snackbar.make(navigationView,"exit",Snackbar.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    private void startFragment(Class<?> fragmentClass) {
        for (int i = 0; i < fragmentSparseArray.size(); i++) {
            Fragment fragment = fragmentSparseArray.get(i);
            if (fragment.getClass().equals(fragmentClass)) {
                getSupportFragmentManager().beginTransaction().show(fragment).commit();
            } else {
                getSupportFragmentManager().beginTransaction().hide(fragment).commit();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_setting:
                Snackbar.make(navigationView,"设置",Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.action_nightmode:
                Snackbar.make(navigationView,"夜间模式",Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.action_about:
                Snackbar.make(navigationView,"关于app",Snackbar.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}

