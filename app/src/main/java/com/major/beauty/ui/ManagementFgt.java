package com.major.beauty.ui;

import android.support.v7.widget.RecyclerView;

import com.major.beauty.R;
import com.major.beauty.base.BaseFgt;

import butterknife.BindView;

/**
 * Desc: TODO
 * <p>
 * Author: meijie
 * PackageName: com.major.beauty.vm
 * ProjectName: Beauty
 * Date: 2019/6/3 12:42
 */
public class ManagementFgt extends BaseFgt {

    @BindView(R.id.rv_management)
    RecyclerView mRecyclerView;


    @Override
    protected int getRootView() {
        return R.layout.fgt_management;
    }

    @Override
    protected void init() {
        mRecyclerView.setAdapter();
    }
}
