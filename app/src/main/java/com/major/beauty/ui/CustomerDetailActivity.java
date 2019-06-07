package com.major.beauty.ui;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.major.base.util.ToastUtil;
import com.major.beauty.R;
import com.major.beauty.base.BaseActivity;
import com.major.beauty.bean.Customer;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;

/**
 * @desc: 客户详细资料及修改界面
 * @author: Major
 * @since: 2019/6/3 21:03
 */
public class CustomerDetailActivity extends BaseActivity {

    @BindViews({R.id.tet_name, R.id.tet_phone,
            R.id.tet_sex, R.id.tet_height,
            R.id.tet_weight, R.id.tiet_birthday,
            R.id.tet_lunar_birthday, R.id.tet_wedding_day,
            R.id.tet_skin_type, R.id.tet_nursing_needs,
            R.id.tet_available_time, R.id.tet_comment})
    List<TextInputEditText> mTies;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ctl_customer_detail)
    CollapsingToolbarLayout layout;
    @BindView(R.id.fab_customer_detail)
    FloatingActionButton mFab;

    private boolean mIsEditable; // 是否是编辑状态
    private Calendar calendar;

    @Override
    protected int getRootView() {
        return R.layout.act_customer_detail;
    }

    @Override
    protected void init() {
        calendar = Calendar.getInstance();

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        Customer c = test();
        layout.setTitle(c.getName() + "个人详情");

        mTies.get(0).setText(c.getName());
        mTies.get(1).setText(c.getPhone());
        mTies.get(2).setText(c.getSex());
        mTies.get(3).setText(c.getHeight() + "cm");
        mTies.get(4).setText(c.getWeight() + "kg");
        mTies.get(5).setText(c.getBirthday());
        mTies.get(6).setText(c.getLunarBirthday());
        mTies.get(7).setText(c.getWeddingDay());
        mTies.get(8).setText(c.getSkinType());
        mTies.get(9).setText(c.getNursingNeeds());
        mTies.get(10).setText(c.getAvailableTime());
        mTies.get(11).setText(c.getComment());

        editable(false);
    }

    @Override
    public void onBackPressed() {
        if (mIsEditable) {
            // TODO: 2019/6/7 如果有修改未保存,提示

            mIsEditable = false;
            return;
        }
        super.onBackPressed();
    }

    private void editable(boolean b) {
        for (TextInputEditText mTY : mTies) {
            mTY.setEnabled(b);
            if (b) {
                // 编辑模式
                mTY.setTextColor(getResources().getColor(R.color.colorAccent));
            } else {
                // 查看模式
                mTY.setTextColor(getResources().getColor(R.color.primary_text));
                mTY.setHighlightColor(Color.GREEN);
            }
        }
    }

    private Customer test() {
        Customer c = new Customer();
        c.setName("花花");
        c.setPhone("15818697500");
        c.setSex("女");
        c.setHeight(160);
        c.setWeight(45);
        c.setBirthday("2000-01-01");
        c.setLunarBirthday("农历元旦");
        c.setWeddingDay("2019-10-01");
        c.setSkinType("油性皮肤");
        c.setNursingNeeds("面部保养");
        c.setAvailableTime("周天上午");
        c.setComment("");

        return c;
    }

    @OnClick({R.id.fab_customer_detail, R.id.mb_cost, R.id.tiet_birthday})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_customer_detail:
                if (mIsEditable) {
                    // 提交数据，设置为查看状态
                    editable(false);
                    Snackbar.make(view, "修改成功", Snackbar.LENGTH_SHORT).show();
                    mFab.setImageResource(R.drawable.ic_edit);
                } else {
                    // 设置为编辑状态
                    editable(true);
                    mFab.setImageResource(R.drawable.ic_done);
                }
                mIsEditable = !mIsEditable;
                break;
            case R.id.mb_cost:
                // 充值，消费详细记录
                Snackbar.make(view, "消费详细记录", Snackbar.LENGTH_SHORT).show();

                break;
            case R.id.tiet_birthday:
                // 选择日期
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view1, year, monthOfYear, dayOfMonth) -> {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, monthOfYear);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    String date = DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar.getTime());
                    ToastUtil.showShort(date);
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();

                break;
        }
    }
}
