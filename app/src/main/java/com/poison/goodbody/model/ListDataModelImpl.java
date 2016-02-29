package com.poison.goodbody.model;

import com.poison.goodbody.bean.DataListEntity;

import java.util.List;

/**
 * Created by PoisonH on 2016/2/29.
 */
public class ListDataModelImpl implements IListDataModel
{

    @Override
    public void loadDataList(String url, OnLoadDataListListener onLoadDataListListener)
    {

    }

    public interface OnLoadDataListListener
    {
        void onSuccess(List<DataListEntity> list);

        void onFailure(String msg, Exception e);
    }
}
