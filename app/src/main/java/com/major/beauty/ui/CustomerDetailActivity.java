package com.major.beauty.ui;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.major.beauty.R;
import com.major.beauty.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @desc: 客户详细资料及修改界面
 * @author: Major
 * @since: 2019/6/3 21:03
 */
public class CustomerDetailActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ctl_customer_detail)
    CollapsingToolbarLayout layout;

    @Override
    protected int getRootView() {
        return R.layout.act_customer_detail;
    }

    @Override
    protected void init() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        layout.setTitle("个人详情");
    }

    @OnClick(R.id.fab_customer_detail)
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_customer_detail:
                Snackbar.make(view, "编辑", Snackbar.LENGTH_SHORT).show();
                break;
        }
    }
}
