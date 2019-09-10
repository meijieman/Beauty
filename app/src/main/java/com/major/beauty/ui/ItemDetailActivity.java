package com.major.beauty.ui;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.major.base.log.LogUtil;
import com.major.base.util.CommonUtil;
import com.major.base.util.ToastUtil;
import com.major.beauty.R;
import com.major.beauty.adapter.ProductCountAdapter;
import com.major.beauty.base.App;
import com.major.beauty.base.BaseActivity;
import com.major.beauty.base.Constant;
import com.major.beauty.bean.Item;
import com.major.beauty.bean.ProductCount;
import com.major.beauty.bean.SmartItem;
import com.major.beauty.dao.ItemDao;
import com.major.beauty.ui.vm.CustomersVM;
import com.major.beauty.ui.vm.ItemsVM;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;

/**
 * @desc: 项目详情
 * @author: Major
 * @since: 2019/6/7 23:42
 */
public class ItemDetailActivity extends BaseActivity {

    @BindView(R.id.rv_item_detail)
    RecyclerView mRecyclerView;
    @BindViews({R.id.tet_name, R.id.tet_price, R.id.tet_comment})
    List<TextInputEditText> mTies;

    private ItemDao mDao = new ItemDao();
    private Item mItem;
    private ProductCountAdapter mAdapter;

    @Override
    protected int getRootView() {
        return R.layout.act_item_detail;
    }

    @Override
    protected void init() {
        long iid = getIntent().getLongExtra(Constant.EXTRA_IID, -1);
        if (iid != -1) {
            mItem = mDao.queryById(iid, Item.class);
            if (mItem == null) {
                LogUtil.e("error " + iid);
            } else {
                mTies.get(0).setText(mItem.getName());
            }
        } else {
            mItem = new Item(App.getInstance().mAvatar.getName());
            List<ProductCount> productCounts = new ArrayList<>();
            productCounts.add(new ProductCount());
            mItem.setProductCounts(productCounts);
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ProductCountAdapter(this);

//        MutilInputAdapter adapter = new MutilInputAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setData(mItem.getProductCounts());

//        adapter.setData(getDatas());
    }

    @OnClick({R.id.mb_item_commit})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.mb_item_commit:
                String name = mTies.get(0).getText().toString();
                String comment = mTies.get(2).getText().toString();
                if (CommonUtil.isEmpty(name)) {
                    mTies.get(0).setError("项目名称不能为空");
                    return;
                }

                mItem.setName(name);
                mItem.setProductCounts(mAdapter.getDatas());
                mItem.setComment(comment);

                long rst = mDao.insertOrUpdate(mItem);
                LogUtil.i("rst " + rst);
                if (rst != -1) {
                    MutableLiveData<Integer> updateLD = ViewModelProviders.of(this)
                            .get(ItemsVM.class).getUpdate();
                    updateLD.postValue(CustomersVM.ADD);
                    ToastUtil.showShort("add success");
                    finish();
                } else {
                    ToastUtil.showShort("add fail");
                }
                break;
            default:
                break;
        }
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
