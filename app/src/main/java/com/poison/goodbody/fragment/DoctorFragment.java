package com.poison.goodbody.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.poison.goodbody.R;

/**
 * 中医
 * Created by Administrator on 2016/2/18.
 */
public class DoctorFragment extends Fragment
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_doctor_layout, null);
        return view;
    }
}
