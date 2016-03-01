package com.poison.goodbody.view;

import com.poison.goodbody.bean.DataList;
import com.poison.goodbody.bean.DataListEntity;

import java.util.List;

/**
 * Created by PoisonH on 2016/2/29.
 */
public interface DataView
{
    //显示进度条
    void showProgress();

    //隐藏进度条
    void hideProgress();

    //添加数据
    void addListData(List<DataList> lists);

    //显示加载失败显示
    void showLoadFailMsg();
}
