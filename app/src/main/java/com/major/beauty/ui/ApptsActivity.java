package com.major.beauty.ui;

import android.content.Intent;
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
import com.major.beauty.adapter.ApptAdapter;
import com.major.beauty.base.App;
import com.major.beauty.base.BaseActivity;
import com.major.beauty.base.BaseAdapter;
import com.major.beauty.base.Constant;
import com.major.beauty.bean.Appointment;
import com.major.beauty.dao.ApptDao;
import com.major.beauty.ui.decoration.SpaceDecoration;
import com.major.beauty.util.IntentUtil;

import java.util.List;

import butterknife.BindView;

/**
 * @desc: 预约列表界面
 * @author: Major
 * @since: 2019/11/2 9:54
 */
public class ApptsActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_management)
    RecyclerView mRecyclerView;

    private ApptAdapter mAdapter;
    private ApptDao mDao = new ApptDao();

    @Override
    protected int getRootView() {
        return R.layout.act_appts;
    }

    @Override
    protected void init() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("客户预约列表");

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        SpaceDecoration itemDecoration = new SpaceDecoration(14);
        mRecyclerView.addItemDecoration(itemDecoration);
        mAdapter = new ApptAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener<Appointment>() {
            @Override
            public void onItemClick(int pos, Appointment appt, View view) {
                // 查看详情
                Intent intent = new Intent(ApptsActivity.this, CustomerDetailActivity.class);
                intent.putExtra(Constant.EXTRA_CID, appt.getCid());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(int pos, Appointment appt, View view) {
                String[] opts = {"已完成", "已取消", "已过期", "电话联系"};
                AlertDialog dialog = new AlertDialog.Builder(ApptsActivity.this)
                        .setTitle("操作")
                        .setSingleChoiceItems(opts, -1, (dialogInterface, i) -> {
                            if (i == opts.length - 1) {
                                IntentUtil.callPhone(App.getInstance(), appt.getPhone());
                            } else {
                                ToastUtil.showShort(opts[i]);
                            }
                            dialogInterface.dismiss();
                        })
                        .create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });

        List<Appointment> appts = mDao.query();
        if (CommonUtil.isNotEmpty(appts)) {
            mAdapter.setData(appts);
        } else {
            LogUtil.i("no data");
        }
    }
}
