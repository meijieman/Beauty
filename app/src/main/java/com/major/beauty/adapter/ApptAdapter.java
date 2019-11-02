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
import com.major.beauty.bean.Appointment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @desc: TODO
 * @author: Major
 * @since: 2019/11/2 9:56
 */
public class ApptAdapter extends BaseAdapter<Appointment, ApptAdapter.VH> {

    public ApptAdapter(Context context) {
        super(context);
    }

    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_appt, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Appointment appt = mData.get(position);
        holder.name.setText(appt.getName());
        holder.phone.setText(appt.getPhone());

        holder.itemView.setOnClickListener(view -> {
            if (mListener != null) {
                mListener.onItemClick(position, appt, null);
            }
        });
        holder.itemView.setOnLongClickListener(view -> {
            if (mListener != null) {
                mListener.onItemLongClick(position, appt, null);
            }
            return true;
        });
    }

    static class VH extends RecyclerView.ViewHolder {

        View itemView;

        @BindView(R.id.tv_appt_name)
        TextView name;
        @BindView(R.id.tv_appt_phone)
        TextView phone;

        VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }
    }
}