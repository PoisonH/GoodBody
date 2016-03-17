package com.poison.goodbody.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.poison.goodbody.R;
import com.poison.goodbody.WebViewActivity;
import com.poison.goodbody.adapter.ListDataRVAdapter;
import com.poison.goodbody.bean.DataList;
import com.poison.goodbody.presenter.IPresenter;
import com.poison.goodbody.presenter.PresenterImpl;
import com.poison.goodbody.utils.CacheManager;
import com.poison.goodbody.utils.Constant;
import com.poison.goodbody.utils.ToastUtils;
import com.poison.goodbody.view.DataView;
import com.poison.goodbody.widget.PullLoadMoreRecyclerView;

import java.util.List;

/**
 * 焦点
 * Created by PoisonH on 2016/2/18.
 */
public class ListFragment extends Fragment implements DataView, ListDataRVAdapter.OnItemClickLitener
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
        mRVAdapter.setOnItemClickLitener(this);
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
            ToastUtils.showToast(getActivity(), "Not More Data...", Toast.LENGTH_SHORT);
        } else
        {
            mRVAdapter.setData(lists);
            mRVAdapter.setmStrFileName(catid + "");
        }
        pageIndex += 1;
    }


    @Override
    public void showLoadFailMsg()
    {
        ToastUtils.showToast(getActivity(), "数据加载失败...", Toast.LENGTH_SHORT);
        mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
    }

    class PullLoadMoreListener implements PullLoadMoreRecyclerView.PullLoadMoreListener
    {
        @Override
        public void onRefresh()
        {
            pageIndex = 1;
            showProgress();
            if (CacheManager.isExistDataCache(getActivity(), getFileName(catid + "")))
            {
                Message msg = Message.obtain();
                msg.what = 0;
                getDataHandler.sendMessage(msg);
            } else
            {
                mRVAdapter.cleanListData();
                mPresenter.loadDataList(catid, pageIndex);
            }
        }

        @Override
        public void onLoadMore()
        {
            mPresenter.loadDataList(catid, pageIndex);
        }
    }

    Handler getDataHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case 0:
                    mList = CacheManager.readObject(getActivity(), catid + "");
                    pageIndex = mList.size() / Constant.PAGESIZE + 1;
                    mRVAdapter.cleanListData();
                    mRVAdapter.setData(mList);
                    mRVAdapter.notifyDataSetChanged();
                    hideProgress();
                    break;
            }
        }
    };

    private String getFileName(String filename)
    {
        return CacheManager.getAppStoragePath(getActivity()) + "/" + filename + "DataList.bat";
    }

    @Override
    public void onItemClick(View view, int position)
    {
        Intent mIntent = new Intent();
        mIntent.setClass(getContext(), WebViewActivity.class);
        mIntent.putExtra("id", mRVAdapter.getData().get(position).getId() + "");
        startActivity(mIntent);
        View transitionView = view.findViewById(R.id.sdv_imageview);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), transitionView, getString(R.string.transition_news_img));
        ActivityCompat.startActivity(getActivity(), mIntent, options.toBundle());
    }

    @Override
    public void onItemLongClick(View view, int position)
    {
    }
}

