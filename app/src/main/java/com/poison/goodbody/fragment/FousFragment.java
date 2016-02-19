package com.poison.goodbody.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.poison.goodbody.R;
import com.poison.goodbody.model.OuterLayerEntity;
import com.poison.goodbody.utils.HttpUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 焦点
 * Created by PoisonH on 2016/2/18.
 */
public class FousFragment extends Fragment
{
    private ConvenientBanner mConvenientBanner;
    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_focus_layout, null);
        initView(view);
        return view;
    }

    private void initView(View view)
    {
        mConvenientBanner = (ConvenientBanner) view.findViewById(R.id.cb_banner);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_recyclerview);
    }

}
