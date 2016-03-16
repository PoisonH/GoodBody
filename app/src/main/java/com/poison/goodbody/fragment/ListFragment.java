package com.poison.goodbody.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.poison.goodbody.R;
import com.poison.goodbody.adapter.ListDataRVAdapter;
import com.poison.goodbody.bean.DataList;
import com.poison.goodbody.presenter.IPresenter;
import com.poison.goodbody.presenter.PresenterImpl;
import com.poison.goodbody.utils.CacheManager;
import com.poison.goodbody.view.DataView;
import com.poison.goodbody.widget.PullLoadMoreRecyclerView;

import java.util.List;

/**
 * 焦点
 * Created by PoisonH on 2016/2/18.
 */
public class ListFragment extends Fragment implements DataView
{
    private ConvenientBanner mConvenientBanner;
    private PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private ListDataRVAdapter mRVAdapter;
    private IPresenter mPresenter;
    private static List<DataList> mList;
    private int pageIndex;
    //类别
    private int catid;

    public static ListFragment newInstance(int catid)
    {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putInt("catid", catid);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mPresenter = new PresenterImpl(this);
        catid = getArguments().getInt("catid");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_focus_layout, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        mConvenientBanner = (ConvenientBanner) view.findViewById(R.id.cb_banner);
        mPullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) view.findViewById(R.id.rv_recyclerview);
        //设置上拉刷新文字
        mPullLoadMoreRecyclerView.setFooterViewText("Loading Data...");
        mPullLoadMoreRecyclerView.setLinearLayout();
        mRVAdapter = new ListDataRVAdapter(getActivity());
        mPullLoadMoreRecyclerView.setAdapter(mRVAdapter);
        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreListener());
        new PullLoadMoreListener().onRefresh();
    }


    @Override
    public void showProgress()
    {
        //显示刷新进度条
        mPullLoadMoreRecyclerView.setRefreshing(true);
    }

    @Override
    public void hideProgress()
    {
        //隐藏刷新进度条
        mPullLoadMoreRecyclerView.setRefreshing(false);
        mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
    }

    @Override
    public void addListData(List<DataList> lists)
    {
        if (lists == null || lists.size() <= 0)
        {
            Toast.makeText(getActivity(), "Not More Data", Toast.LENGTH_SHORT).show();
        } else
        {
            mRVAdapter.setData(lists);
        }
        pageIndex += 1;
    }

    @Override
    public void showLoadFailMsg()
    {
        Toast.makeText(getActivity(), "加载数据失败", Toast.LENGTH_SHORT).show();
        mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
    }

    Handler myHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case 1:
                    mList = CacheManager.readObject(getActivity(), catid + "");
                    mRVAdapter.setData(mList);
                    mRVAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    class PullLoadMoreListener implements PullLoadMoreRecyclerView.PullLoadMoreListener
    {
        @Override
        public void onRefresh()
        {
            pageIndex = 1;
            showProgress();
            mPresenter.loadDataList(catid, pageIndex);
        }

        @Override
        public void onLoadMore()
        {
            mPresenter.loadDataList(catid, pageIndex);
        }
    }
}

