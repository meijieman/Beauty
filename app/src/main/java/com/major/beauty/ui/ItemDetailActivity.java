package com.major.beauty.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.major.beauty.R;
import com.major.beauty.adapter.MutilInputAdapter;
import com.major.beauty.base.BaseActivity;
import com.major.beauty.bean.SmartItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @desc: 项目详情
 * @author: Major
 * @since: 2019/6/7 23:42
 */
public class ItemDetailActivity extends BaseActivity {

    @BindView(R.id.rv_item_detail)
    RecyclerView mRecyclerView;

    @Override
    protected int getRootView() {
        return R.layout.act_item_detail;
    }

    @Override
    protected void init() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        MutilInputAdapter adapter = new MutilInputAdapter(this);
        mRecyclerView.setAdapter(adapter);
        adapter.setData(getDatas());
    }

    private List<SmartItem> getDatas() {
        List<SmartItem> list = new ArrayList<>();
        {
            SmartItem item = new SmartItem(SmartItem.ITEM_EDIT);
            item.setTitle("标题");
            item.setSubTitle("副标题");
            list.add(item);
        }
        {
            SmartItem item = new SmartItem(SmartItem.ITEM_EDIT);
            item.setTitle("标题");
            item.setSubTitle("副标题");
            list.add(item);
        }
        {
            SmartItem item = new SmartItem(SmartItem.ITEM_SPINNER);
            item.setTitle("标题");
            item.setSubTitle("副标题");
            List<String> data = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                data.add("选项 " + i);
            }
            item.setSpinnerShow(data);
            list.add(item);
        }
        {
            SmartItem item = new SmartItem(SmartItem.ITEM_SPINNER);
            item.setTitle("标题");
            item.setSubTitle("副标题");
            list.add(item);
        }
        {
            SmartItem item = new SmartItem(SmartItem.ITEM_TIMMER);
            item.setTitle("标题");
            list.add(item);
        }

        return list;
    }
}
