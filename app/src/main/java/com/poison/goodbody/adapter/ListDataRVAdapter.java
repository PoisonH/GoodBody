package com.poison.goodbody.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.poison.goodbody.R;
import com.poison.goodbody.bean.DataList;

import java.util.List;

/**
 * Created by PoisonH on 2016/2/26.
 */
public class ListDataRVAdapter extends RecyclerView.Adapter<ListDataRVAdapter.RecyclerHolder>
{

    private Context mContext;
    private List<DataList> mList;

    private boolean mShowFooter = true;
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    public ListDataRVAdapter(Context context)
    {
        this.mContext = context;
    }

    @Override
    public int getItemCount()
    {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position)
    {
        holder.mTv.setText(mList.get(position).getTitle());
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rv_item_layout, null);
        return new RecyclerHolder(view);
    }

    public static class RecyclerHolder extends RecyclerView.ViewHolder
    {
        private TextView mTv;

        public RecyclerHolder(View view)
        {
            super(view);
            mTv = (TextView) view.findViewById(R.id.tv);
        }
    }


    public boolean isShowFooter()
    {
        return this.mShowFooter;
    }


    public void isShowFooter(boolean showFooter)
    {
        this.mShowFooter = showFooter;
    }

    @Override
    public int getItemViewType(int position)
    {
        // 最后一个item设置为footerView
        if (!mShowFooter)
        {
            return TYPE_ITEM;
        }
        if (position + 1 == getItemCount())
        {
            return TYPE_FOOTER;
        } else
        {
            return TYPE_ITEM;
        }
    }

    public void setData(List<DataList> lists)
    {
        this.mList = lists;
        this.notifyDataSetChanged();
    }
}
