package com.poison.goodbody.presenter;

import com.poison.goodbody.bean.DataList;
import com.poison.goodbody.fragment.FocusFragment;
import com.poison.goodbody.model.IListDataModel;
import com.poison.goodbody.model.ListDataModelImpl;
import com.poison.goodbody.utils.Constant;
import com.poison.goodbody.view.DataView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PoisonH on 2016/2/29.
 */
public class PresenterImpl implements IPresenter, ListDataModelImpl.OnLoadDataListListener
{
    private DataView mDataView;
    private IListDataModel mListDataModel;
    private List<DataList> mList;

    public PresenterImpl(DataView dataView)
    {
        this.mDataView = dataView;
        this.mListDataModel = new ListDataModelImpl();
    }

    @Override
    public void loadDataList(int catid, int page)
    {
        String url = getUrl(catid, page);
        //只有第一页的或者刷新的时候才显示刷新进度条
        if (page == 0)
        {
            mDataView.showProgress();
        }
        mListDataModel.loadDataList(url, this);
    }

    private String getUrl(int catid, int page)
    {
        String str = Constant.URL + catid + "&page=" + page + "&pagesize=1";
        return str;
    }

    @Override
    public void onSuccess(List<DataList> list)
    {
        mDataView.hideProgress();
        if (null == mList)
        {
            mList = new ArrayList<>();
            mList.addAll(list);
        } else
        {
            mList.addAll(mList.size(), list);
        }
        if (FocusFragment.isRefresh)
        {
            mList.clear();
            mList.addAll(list);
        }
        mDataView.addListData(mList);
    }

    @Override
    public void onFailure(String msg, Exception e)
    {
        mDataView.hideProgress();
        mDataView.showLoadFailMsg();
    }
}
