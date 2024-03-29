package com.major.beauty.ui;

import android.animation.ObjectAnimator;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.major.base.util.ToastUtil;
import com.major.beauty.R;
import com.major.beauty.base.BaseFragment;
import com.major.beauty.dao.CustomerDao;
import com.major.beauty.ui.vm.CustomersVM;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Desc: TODO
 * <p>
 * Author: meijie
 * PackageName: com.major.beauty.vm
 * ProjectName: Beauty
 * Date: 2019/6/3 12:42
 */
public class ManagementFgt extends BaseFragment {

    @BindView(R.id.btn_management_customers)
    CardView mCustomers;
    @BindView(R.id.btn_management_products)
    CardView mProducts;
    @BindView(R.id.btn_management_items)
    CardView mItems;
    @BindView(R.id.tv_count_customer)
    TextView mCounts;

    private CustomerDao mDao = new CustomerDao();

    @Override
    protected int getRootView() {
        return R.layout.fgt_management;
    }

    @Override
    protected void init() {
        mCustomers.setOnTouchListener(this::onTouch);
        mProducts.setOnTouchListener(this::onTouch);
        mItems.setOnTouchListener(this::onTouch);

        mCounts.setText(String.format("共%s位", mDao.queryCount()));

        ViewModelProviders.of(this).get(CustomersVM.class).getUpdate()
                .observe(this, integer -> {
                    if (integer == CustomersVM.ADD
                            || integer == CustomersVM.DEL) {
                        ToastUtil.showShort("收到更新");

                        mCounts.setText(String.format("共%s位", mDao.queryCount()));
                    }
                });
    }

    private boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
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

    @OnClick({R.id.btn_management_customers, R.id.btn_management_products, R.id.btn_management_items})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_management_customers:
                startActivity(new Intent(mActivity, CustomersActivity.class));
                break;
            case R.id.btn_management_products:
                startActivity(new Intent(mActivity, ProductsActivity.class));

                break;
            case R.id.btn_management_items:
                startActivity(new Intent(mActivity, ItemsActivity.class));
                break;
            default:
                break;
        }
    }
}
