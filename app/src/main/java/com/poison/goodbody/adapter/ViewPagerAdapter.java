package com.poison.goodbody.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.poison.goodbody.fragment.FousFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.H on 2016/2/18.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter
{
    private final String[] TITLES = {"焦点", "时令", "运动", "人群", "食疗", "中医", "两性"};
    private List<Fragment> mList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> list)
    {
        super(fm);
        this.mList = list;
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

        return mList.get(position);
    }


}
