package com.poison.goodbody.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.poison.goodbody.R;

/**
 * 食疗
 * Created by PoisonH on 2016/2/18.
 */
public class DietFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_diet_layout, null);
        return view;
    }
}
