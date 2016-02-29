package com.poison.goodbody.model;

/**
 * Created by PoisonH on 2016/2/29.
 */
public interface IListDataModel
{
    void loadDataList(String url,ListDataModelImpl.OnLoadDataListListener onLoadDataListListener);
}
