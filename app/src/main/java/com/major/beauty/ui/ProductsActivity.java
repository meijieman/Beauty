package com.major.beauty.ui;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.major.beauty.R;
import com.major.beauty.adapter.ProductAdapter;
import com.major.beauty.base.BaseActivity;
import com.major.beauty.bean.Product;
import com.major.beauty.ui.behavior.HideButtonBehavior;
import com.major.beauty.ui.decoration.SpaceDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Desc: 产品列表界面
 * <p>
 * Author: meijie
 * PackageName: com.major.beauty.ui
 * ProjectName: Beauty
 * Date: 2019/6/4 12:45
 */
public class ProductsActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_management)
    RecyclerView mRecyclerView;
    @BindView(R.id.fab_management_add)
    FloatingActionButton mFabButton;

    private ProductAdapter mAdapter;

    @Override
    protected int getRootView() {
        return R.layout.act_products;
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
        mAdapter = new ProductAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener((pos, item, view) -> {
            // 转场动画
//            animateActivity(item, view);
        });

        mAdapter.setData(getDatas());

        CoordinatorLayout.LayoutParams cLayout = (CoordinatorLayout.LayoutParams) mFabButton.getLayoutParams();
        HideButtonBehavior myBehavior = new HideButtonBehavior();
        cLayout.setBehavior(myBehavior);
    }

    private List<Product> getDatas() {
        List<Product> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Product p = new Product();
            p.setName("高效产品 " + i);
            p.setInstruction("每隔3 min 一次");
            list.add(p);
        }
        return list;
    }


    @OnClick(R.id.fab_management_add)
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_management_add:
                startActivity(new Intent(this, ItemDetailActivity.class));
                break;
            default:

                break;
        }
    }
}
