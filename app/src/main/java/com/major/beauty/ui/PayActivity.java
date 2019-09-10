package com.major.beauty.ui;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.major.base.rx.rxtask.RxTask;
import com.major.base.util.ToastUtil;
import com.major.beauty.R;
import com.major.beauty.base.BaseActivity;
import com.major.beauty.bean.Customer;
import com.major.beauty.dao.CustomerDao;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @desc: 护理划卡界面
 * @author: Major
 * @since: 2019/6/7 22:12
 */
public class PayActivity extends BaseActivity {

    @BindView(R.id.tiet_pay)
    TextInputEditText mPay;


    private CustomerDao mCustomerDao = new CustomerDao();

    @Override
    protected int getRootView() {
        return R.layout.act_pay;
    }

    @Override
    protected void init() {

    }

    @OnClick({R.id.mb_pay_query, R.id.mb_pay_confirm})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.mb_pay_query:
                String text = mPay.getText().toString().trim();

                List<Customer> customers = mCustomerDao.queryByNameOrPhone(text);
                ToastUtil.showLong("" + customers);


                // 弹出单选对话框
                String[] singleChoiceItems = {"纽约", "洛杉矶", "旧金山", "巴黎", "伦敦"};
                int itemSelected = 0;
                new AlertDialog.Builder(this)
                        .setTitle("单选")
                        .setSingleChoiceItems(singleChoiceItems, itemSelected, (dialogInterface, i) -> {
                            dialogInterface.dismiss();
                            ToastUtil.showShort(singleChoiceItems[i]);
                        })
                        .setNegativeButton("cancel", null)
                        .show();
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
