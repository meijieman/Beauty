package com.major.beauty.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.major.base.log.LogUtil;
import com.major.base.util.CommonUtil;
import com.major.base.util.KeyboardUtil;
import com.major.beauty.R;
import com.major.beauty.base.BaseActivity;
import com.major.beauty.base.Constant;
import com.major.beauty.bean.Customer;
import com.major.beauty.dao.CustomerDao;
import com.major.beauty.ui.vm.CustomersVM;

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
//            R.id.tet_sex, R.id.tet_height,
//            R.id.tet_weight, R.id.tiet_birthday,
//            R.id.tet_lunar_birthday, R.id.tet_wedding_day,
//            R.id.tet_skin_type, R.id.tet_nursing_needs,
//            R.id.tet_available_time,
            R.id.tet_comment})
    List<TextInputEditText> mTies;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ctl_customer_detail)
    CollapsingToolbarLayout layout;
    @BindView(R.id.fab_customer_detail)
    FloatingActionButton mFab;

    private boolean mIsEditMode; // 是否是编辑状态
    private Calendar calendar;
    private Customer mCustomer;
    private CustomerDao mDao = new CustomerDao();

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

        long cid = getIntent().getLongExtra(Constant.EXTRA_CID, -1L);
        if (cid == -1) {
            // 新增
            mIsEditMode = true;
            editable(true);

            layout.setTitle("个人档案");

        } else {
            mCustomer = mDao.queryById(cid, Customer.class);
            if (mCustomer == null) {
                LogUtil.e("query error " + cid);
                return;
            }
            layout.setTitle(mCustomer.getName() + "个人档案");

            mTies.get(0).setText(mCustomer.getName());
            mTies.get(1).setText(mCustomer.getPhone());
//            mTies.get(2).setText(mCustomer.getSex());
//            mTies.get(3).setText(mCustomer.getHeight() + "cm");
//            mTies.get(4).setText(mCustomer.getWeight() + "kg");
//            mTies.get(5).setText(mCustomer.getBirthday());
//            mTies.get(6).setText(mCustomer.getLunarBirthday());
//            mTies.get(7).setText(mCustomer.getWeddingDay());
//            mTies.get(8).setText(mCustomer.getSkinType());
//            mTies.get(9).setText(mCustomer.getNursingNeeds());
//            mTies.get(10).setText(mCustomer.getAvailableTime());
            mTies.get(2).setText(mCustomer.getComment());

            editable(false);
        }
    }

    @Override
    public void onBackPressed() {
        if (mIsEditMode) {
            // TODO: 2019/6/7 如果有修改未保存,提示

            mIsEditMode = false;
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
                mFab.setImageResource(R.drawable.ic_done);

            } else {
                // 查看模式
                mTY.setTextColor(getResources().getColor(R.color.primary_text));
                mTY.setHighlightColor(Color.GREEN);
                mFab.setImageResource(R.drawable.ic_edit);
            }
        }
    }

    @OnClick({R.id.fab_customer_detail, R.id.mb_cost/*, R.id.tiet_birthday*/})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_customer_detail:
                if (mIsEditMode) {
                    // 提交数据
                    KeyboardUtil.hideKeyboard(this, view);

                    String tip = "修改成功";
                    if (mCustomer == null) {
                        mCustomer = new Customer("major");
                        tip = "添加成功";
                    }

                    String name = mTies.get(0).getText().toString();
                    if (CommonUtil.isEmpty(name)) {
                        mTies.get(0).setError("姓名不能为空");
                        mTies.get(0).requestFocus();
                        return;
                    }
                    mCustomer.setName(name);
                    String phone = mTies.get(1).getText().toString();
                    if (CommonUtil.isEmpty(phone)) {
                        mTies.get(1).setError("电话不能为空");
                        mTies.get(1).requestFocus();
                        return;
                    }
                    mCustomer.setPhone(phone);

                    long update = mDao.insertOrUpdate(mCustomer);
                    LogUtil.i("update " + update);
                    if (update != -1) {
                        Snackbar.make(view, tip, Snackbar.LENGTH_SHORT).show();
                        editable(false);

                        // 如果是新增，通知其他界面更新
                        CustomersVM.SingletonLiveData<Integer> updateLD = ViewModelProviders.of(this).get(CustomersVM.class).getUpdate();
                        updateLD.postValue(CustomersVM.ADD);

                    }
                } else {
                    // 设置为编辑状态
                    editable(true);
                }

                mIsEditMode = !mIsEditMode;
                break;
            case R.id.mb_cost:
                // 充值，消费详细记录
                Snackbar.make(view, "消费详细记录", Snackbar.LENGTH_SHORT).show();

                break;
//            case R.id.tiet_birthday:
//                // 选择日期
//                DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view1, year, monthOfYear, dayOfMonth) -> {
//                    calendar.set(Calendar.YEAR, year);
//                    calendar.set(Calendar.MONTH, monthOfYear);
//                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//                    String date = DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar.getTime());
//                    ToastUtil.showShort(date);
//                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
//                datePickerDialog.show();
//
//                break;
        }
    }
}
