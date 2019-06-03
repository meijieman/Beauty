package com.major.beauty.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Desc: TODO
 * <p>
 * Author: meijie
 * PackageName: com.major.beauty.base
 * ProjectName: Beauty
 * Date: 2019/6/3 11:35
 */
public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder mBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getRootView());
        mBind = ButterKnife.bind(this);

        init();

    }

    protected abstract int getRootView();

    protected abstract void init();

    @Override
    protected void onDestroy() {

        mBind.unbind();
        super.onDestroy();
    }
}
