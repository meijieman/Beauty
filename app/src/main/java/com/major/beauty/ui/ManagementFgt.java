package com.major.beauty.ui;

import android.content.Intent;
import android.view.View;

import com.major.beauty.R;
import com.major.beauty.base.BaseFragment;

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

    @Override
    protected int getRootView() {
        return R.layout.fgt_management;
    }

    @Override
    protected void init() {

    }

    @OnClick({R.id.btn_management_customers, R.id.btn_management_products})
    void onClick(View view) {
        switch(view.getId()) {
            case R.id.btn_management_customers:
                startActivity(new Intent(mActivity, CustomersActivity.class));
                break;
            case R.id.btn_management_products:

                break;
            default:
                break;
        }
    }
}
