package com.poison.goodbody.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.poison.goodbody.R;

/**
 * 焦点
 * Created by Mr.H on 2016/2/18.
 */
public class FousFragment extends Fragment
{

    public static FousFragment newInstance(int position)
    {
        FousFragment fousFragment = new FousFragment();
        return fousFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_focus_layout, null);
        return view;
    }
}
