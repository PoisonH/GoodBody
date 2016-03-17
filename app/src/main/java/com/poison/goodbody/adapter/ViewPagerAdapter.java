package com.poison.goodbody.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.poison.goodbody.bean.DataListEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPager适配器
 * Created by PoisonH on 2016/2/18.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter
{
    private final String[] TITLES = {"焦点", "时令", "运动", "人群", "食疗", "中医", "糖尿病"};
    private FragmentManager mFragmentManager = null;
    private Context mContext;
    private ArrayList<DataListEntity> mList;
    private List<Fragment> mListFragments;

    public ViewPagerAdapter(FragmentManager fm, Context context, List<Fragment> list)
    {
        super(fm);
        this.mFragmentManager = fm;
        this.mContext = context;
        this.mListFragments = list;
    }

    //提供一个加载数据的方法
    public void setDataList(ArrayList<DataListEntity> list)
    {
        this.mList = list;
        this.notifyDataSetChanged();
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return TITLES[position];
    }

    @Override
    public int getCount()
    {
        return TITLES.length;
    }

    @Override
    public Fragment getItem(int position)
    {

        return mListFragments.get(position);
    }
}
