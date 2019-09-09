package com.major.beauty.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
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
import com.major.beauty.adapter.ProductAdapter;
import com.major.beauty.base.BaseActivity;
import com.major.beauty.base.BaseAdapter;
import com.major.beauty.bean.Product;
import com.major.beauty.dao.ProductDao;
import com.major.beauty.dialog.ModifyProductDlg;
import com.major.beauty.ui.behavior.HideButtonBehavior;
import com.major.beauty.ui.decoration.SpaceDecoration;
import com.major.beauty.ui.vm.CustomersVM;
import com.major.beauty.ui.vm.ProductsVM;

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

    private ProductDao mDao = new ProductDao();

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
        actionBar.setTitle("产品档案");

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        SpaceDecoration itemDecoration = new SpaceDecoration(14);
        mRecyclerView.addItemDecoration(itemDecoration);
        ProductAdapter adapter = new ProductAdapter(this);
        mRecyclerView.setAdapter(adapter);

        ViewModelProviders.of(ProductsActivity.this).get(ProductsVM.class).getUpdate()
                .observe(this, integer -> {
                    ToastUtil.showShort("收到更新");
                    if (integer == CustomersVM.ADD) {
                        List<Product> Product = mDao.query();
                        if (CommonUtil.isNotEmpty(Product)) {
                            adapter.setData(Product);
                        } else {
                            LogUtil.i("no data");
                        }
                    }
                });

        adapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener<Product>() {
            @Override
            public void onItemClick(int pos, Product bean, View view) {
                // 转场动画
//            animateActivity(item, view);
                ModifyProductDlg dialog = new ModifyProductDlg(ProductsActivity.this, bean);
                dialog.show();
            }

            @Override
            public void onItemLongClick(int pos, Product bean, View view) {
                new AlertDialog.Builder(ProductsActivity.this)
                        .setTitle("提示")
                        .setMessage(String.format("\n要删除产品[%s]吗？", bean.getName()))
                        .setPositiveButton("确定", (dialogInterface, i) -> {
                            // 更新数据库
                            long rst = mDao.delById(bean.getId());
                            LogUtil.v("删除产品 " + bean.getId() + ", rst " + rst);
                            adapter.del(pos);
                            dialogInterface.dismiss();
                        })
                        .setNegativeButton("取消", null)
                        .show();
            }
        });

        List<Product> query = mDao.query();
        if (CommonUtil.isNotEmpty(query)) {
            adapter.setData(query);
        }

        CoordinatorLayout.LayoutParams cLayout = (CoordinatorLayout.LayoutParams) mFabButton.getLayoutParams();
        HideButtonBehavior myBehavior = new HideButtonBehavior();
        cLayout.setBehavior(myBehavior);
    }

    @OnClick(R.id.fab_management_add)
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_management_add:
                ModifyProductDlg dialog = new ModifyProductDlg(this);
                dialog.show();
//                skipIntent(ProductDetailActivity.class);
                break;
            default:

                break;
        }
    }
}
