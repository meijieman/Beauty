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
import com.major.beauty.bean.Item;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @desc: 项目列表
 * @author: Major
 * @since: 2019/6/7 23:36
 */
public class ItemAdapter extends BaseAdapter<Item, ItemAdapter.VH> {

    public ItemAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_items, viewGroup, false);
        return new ItemAdapter.VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        Item item = mData.get(i);
        vh.name.setText(item.getName());
    }

    static class VH extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_item_name)
        TextView name;

        public VH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
