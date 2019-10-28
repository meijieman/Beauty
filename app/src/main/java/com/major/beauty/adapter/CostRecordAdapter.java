package com.major.beauty.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.major.base.util.CommonUtil;
import com.major.beauty.R;
import com.major.beauty.base.BaseAdapter;
import com.major.beauty.bean.CostRecord;
import com.major.beauty.bean.Item;
import com.major.beauty.bean.Product;
import com.major.beauty.util.TimeUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Desc: 消费记录列表
 * <p>
 * Author: meijie
 * PackageName: com.major.beauty
 * ProjectName: Beauty
 * Date: 2019/10/28 10:29
 */
public class CostRecordAdapter extends BaseAdapter<CostRecord, CostRecordAdapter.VH> {

    public CostRecordAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_cost_record, parent, false);
        return new CostRecordAdapter.VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int position) {
        CostRecord record = mData.get(position);

        if (CommonUtil.isNotEmpty(record.getItems())) {
            StringBuilder sb = new StringBuilder();
            for (Item item : record.getItems()) {
                sb.append(item.getName()).append(", ");
            }
            vh.itemName.setText(sb.toString());
        }

        if (CommonUtil.isNotEmpty(record.getProducts())) {
            StringBuilder sb = new StringBuilder();
            for (Product product : record.getProducts()) {
                sb.append(product.getName()).append(", ");
            }
            vh.productName.setText(sb.toString());
        }
        vh.time.setText(TimeUtil.format(record.getCreateTime()));
        vh.name.setText(record.getName());
        vh.phone.setText(record.getPhone());
        vh.pay.setText("总计: " + record.getPay());
    }

    static class VH extends RecyclerView.ViewHolder {

        View itemView;

        @BindView(R.id.tv_customer_name)
        TextView name;
        @BindView(R.id.tv_customer_phone)
        TextView phone;
        @BindView(R.id.tv_product_name)
        TextView productName;
        @BindView(R.id.tv_item_name)
        TextView itemName;
        @BindView(R.id.tv_pay)
        TextView pay;
        @BindView(R.id.tv_time)
        TextView time;

        VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }
    }
}
