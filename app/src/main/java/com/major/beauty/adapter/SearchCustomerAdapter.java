package com.major.beauty.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.major.beauty.R;
import com.major.beauty.base.BaseAdapter;
import com.major.beauty.bean.Customer;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @desc: 顾客列表
 * @author: Major
 * @since: 2019/3/2 10:33
 */
public class SearchCustomerAdapter extends BaseAdapter<Customer, SearchCustomerAdapter.VH> {

    public SearchCustomerAdapter(Context context) {
        super(context);
    }

    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_customer_search, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Customer notice = mData.get(position);
        holder.name.setText(notice.getName());
        holder.phone.setText(notice.getPhone());
        holder.itemView.setOnClickListener(view -> {
            if (mListener != null) {
                mListener.onItemClick(position, notice, null);
            }
        });
        holder.itemView.setOnLongClickListener(view -> {
            if (mListener != null) {
                mListener.onItemLongClick(position, notice, null);
            }
            return true;
        });
    }

    static class VH extends RecyclerView.ViewHolder {

        View itemView;

        @BindView(R.id.tv_customer_name)
        TextView name;
        @BindView(R.id.tv_customer_phone)
        TextView phone;

        VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }
    }
}
