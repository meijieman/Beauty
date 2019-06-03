package com.major.beauty.ui;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.major.beauty.R;
import com.major.beauty.adapter.CustomerAdapter;
import com.major.beauty.base.BaseActivity;
import com.major.beauty.bean.Customer;
import com.major.beauty.ui.decoration.SpaceDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;

import static com.major.base.rx.rxtask.RxTask.doOnIOThreadDelay;

/**
 * @desc: 客户列表
 * @author: Major
 * @since: 2019/6/3 21:03
 */
public class CustomersActivity extends BaseActivity {

    @BindView(R.id.rv_management)
    RecyclerView mRecyclerView;
    @BindView(R.id.fab_management_add)
    FloatingActionButton mAdd;
    private CustomerAdapter mAdapter;

    @Override
    protected int getRootView() {
        return R.layout.act_customers;
    }

    @Override
    protected void init() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        SpaceDecoration itemDecoration = new SpaceDecoration(14);
        mRecyclerView.addItemDecoration(itemDecoration);
        mAdapter = new CustomerAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener((pos, item) -> {
            // 转场动画
            doOnIOThreadDelay(() -> startActivity(new Intent(CustomersActivity.this, CustomerDetailActivity.class)), 200, TimeUnit.MILLISECONDS);
        });

        mAdd.setOnClickListener(v -> {
            doOnIOThreadDelay(() -> startActivity(new Intent(CustomersActivity.this, CustomerDetailActivity.class)), 200, TimeUnit.MILLISECONDS);
        });

        mAdapter.setData(getDatas());
    }

    private List<Customer> getDatas() {
        List<Customer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Customer c = new Customer();
            c.setName("小明 " + i);
            c.setPhone("17000000" + i);
            c.setAddress("北京市东城区故宫博物馆" + i);
            list.add(c);
        }
        return list;
    }
}
