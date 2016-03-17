package com.poison.goodbody;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.widget.TextView;

import com.poison.goodbody.adapter.ViewPagerAdapter;
import com.poison.goodbody.fragment.ListFragment;
import com.poison.goodbody.utils.CacheManager;
import com.poison.goodbody.utils.Constant;
import com.poison.goodbody.utils.DensityUtils;
import com.poison.goodbody.widget.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PoisonH on 2016/2/19.
 */
public class MainActivity extends AppCompatActivity
{
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private PagerSlidingTabStrip mPagerSlidingTabStrip;
    private ViewPager mViewPager;
    private List<Fragment> mList = new ArrayList<>();
    private TextView mTvCache;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initToolbar();
    }

    private void initView()
    {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_drawer);
        mPagerSlidingTabStrip = (PagerSlidingTabStrip) findViewById(R.id.psts_pagertabs);
        mViewPager = (ViewPager) findViewById(R.id.vp_viewpager);

    }

    private void initToolbar()
    {
        mTvCache = (TextView) findViewById(R.id.tv_cache);
        Message msg = Message.obtain();
        msg.what = 0;
        getCacheSizeHandler.sendMessage(msg);
        mToolbar.setBackgroundResource(R.color.colorPrimaryDark);
        setSupportActionBar(mToolbar);
        //给左上角加上一个返回的图标
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mActionBarDrawerToggle.syncState();
        mActionBarDrawerToggle.onOptionsItemSelected(null);
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);

        mList.add(ListFragment.newInstance(Constant.FOCUS));
        mList.add(ListFragment.newInstance(Constant.SEASON));
        mList.add(ListFragment.newInstance(Constant.EXERCISE));
        mList.add(ListFragment.newInstance(Constant.CROWD));
        mList.add(ListFragment.newInstance(Constant.DIET));
        mList.add(ListFragment.newInstance(Constant.DOCTOR));
        mList.add(ListFragment.newInstance(Constant.DIABETES));

        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), MainActivity.this.getApplicationContext(), mList));
        mPagerSlidingTabStrip.setViewPager(mViewPager);
        mViewPager.setCurrentItem(0);

        initTabsValue();
    }

    /**
     * mPagerSlidingTabStrip默认值配置
     */
    private void initTabsValue()
    {
        //设置tab上title的字体大小（px）
        mPagerSlidingTabStrip.setTextSize((int) (DensityUtils.dp2px(this, 12)));
        // 底部游标颜色
        mPagerSlidingTabStrip.setIndicatorColor(Color.YELLOW);
        // tab的分割线颜色
        mPagerSlidingTabStrip.setDividerColor(Color.TRANSPARENT);
        // tab背景
        mPagerSlidingTabStrip.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        // tab底线高度
        mPagerSlidingTabStrip.setUnderlineHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics()));
        // 游标高度
        mPagerSlidingTabStrip.setIndicatorHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, getResources().getDisplayMetrics()));
        //设置选中的颜色
        mPagerSlidingTabStrip.setSelectedTextColor(Color.YELLOW);
        // 正常文字颜色
        mPagerSlidingTabStrip.setTextColor(Color.BLACK);
    }

    Handler getCacheSizeHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case 0:
                    try
                    {
                        String mStrCacheSize = CacheManager.getCacheSize(getApplicationContext());
                        mTvCache.setText(mStrCacheSize);
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };
}