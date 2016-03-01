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
        Uri mPicUri = Uri.parse(mList.get(position).getPicurl());
        holder.sdv_imageview.setImageURI(mPicUri);
        holder.tv_title.setText(mList.get(position).getTitle());
        holder.tv_description.setText(mList.get(position).getDescription());
        holder.tv_pubDate.setText(mList.get(position).getPubdate());
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType)
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
