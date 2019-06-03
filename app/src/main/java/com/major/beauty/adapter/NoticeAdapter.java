package com.major.beauty.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ework.R;
import com.ework.base.BaseAdapter;
import com.ework.bean.Notice;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @desc: 通知 adapter
 * @author: Major
 * @since: 2019/3/2 10:33
 */
public class NoticeAdapter extends BaseAdapter<Notice, NoticeAdapter.VH> {

    public NoticeAdapter(Context context) {
        super(context);
    }

    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_notice, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Notice notice = mData.get(position);
        holder.time.setText(notice.getNoticeTime());
        holder.title.setText(notice.getTitle());
        holder.content.setText(notice.getContent());
    }

    static class VH extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_item_time)
        TextView time;
        @BindView(R.id.tv_item_title)
        TextView title;
        @BindView(R.id.tv_item_content)
        TextView content;

        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
