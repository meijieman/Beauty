package com.major.beauty.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.major.base.log.LogUtil;
import com.major.base.util.CommonUtil;
import com.major.base.util.ToastUtil;
import com.major.beauty.R;
import com.major.beauty.bean.Product;
import com.major.beauty.dao.ProductDao;

import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @desc: 新增，修改 产品 dialog
 * @author: Major
 * @since: 2019/9/8 23:24
 */
public class ModifyProductDlg extends AlertDialog {

    @BindViews({R.id.tet_name, R.id.tet_price, R.id.tet_unit,
            R.id.tet_comment})
    List<TextInputEditText> mTies;

    private Product mProduct;
    private ProductDao mDao = new ProductDao();

    public ModifyProductDlg(Context context) {
        super(context);
    }

    public ModifyProductDlg(Context context, Product product) {
        this(context);
        mProduct = product;
    }

    public ModifyProductDlg(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dlg_modify_product);
        ButterKnife.bind(this);
        setCanceledOnTouchOutside(false);

        if (mProduct != null) {
            mTies.get(0).setText(mProduct.getName());
            mTies.get(1).setText(String.valueOf(mProduct.getPrice()));
            mTies.get(2).setText(mProduct.getUnit());
            mTies.get(3).setText(mProduct.getComment());
        }

        mTies.get(0).requestFocus();
    }

    @OnClick({R.id.mb_dialog_commit})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.mb_dialog_commit:
                String name = mTies.get(0).getText().toString();
                String price = mTies.get(1).getText().toString();
                String unit = mTies.get(2).getText().toString();
                String comment = mTies.get(3).getText().toString();
                if (CommonUtil.isEmpty(name)) {
                    ToastUtil.showShort("产品名称不能为空");
                    return;
                }
                if (mProduct == null) {
                    // 新增
                    mProduct = new Product();
                }

                mProduct.setName(name);
                mProduct.setPrice(toDouble(price));
                mProduct.setUnit(unit);
                mProduct.setComment(comment);

                long rst = mDao.insertOrUpdate(mProduct);
                LogUtil.i("rst " + rst);
                if (rst != -1) {
                    dismiss();
                }

                break;
            default:
                break;
        }
    }

    public static double toDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
