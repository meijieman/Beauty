package com.major.beauty.ui;

import android.arch.lifecycle.MutableLiveData;
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
import com.major.beauty.adapter.ItemAdapter;
import com.major.beauty.base.BaseActivity;
import com.major.beauty.base.BaseAdapter;
import com.major.beauty.bean.Item;
import com.major.beauty.dao.ItemDao;
import com.major.beauty.dialog.ModifyItemDlg;
import com.major.beauty.ui.behavior.HideButtonBehavior;
import com.major.beauty.ui.decoration.SpaceDecoration;
import com.major.beauty.ui.vm.CustomersVM;
import com.major.beauty.ui.vm.ItemsVM;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Desc: 项目列表
 * <p>
 * Author: meijie
 * PackageName: com.major.beauty.ui
 * ProjectName: Beauty
 * Date: 2019/6/4 12:45
 */
public class ItemsActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_management)
    RecyclerView mRecyclerView;
    @BindView(R.id.fab_management_add)
    FloatingActionButton mFabButton;

    private ItemAdapter mAdapter;
    private ItemDao mDao = new ItemDao();

    @Override
    protected int getRootView() {
        return R.layout.act_items;
    }

    @Override
    protected void init() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("项目档案");

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        SpaceDecoration itemDecoration = new SpaceDecoration(14);
        mRecyclerView.addItemDecoration(itemDecoration);
        mAdapter = new ItemAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        ViewModelProviders.of(this).get(ItemsVM.class).getUpdate()
                .observe(this, integer -> {
                    if (integer == ItemsVM.ADD) {
                        ToastUtil.showShort("收到更新");

                        List<Item> items = mDao.query();
                        if (CommonUtil.isNotEmpty(items)) {
                            mAdapter.setData(items);
                        } else {
                            LogUtil.i("no data");
                        }
                    }
                });

        mAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener<Item>() {
            @Override
            public void onItemClick(int pos, Item bean, View view) {
                ModifyItemDlg dialog = new ModifyItemDlg(ItemsActivity.this, bean);
                dialog.show();
            }

            @Override
            public void onItemLongClick(int pos, Item bean, View view) {
                new AlertDialog.Builder(ItemsActivity.this)
                        .setTitle("提示")
                        .setMessage(String.format("\n要删除用户[%s]吗？", bean.getName()))
                        .setPositiveButton("确定", (dialogInterface, i) -> {
                            // 更新数据库
                            long rst = mDao.delById(bean.getId());
                            LogUtil.v("删除用户 " + bean.getId() + ", rst " + rst);
                            mAdapter.del(pos);
                            dialogInterface.dismiss();

                            // 更新项目
                            MutableLiveData<Integer> updateLD = ViewModelProviders.of(ItemsActivity.this)
                                    .get(ItemsVM.class).getUpdate();
                            updateLD.postValue(CustomersVM.DEL);
                        })
                        .setNegativeButton("取消", null)
                        .show();
            }
        });

        List<Item> query = mDao.query();
        if (CommonUtil.isNotEmpty(query)) {
            mAdapter.setData(query);
        } else {
            LogUtil.i("no data");
        }

        CoordinatorLayout.LayoutParams cLayout = (CoordinatorLayout.LayoutParams) mFabButton.getLayoutParams();
        HideButtonBehavior myBehavior = new HideButtonBehavior();
        cLayout.setBehavior(myBehavior);
    }

    @OnClick(R.id.fab_management_add)
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_management_add:
                ModifyItemDlg dialog = new ModifyItemDlg(this);
                dialog.show();
                break;
            default:

                break;
        }
    }
}
