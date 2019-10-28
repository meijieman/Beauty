package com.major.beauty.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.major.base.log.LogUtil;
import com.major.base.util.CommonUtil;
import com.major.beauty.R;
import com.major.beauty.adapter.CostRecordAdapter;
import com.major.beauty.base.BaseActivity;
import com.major.beauty.bean.CostRecord;
import com.major.beauty.dao.CostRecordDao;
import com.major.beauty.ui.decoration.SpaceDecoration;

import java.util.List;

import butterknife.BindView;

/**
 * Desc: 消费记录
 * <p>
 * Author: meijie
 * PackageName: com.major.beauty.ui
 * ProjectName: Beauty
 * Date: 2019/10/28 10:25
 */
public class CostRecordActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_cost_record)
    RecyclerView mRecyclerView;

    private CostRecordDao mDao = new CostRecordDao();

    @Override
    protected int getRootView() {
        return R.layout.act_cost_record;
    }

    @Override
    protected void init() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("消费记录");

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        SpaceDecoration itemDecoration = new SpaceDecoration(14);
        mRecyclerView.addItemDecoration(itemDecoration);
        CostRecordAdapter adapter = new CostRecordAdapter(this);
        mRecyclerView.setAdapter(adapter);

        List<CostRecord> query = mDao.query();
        if (CommonUtil.isNotEmpty(query)) {
            adapter.setData(query);
        } else {
            LogUtil.d("no data");
        }
    }
}
