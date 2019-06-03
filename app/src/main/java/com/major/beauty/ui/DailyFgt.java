package com.major.beauty.ui;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.major.beauty.R;
import com.major.beauty.base.BaseFgt;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Desc: TODO
 * <p>
 * Author: meijie
 * PackageName: com.major.beauty.vm
 * ProjectName: Beauty
 * Date: 2019/6/3 12:42
 */
public class DailyFgt extends BaseFgt {

    @BindView(R.id.til_daily_content)
    TextInputLayout mContextTil;

    @BindView(R.id.tie_daily_content)
    TextInputEditText mContextTie;


    @Override
    protected int getRootView() {
        return R.layout.fgt_daily;
    }

    @Override
    protected void init() {
        mContextTie.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                mContextTil.setErrorEnabled(false);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    mContextTil.setError("输入为空");
                    mContextTil.setErrorEnabled(true);
                } else {
                    mContextTil.setErrorEnabled(false);
                }
            }
        });
    }


    @OnClick(R.id.mb_daily_confirm)
    void onClick(View view){
        switch (view.getId()) {
            case R.id.mb_daily_confirm:

                String text = mContextTie.getText().toString().trim();

                break;
        }
    }
}
