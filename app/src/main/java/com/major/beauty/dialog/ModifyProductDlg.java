package com.major.beauty.dialog;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.WindowManager;

import com.major.base.log.LogUtil;
import com.major.base.util.CommonUtil;
import com.major.beauty.R;
import com.major.beauty.bean.Product;
import com.major.beauty.dao.ProductDao;
import com.major.beauty.ui.ProductsActivity;
import com.major.beauty.ui.vm.CustomersVM;
import com.major.beauty.ui.vm.ProductsVM;

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
    private Context mContext;

    public ModifyProductDlg(Context context) {
        super(context);
        mContext = context;
    }

    public ModifyProductDlg(Context context, Product product) {
        this(context);
        mProduct = product;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dlg_modify_product);
        ButterKnife.bind(this);
        setCanceledOnTouchOutside(false);

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE; // 显示dialog的时候,就显示软键盘
        params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND; // 就是这个属性导致不能获取焦点,默认的是FLAG_NOT_FOCUSABLE,故名思义不能获取输入焦点,
        getWindow().setAttributes(params);
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
                    mTies.get(0).setError("产品名称不能为空");
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

                    MutableLiveData<Integer> updateLD = ViewModelProviders.of((ProductsActivity) mContext)
                            .get(ProductsVM.class).getUpdate();
                    updateLD.postValue(CustomersVM.ADD);
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
