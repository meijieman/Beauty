package com.major.beauty.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc: recyclerView 通用 adapter
 * @author: Major
 * @since: 2019/3/10 11:19
 */
public abstract class BaseAdapter<T, V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<V> {

    protected Context mContext;
    protected List<T> mData;

    public BaseAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<T> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public List<T> getDatas(){
        return mData;
    }

    public void addData(T data){
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.add(data);
        notifyDataSetChanged();
    }

    public void del(int pos) {
        if (pos < 0 || pos > mData.size() - 1) {
            return;
        }
        mData.remove(pos);
        notifyItemRemoved(pos);
        notifyItemRangeChanged(pos, mData.size() - 1, "");
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    protected OnItemClickListener<T> mListener;

    public void setOnItemClickListener(OnItemClickListener<T> listener) {
        mListener = listener;
    }

    public interface OnItemClickListener<T> {

        void onItemClick(int pos, T bean, View view);

        default void onItemLongClick(int pos, T bean, View view) {
        }
    }
}
