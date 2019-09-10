package com.major.beauty.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.major.base.util.ToastUtil;
import com.major.beauty.R;
import com.major.beauty.base.BaseAdapter;
import com.major.beauty.bean.ProductCount;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @desc: TODO
 * @author: Major
 * @since: 2019/9/10 21:51
 */
public class ProductCountAdapter extends BaseAdapter<ProductCount, ProductCountAdapter.VH> {

    public ProductCountAdapter(Context context) {
        super(context);
    }

    @Override
    public ProductCountAdapter.VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_product_count, viewGroup, false);
        return new ProductCountAdapter.VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductCountAdapter.VH vh, int i) {
        ProductCount productCount = mData.get(i);
        vh.name.setText(productCount.getProductName());
        vh.count.setText(String.format(Locale.CHINESE, "%d%s", productCount.getCount(), productCount.getProductUnit()));

        if (i == mData.size() - 1) {
            vh.add.setText("add");
        } else {
            vh.add.setText("del");
        }

        vh.add.setOnClickListener(view -> {
            if (i == mData.size() - 1) {
                if (mData.size() > 20) {
                    ToastUtil.showShort("最多只能添加20个产品");
                    return;
                }
                // 增加产品
                addData(new ProductCount());
            } else {
                // 删除产品
                del(i);
                notifyDataSetChanged();
            }
        });
    }

    static class VH extends RecyclerView.ViewHolder {
        View itemView;

        @BindView(R.id.btn_product_name)
        Button name;

        @BindView(R.id.btn_product_count)
        Button count;
        @BindView(R.id.btn_product_add)
        Button add;

        public VH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }
    }
}
