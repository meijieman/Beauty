package com.major.beauty.ui;

import android.app.TimePickerDialog;
import android.support.design.button.MaterialButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

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

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tie_appointment_remark)
    TextInputEditText mRemark;
    @BindView(R.id.mb_appointment_time_start)
    MaterialButton mTimeStart;
    @BindView(R.id.mb_appointment_time_end)
    MaterialButton mTimeEnd;

    private Calendar calendar;

    @Override
    protected int getRootView() {
        return R.layout.act_appointment;
    }

    @Override
    protected void init() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("预约");

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        calendar = Calendar.getInstance();

    }

    @OnClick({R.id.mb_appointment_name, R.id.mb_appointment_time_start, R.id.mb_appointment_time_end, R.id.mb_appointment_add})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.mb_appointment_name:
                // 弹出客户搜索框
                Snackbar.make(view, "customer", Snackbar.LENGTH_SHORT).show();

                break;
            case R.id.mb_appointment_time_start:
                TimePickerDialog timePickerDialog = new TimePickerDialog(this, (timePicker, i, i1) -> {
                    calendar.set(Calendar.HOUR_OF_DAY, i);
                    calendar.set(Calendar.MINUTE, i1);
                    String time = DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.getTime());
                    ToastUtil.showShort(time);

                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                timePickerDialog.show();
                break;
            case R.id.mb_appointment_time_end:

                break;
            case R.id.mb_appointment_add:
                String name = mTimeStart.getText().toString().trim();
                String phone = mTimeStart.getText().toString().trim();
                KeyboardUtil.hideKeyboard(this, view);
                Snackbar.make(view, name + "，" + phone, Snackbar.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
