package com.major.beauty.ui;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.major.base.rx.rxtask.RxTask;
import com.major.beauty.R;
import com.major.beauty.base.BaseActivity;
import com.major.beauty.bean.Customer;
import com.major.beauty.dao.CustomerDao;
import com.major.beauty.dialog.SearchCustomDialog;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @desc: 护理划卡界面
 * @author: Major
 * @since: 2019/6/7 22:12
 */
public class PayActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tiet_customer_info)
    TextInputEditText mCustomerInfo;

    private CustomerDao mCustomerDao = new CustomerDao();
    private Customer mCustomer;

    @Override
    protected int getRootView() {
        return R.layout.act_pay;
    }

    @Override
    protected void init() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("护理划卡");

        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    @OnClick({R.id.mb_customer_query, R.id.mb_pay_confirm})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.mb_customer_query:
                SearchCustomDialog search = new SearchCustomDialog(this);
                search.setResultListener(customer -> {
                    mCustomerInfo.setText(customer.getName());
                    mCustomer = customer;
                    // 查询余额

                });
                search.show();
                break;
            case R.id.mb_pay_confirm:
                Snackbar snackbar = Snackbar.make(view, "提交成功", Snackbar.LENGTH_SHORT);
                snackbar.getView().setBackgroundColor(Color.BLUE);
                snackbar.show();
                RxTask.doOnUIThreadDelay(this::finish, 2, TimeUnit.SECONDS);
                break;
            default:
                break;
        }
    }
}
