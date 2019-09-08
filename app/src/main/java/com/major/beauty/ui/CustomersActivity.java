package com.major.beauty.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.major.base.log.LogUtil;
import com.major.base.util.CommonUtil;
import com.major.base.util.ToastUtil;
import com.major.beauty.R;
import com.major.beauty.adapter.CustomerAdapter;
import com.major.beauty.base.BaseActivity;
import com.major.beauty.base.BaseAdapter;
import com.major.beauty.base.Constant;
import com.major.beauty.bean.Customer;
import com.major.beauty.dao.CustomerDao;
import com.major.beauty.ui.behavior.HideButtonBehavior;
import com.major.beauty.ui.decoration.SpaceDecoration;
import com.major.beauty.ui.vm.CustomersVM;

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
    private CustomerDao mDao = new CustomerDao();

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
        actionBar.setTitle("顾客管理");

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        SpaceDecoration itemDecoration = new SpaceDecoration(14);
        mRecyclerView.addItemDecoration(itemDecoration);
        mAdapter = new CustomerAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener<Customer>() {
            @Override
            public void onItemClick(int pos, Customer item, View view) {
                // 转场动画
                animateActivity(item.getCid(), view);
            }

            @Override
            public void onLongClick(int pos, Customer bean, View view) {
                new AlertDialog.Builder(CustomersActivity.this)
                        .setTitle("提示")
                        .setMessage(String.format("\n要删除用户[%s]吗？", bean.getName()))
                        .setPositiveButton("确定", (dialogInterface, i) -> {
                            // 更新数据库
                            long rst = mDao.delById(bean.getCid());
                            LogUtil.v("删除用户 " + bean.getCid() + ", rst " + rst);
                            mAdapter.del(pos);
                            dialogInterface.dismiss();

                            // 更新顾客数量
                            CustomersVM.SingletonLiveData<Integer> updateLD = ViewModelProviders.of(CustomersActivity.this)
                                    .get(CustomersVM.class).getUpdate();
                            updateLD.postValue(CustomersVM.DEL);
                        })
                        .setNegativeButton("取消", null)
                        .show();
            }
        });

        List<Customer> customers = mDao.query();
        if (CommonUtil.isNotEmpty(customers)) {
            mAdapter.setData(customers);
        } else {
            LogUtil.i("no data");
        }

        CoordinatorLayout.LayoutParams cLayout = (CoordinatorLayout.LayoutParams) mFabButton.getLayoutParams();
        HideButtonBehavior myBehavior = new HideButtonBehavior();
        cLayout.setBehavior(myBehavior);

        ViewModelProviders.of(this).get(CustomersVM.class).getUpdate()
                .observe(this, integer -> {
                    if (integer == CustomersVM.ADD) {
                        ToastUtil.showShort("收到更新");

                        List<Customer> customers1 = mDao.query();
                        if (CommonUtil.isNotEmpty(customers1)) {
                            mAdapter.setData(customers1);
                        } else {
                            LogUtil.i("no data");
                        }
                    }
                });
    }

    public void animateActivity(long cid, View appIcon) {
        Intent intent = new Intent(this, CustomerDetailActivity.class);
        intent.putExtra(Constant.EXTRA_CID, cid);
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                Pair.create(mFabButton, "fab"), Pair.create(appIcon, "appIcon"));
        startActivity(intent, transitionActivityOptions.toBundle());
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
