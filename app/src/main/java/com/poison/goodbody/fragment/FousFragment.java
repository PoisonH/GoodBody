package com.poison.goodbody.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.poison.goodbody.R;
import com.poison.goodbody.adapter.ListDataRVAdapter;
import com.poison.goodbody.bean.DataList;
import com.poison.goodbody.bean.DataListEntity;
import com.poison.goodbody.presenter.IPresenter;
import com.poison.goodbody.presenter.PresenterImpl;
import com.poison.goodbody.view.DataView;

import java.util.ArrayList;
import java.util.List;

/**
 * 焦点
 * Created by PoisonH on 2016/2/18.
 */
public class FousFragment extends Fragment implements DataView, SwipeRefreshLayout.OnRefreshListener
{
    //  private ConvenientBanner mConvenientBanner;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private ListDataRVAdapter mRVAdapter;
    private LinearLayoutManager manager;
    private IPresenter mPresenter;
    private ArrayList<DataList> mList;

    public static int pageIndex;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mPresenter = new PresenterImpl(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_focus_layout, null);
        initView(view);
        onRefresh();
        return view;
    }


    private void initView(View view)
    {
        // mConvenientBanner = (ConvenientBanner) view.findViewById(R.id.cb_banner);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl_swiperefreshlayout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.primary, R.color.primary_dark, R.color.primary_light, R.color.accent);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_recyclerview);
        manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRVAdapter = new ListDataRVAdapter(getActivity());
        mRecyclerView.setAdapter(mRVAdapter);
        mRecyclerView.addOnScrollListener(mOnScrollListener);

    }

    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener()
    {
        //最后一个可见item
        private int lastVisibleItem;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy)
        {
            super.onScrolled(recyclerView, dx, dy);
            //查找最后一个可见的item的位置
            lastVisibleItem = manager.findLastVisibleItemPosition();
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState)
        {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mRVAdapter.getItemCount() && mRVAdapter.isShowFooter())
            {
                mPresenter.loadDataList(0, pageIndex);
            }
        }

    };

    @Override
    public void showProgress()
    {
        //显示刷新进度条
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress()
    {
        //隐藏刷新进度条
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void addListData(List<DataList> lists)
    {
        mRVAdapter.isShowFooter(true);
        if (null == mList)
        {
            mList = new ArrayList<>();
        }
        mList.addAll(lists);
        if (0 == pageIndex)
        {
            mRVAdapter.setData(lists);
        } else
        {
            //如果没有更多数据了,则隐藏footer布局
            if (lists == null || lists.size() == 0)
            {
                mRVAdapter.isShowFooter(false);
            }
            mRVAdapter.notifyDataSetChanged();
        }
        pageIndex += 1;
    }

    @Override
    public void showLoadFailMsg()
    {
        Toast.makeText(getActivity(), "加载数据失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh()
    {
        pageIndex = 0;
        if (mList != null)
        {
            mList.clear();
        }
        mPresenter.loadDataList(0, pageIndex);
    }
}
