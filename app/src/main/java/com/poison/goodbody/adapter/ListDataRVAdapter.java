package com.poison.goodbody.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.poison.goodbody.R;
import com.poison.goodbody.bean.DataList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PoisonH on 2016/2/26.
 */
public class ListDataRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    private Context mContext;
    private List<DataList> mList;

    public ListDataRVAdapter(Context context)
    {
        this.mContext = context;
        mList=new ArrayList<>();
    }

    @Override
    public int getItemCount()
    {
        return mList.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        if (holder instanceof RecyclerHolder)
        {
            DataList mDataList = mList.get(position);
            if (null == mDataList)
            {
                return;
            }
            Uri mPicUri = Uri.parse(mList.get(position).getPicurl());
            ((RecyclerHolder) holder).sdv_imageview.setImageURI(mPicUri);
            ((RecyclerHolder) holder).tv_title.setText(mList.get(position).getTitle());
            ((RecyclerHolder) holder).tv_description.setText(mList.get(position).getDescription());
            ((RecyclerHolder) holder).tv_pubDate.setText(mList.get(position).getPubdate());
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        View view = LayoutInflater.from(mContext).inflate(R.layout.rv_item_layout, null);
        return new RecyclerHolder(view);
    }

    public static class RecyclerHolder extends RecyclerView.ViewHolder
    {
        public SimpleDraweeView sdv_imageview;
        public TextView tv_title;
        public TextView tv_description;
        public TextView tv_pubDate;

        public RecyclerHolder(View view)
        {
            super(view);
            sdv_imageview = (SimpleDraweeView) itemView.findViewById(R.id.sdv_imageview);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            tv_pubDate = (TextView) itemView.findViewById(R.id.tv_pubDate);
        }
    }

    public void setData(List<DataList> lists)
    {
        mList.addAll(mList.size(), lists);
        this.notifyDataSetChanged();
    }
}
