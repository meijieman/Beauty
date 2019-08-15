package com.major.beauty.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.major.beauty.R;
import com.major.beauty.base.BaseAdapter;
import com.major.beauty.bean.SmartItem;
import com.major.beauty.ui.view.SmartItemView;

/**
 * Desc: TODO
 * <p>
 * Author: meijie
 * PackageName: com.major.beauty.adapter
 * ProjectName: Beauty
 * Date: 2019/8/15 16:46
 */
public class MutilInputAdapter extends BaseAdapter<SmartItem, MutilInputAdapter.VH> {

    public MutilInputAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(mContext, R.layout.item_smart_item, null);
        return new VH(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull VH viewHolder, int i) {
        viewHolder.siv.setItem(mData.get(i));
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getType();
    }

    static class VH extends RecyclerView.ViewHolder{

        SmartItemView siv;

        public VH(@NonNull View itemView) {
            super(itemView);
            siv = itemView.findViewById(R.id.siv_root);

        }
    }
}
