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
public class DailyFgt extends BaseFragment {

    @Override
    protected int getRootView() {
        return R.layout.fgt_daily;
    }

    @Override
    protected void init() {

    }

    @OnClick({R.id.mb_daily_confirm, R.id.mb_daily_pay, R.id.mb_daily_sales})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.mb_daily_confirm:
                startActivity(new Intent(getContext(), AppointmentActivity.class));
                break;
            case R.id.mb_daily_pay:
                startActivity(new Intent(getContext(), PayActivity.class));
                break;
            case R.id.mb_daily_sales:
                startActivity(new Intent(getContext(), SalesActivity.class));
                break;
        }
    }
}
