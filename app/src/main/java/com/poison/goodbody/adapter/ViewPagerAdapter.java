package com.poison.goodbody.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

import com.poison.goodbody.R;
import com.poison.goodbody.fragment.BothSexesFragment;
import com.poison.goodbody.fragment.CrowdFragment;
import com.poison.goodbody.fragment.DietFragment;
import com.poison.goodbody.fragment.DoctorFragment;
import com.poison.goodbody.fragment.ExerciseFragment;
import com.poison.goodbody.fragment.FousFragment;
import com.poison.goodbody.fragment.SeasonFragment;
import com.poison.goodbody.bean.DataListEntity;

import java.util.ArrayList;

/**
 * ViewPager适配器
 * Created by PoisonH on 2016/2/18.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter
{
    private final String[] TITLES = {"焦点", "时令", "运动", "人群", "食疗", "中医", "两性"};
    private FragmentManager mFragmentManager = null;
    private Context mContext;
    private Fragment mBothSexsFragment;
    private Fragment mCrowdFragment;
    private Fragment mDietFragment;
    private Fragment mDoctorFragment;
    private Fragment mExerciseFragment;
    private Fragment mFousFragment;
    private Fragment mSeasonFragment;
    private ArrayList<DataListEntity> mList;

    public ViewPagerAdapter(FragmentManager fm, Context context)
    {
        super(fm);
        this.mFragmentManager = fm;
        this.mContext = context;
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

        return setTabSelection(position);
    }

    /**
     * 根据选择的position实例化相应的fragment
     *
     * @param position
     * @return
     */
    private Fragment setTabSelection(int position)
    {
        // 开启一个Fragment事务
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (position)
        {
            case 0:
                if (mFousFragment == null)
                {
                    mFousFragment = new FousFragment();
                    transaction.add(R.id.vp_viewpager, mFousFragment);
                } else
                {
                    // 如果mHomeFragment不为空，则直接将它显示出来
                    transaction.show(mFousFragment);
                }
                return mFousFragment;
            case 1:
                if (mSeasonFragment == null)
                {
                    mSeasonFragment = new SeasonFragment();
                    transaction.add(R.id.vp_viewpager, mSeasonFragment);
                } else
                {
                    // 如果mHomeFragment不为空，则直接将它显示出来
                    transaction.show(mSeasonFragment);
                }
                return mSeasonFragment;
            case 2:
                if (mExerciseFragment == null)
                {
                    mExerciseFragment = new ExerciseFragment();
                    transaction.add(R.id.vp_viewpager, mExerciseFragment);
                } else
                {
                    // 如果mHomeFragment不为空，则直接将它显示出来
                    transaction.show(mExerciseFragment);
                }
                return mExerciseFragment;
            case 3:
                if (mCrowdFragment == null)
                {
                    mCrowdFragment = new CrowdFragment();
                    transaction.add(R.id.vp_viewpager, mCrowdFragment);
                } else
                {
                    // 如果mHomeFragment不为空，则直接将它显示出来
                    transaction.show(mCrowdFragment);
                }
                return mCrowdFragment;
            case 4:
                if (mDietFragment == null)
                {
                    mDietFragment = new DietFragment();
                    transaction.add(R.id.vp_viewpager, mDietFragment);
                } else
                {
                    // 如果mHomeFragment不为空，则直接将它显示出来
                    transaction.show(mDietFragment);
                }
                return mDietFragment;

            case 5:
                if (mDoctorFragment == null)
                {
                    mDoctorFragment = new DoctorFragment();
                    transaction.add(R.id.vp_viewpager, mDoctorFragment);
                } else
                {
                    // 如果mHomeFragment不为空，则直接将它显示出来
                    transaction.show(mDoctorFragment);
                }
                return mDoctorFragment;
            case 6:
                if (mBothSexsFragment == null)
                {
                    mBothSexsFragment = new BothSexesFragment();
                    transaction.add(R.id.vp_viewpager, mBothSexsFragment);
                } else
                {
                    // 如果mHomeFragment不为空，则直接将它显示出来
                    transaction.show(mBothSexsFragment);
                }
                return mBothSexsFragment;
        }
        transaction.commit();
        return null;
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction)
    {
        if (mFousFragment != null)
        {
            transaction.hide(mFousFragment);
        }
        if (mSeasonFragment != null)
        {
            transaction.hide(mSeasonFragment);
        }
        if (mExerciseFragment != null)
        {
            transaction.hide(mExerciseFragment);
        }
        if (mCrowdFragment != null)
        {
            transaction.hide(mCrowdFragment);
        }
        if (mDietFragment != null)
        {
            transaction.hide(mDietFragment);
        }
        if (mDoctorFragment != null)
        {
            transaction.hide(mDoctorFragment);
        }
        if (mBothSexsFragment != null)
        {
            transaction.hide(mBothSexsFragment);
        }
    }

}
