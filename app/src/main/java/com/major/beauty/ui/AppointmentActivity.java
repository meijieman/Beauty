package com.major.beauty.ui;

import android.app.TimePickerDialog;
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
import com.major.beauty.base.BaseActivity;

import java.text.DateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @desc: 客户预约界面
 * @author: Major
 * @since: 2019/9/9 23:40
 */
public class AppointmentActivity extends BaseActivity {

    @BindView(R.id.til_daily_content)
    TextInputLayout mContextTil;
    @BindView(R.id.tie_daily_content)
    TextInputEditText mContextTie;
    @BindView(R.id.til_daily_content_1)
    TextInputLayout mPhoneTil;
    @BindView(R.id.tie_daily_content_1)
    TextInputEditText mPhoneTie;

    private Calendar calendar;

    @Override
    protected int getRootView() {
        return R.layout.act_appointment;
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

    @OnClick({R.id.mb_appointment_add})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.mb_appointment_add:
                TimePickerDialog timePickerDialog = new TimePickerDialog(this, (timePicker, i, i1) -> {
                    calendar.set(Calendar.HOUR_OF_DAY, i);
                    calendar.set(Calendar.MINUTE, i1);
                    String time = DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.getTime());
                    ToastUtil.showShort(time);

                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                timePickerDialog.show();

                String name = mContextTie.getText().toString().trim();
                String phone = mPhoneTie.getText().toString().trim();
                KeyboardUtil.hideKeyboard(this, view);
                Snackbar.make(view, name + "，" + phone, Snackbar.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
