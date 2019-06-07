package com.major.beauty.ui;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.major.base.rx.rxtask.RxTask;
import com.major.beauty.R;
import com.major.beauty.base.BaseActivity;

import java.util.concurrent.TimeUnit;

import butterknife.OnClick;

/**
 * @desc: 销售登记界面
 * @author: Major
 * @since: 2019/6/7 22:12
 */
public class SalesActivity extends BaseActivity {

    @Override
    protected int getRootView() {
        return R.layout.act_sales;
    }

    @Override
    protected void init() {

    }

    @OnClick({R.id.mb_sales_item, R.id.mb_sales_product, R.id.mb_pay_confirm})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.mb_sales_item:
                // 弹出多选对话框
                String[] singleChoiceItems = {"纽约", "洛杉矶", "旧金山", "巴黎", "伦敦"};
                boolean[] checkedItems = {true, false, false, false, false};
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("选择项目")
                        .setMultiChoiceItems(singleChoiceItems, checkedItems, null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setNegativeButton("取消", null)
                        .create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();

                break;
            case R.id.mb_sales_product:

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
