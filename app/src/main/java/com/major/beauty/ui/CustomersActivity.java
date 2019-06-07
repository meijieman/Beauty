package com.major.beauty.ui;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.major.beauty.R;
import com.major.beauty.adapter.CustomerAdapter;
import com.major.beauty.base.BaseActivity;
import com.major.beauty.bean.Customer;
import com.major.beauty.ui.behavior.HideButtonBehavior;
import com.major.beauty.ui.decoration.SpaceDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @desc: 客户列表
 * @author: Major
 * @since: 2019/6/3 21:03
 */
public class CustomersActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_management)
    RecyclerView mRecyclerView;
    @BindView(R.id.fab_management_add)
    FloatingActionButton mFabButton;

    private CustomerAdapter mAdapter;

    @Override
    protected int getRootView() {
        return R.layout.act_customers;
    }

    @Override
    protected void init() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("列表");

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        SpaceDecoration itemDecoration = new SpaceDecoration(14);
        mRecyclerView.addItemDecoration(itemDecoration);
        mAdapter = new CustomerAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener((pos, item, view) -> {
            // 转场动画
            animateActivity(item, view);
        });

        mAdapter.setData(getDatas());

        CoordinatorLayout.LayoutParams cLayout = (CoordinatorLayout.LayoutParams) mFabButton.getLayoutParams();
        HideButtonBehavior myBehavior = new HideButtonBehavior();
        cLayout.setBehavior(myBehavior);
    }

    public void animateActivity(Customer customer, View appIcon) {
        Intent intent = new Intent(this, CustomerDetailActivity.class);

        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, Pair.create(mFabButton, "fab"), Pair.create(appIcon, "appIcon"));
        startActivity(intent, transitionActivityOptions.toBundle());
    }

    private List<Customer> getDatas() {
        List<Customer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Customer c = new Customer();
            c.setName("小明 " + i);
            c.setPhone("17000000" + i);
            c.setCompany("北京市东城区故宫博物馆" + i);
            list.add(c);
        }
        return list;
    }

    @OnClick(R.id.fab_management_add)
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_management_add:
                startActivity(new Intent(CustomersActivity.this, CustomerDetailActivity.class));
                break;
            default:

                break;
        }
    }
}
