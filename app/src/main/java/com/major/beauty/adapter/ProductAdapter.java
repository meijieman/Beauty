package com.major.beauty.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.major.beauty.R;
import com.major.beauty.base.BaseAdapter;
import com.major.beauty.bean.Product;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @desc: 产品列表
 * @author: Major
 * @since: 2019/6/7 23:36
 */
public class ProductAdapter extends BaseAdapter<Product, ProductAdapter.VH> {

    public ProductAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_products, viewGroup, false);
        return new ProductAdapter.VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        Product product = mData.get(i);
        vh.name.setText(product.getName());
        vh.instruction.setText(product.getInstruction());

        vh.itemView.setOnClickListener(view -> {
            if (mListener != null) {
                mListener.onItemClick(i, product, null);
            }
        });
        vh.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        ObjectAnimator upAnim = ObjectAnimator.ofFloat(view, "translationZ", 16);
                        upAnim.setDuration(100);
                        upAnim.setInterpolator(new DecelerateInterpolator());
                        upAnim.start();
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        ObjectAnimator downAnim = ObjectAnimator.ofFloat(view, "translationZ", 0);
                        downAnim.setDuration(100);
                        downAnim.setInterpolator(new AccelerateInterpolator());
                        downAnim.start();
                        break;
                }
                return false;
            }
        });
    }

    static class VH extends RecyclerView.ViewHolder {
        View itemView;

        @BindView(R.id.tv_product_name)
        TextView name;

        @BindView(R.id.tv_product_instruction)
        TextView instruction;

        public VH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }
    }
}
