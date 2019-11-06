package com.major.beauty.ui;

import android.app.TimePickerDialog;
import android.support.design.button.MaterialButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.major.base.log.LogUtil;
import com.major.base.util.KeyboardUtil;
import com.major.beauty.R;
import com.major.beauty.base.BaseActivity;
import com.major.beauty.bean.Appointment;
import com.major.beauty.bean.Customer;
import com.major.beauty.dao.ApptDao;
import com.major.beauty.dialog.SearchCustomDialog;

import java.text.DateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @desc: 客户预约界面
 * @author: Major
 * @since: 2019/9/9 23:40
 */
public class ApptActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tie_appt_comment)
    TextInputEditText mComment;
    @BindView(R.id.mb_appt_time_start)
    MaterialButton mTimeStartBtn;
    @BindView(R.id.mb_appt_time_end)
    MaterialButton mTimeEndBtn;
    @BindView(R.id.mb_appt_time_date)
    MaterialButton mDateBtn;

    @BindView(R.id.tiet_customer_info)
    TextView mCustomerInfo;

    private Calendar calendar;
    private ApptDao mDao = new ApptDao();

    private long mStartTime;
    private long mEndTime;
    private Customer mCustomer;

    @Override
    protected int getRootView() {
        return R.layout.act_appt;
    }

    @Override
    protected void init() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("客户预约");

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        calendar = Calendar.getInstance();

    }

    @OnClick({R.id.mb_customer_query, R.id.mb_appt_time_start, R.id.mb_appt_time_end, R.id.mb_appt_add, R.id.mb_appt_time_date})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.mb_customer_query:
                // 弹出客户搜索框
                SearchCustomDialog dialog = new SearchCustomDialog(this);
                dialog.setResultListener(customer -> {
                    mCustomerInfo.setText(customer.getName());
                    mCustomer = customer;
                });
                dialog.show();
                break;
            case R.id.mb_appt_time_date:
                // 选择日期

                String[] dates = {"今天", "明天", "后天", "2019/11/9"};
                AlertDialog dialog2 = new AlertDialog.Builder(this)
                        .setTitle("选择项目")
                        .setSingleChoiceItems(dates, 0, (dialogInterface, i) -> {

                            mDateBtn.setText(dates[i]);
                            dialogInterface.dismiss();
                        })
                        .create();
                dialog2.setCanceledOnTouchOutside(false);
                dialog2.show();
                break;
            case R.id.mb_appt_time_start:
                TimePickerDialog tpd = new TimePickerDialog(this, (timePicker, i, i1) -> {
                    calendar.set(Calendar.HOUR_OF_DAY, i);
                    calendar.set(Calendar.MINUTE, i1);
                    String time = DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.getTime());
                    mTimeStartBtn.setText(time);
                    mStartTime = calendar.getTimeInMillis();

                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                tpd.show();
                break;
            case R.id.mb_appt_time_end:
                TimePickerDialog tpdEnd = new TimePickerDialog(this, (timePicker, i, i1) -> {
                    calendar.set(Calendar.HOUR_OF_DAY, i);
                    calendar.set(Calendar.MINUTE, i1);
                    String time = DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.getTime());
                    mTimeEndBtn.setText(time);
                    mEndTime = calendar.getTimeInMillis();

                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                tpdEnd.show();
                break;
            case R.id.mb_appt_add:
                if (mCustomer == null) {
                    Snackbar.make(view, "请选择需要预约的客户", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                KeyboardUtil.hideKeyboard(this, view);

                Appointment appt = new Appointment();
                appt.setCid(mCustomer.getId());
                appt.setName(mCustomer.getName());
                appt.setPhone(mCustomer.getPhone());
                appt.setStartTime(mStartTime);
                appt.setEndTime(mEndTime);
                appt.setCreateTime(System.currentTimeMillis());
                appt.setComment(mComment.getText().toString());

                long update = mDao.insertOrUpdate(appt);
                LogUtil.i("update " + update);
                if (update != -1) {
                    Snackbar.make(view, "预约成功", Snackbar.LENGTH_SHORT).show();
                    finish();
                }

                break;
            default:
                break;
        }
    }
}
