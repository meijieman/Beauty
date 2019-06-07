package com.major.beauty.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.major.base.util.CommonUtil;
import com.major.base.util.KeyboardUtil;
import com.major.base.util.ToastUtil;
import com.major.beauty.R;
import com.major.beauty.base.BaseFragment;

import java.text.DateFormat;
import java.util.Calendar;

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
public class DailyFgt extends BaseFragment {

    @BindView(R.id.til_daily_content)
    TextInputLayout mContextTil;
    @BindView(R.id.tie_daily_content)
    TextInputEditText mContextTie;
    @BindView(R.id.til_daily_content_1)
    TextInputLayout mPhoneTil;
    @BindView(R.id.tie_daily_content_1)
    TextInputEditText mPhoneTie;

    Calendar calendar;

    @Override
    protected int getRootView() {
        return R.layout.fgt_daily;
    }

    @Override
    protected void init() {
        calendar = Calendar.getInstance();

        mContextTie.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

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
        mContextTie.setOnFocusChangeListener((v, hasFocus) -> {
            if (mContextTie == null) {
                return;
            }
            if (hasFocus) {
                mContextTil.setErrorEnabled(false);
            } else {
                String text = mContextTie.getText().toString().trim();
                if (CommonUtil.isEmpty(text)) {
                    mContextTil.setError("输入为空");
                    mContextTil.setErrorEnabled(true);
                }
            }
        });
    }

    @OnClick({R.id.mb_daily_confirm, R.id.mb_daily_pay, R.id.mb_daily_sales })
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.mb_daily_confirm:

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), (view1, year, monthOfYear, dayOfMonth) -> {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, monthOfYear);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    String date = DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar.getTime());
                    ToastUtil.showShort(date);
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();

                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), (timePicker, i, i1) -> {
                    calendar.set(Calendar.HOUR_OF_DAY, i);
                    calendar.set(Calendar.MINUTE, i1);
                    String time = DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.getTime());
                    ToastUtil.showShort(time);

                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                timePickerDialog.show();


                String name = mContextTie.getText().toString().trim();
                String phone = mPhoneTie.getText().toString().trim();
                KeyboardUtil.hideKeyboard(getContext(), view);
                Snackbar.make(view, name + "，" + phone, Snackbar.LENGTH_SHORT).show();

                break;
            case R.id.mb_daily_pay:
                startActivity(new Intent(getContext(), PayActivity.class));
                break;
            case R.id.mb_daily_sales:
                startActivity(new Intent(getContext(), SalesActivity.class));

                break;
        }
    }
}
