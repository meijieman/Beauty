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
import com.major.base.util.ToastUtil;
import com.major.beauty.R;
import com.major.beauty.bean.Item;
import com.major.beauty.dao.ItemDao;
import com.major.beauty.ui.ItemsActivity;
import com.major.beauty.ui.vm.CustomersVM;
import com.major.beauty.ui.vm.ItemsVM;

import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @desc: TODO
 * @author: Major
 * @since: 2019/9/9 23:15
 */
public class ModifyItemDlg extends AlertDialog {

    @BindViews({R.id.tet_name, R.id.tet_price, R.id.tet_unit,
            R.id.tet_comment})
    List<TextInputEditText> mTies;

    private ItemDao mDao = new ItemDao();
    private Context mContext;
    private Item mItem;

    public ModifyItemDlg(Context context) {
        super(context);
        mContext = context;
    }

    public ModifyItemDlg(Context context, Item item) {
        this(context);
        mItem = item;
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
        if (mItem != null) {
            mTies.get(0).setText(mItem.getName());

        }

        mTies.get(0).requestFocus();
    }

    @OnClick({R.id.mb_dialog_commit})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.mb_dialog_commit:
                String name = mTies.get(0).getText().toString();
                String comment = mTies.get(3).getText().toString();
                if (CommonUtil.isEmpty(name)) {
                    ToastUtil.showShort("产品名称不能为空");
                    return;
                }
                if (mItem == null) {
                    // 新增
                    mItem = new Item();
                }

                mItem.setName(name);

                long rst = mDao.insertOrUpdate(mItem);
                LogUtil.i("rst " + rst);
                if (rst != -1) {
                    dismiss();

                    MutableLiveData<Integer> updateLD = ViewModelProviders.of((ItemsActivity) mContext)
                            .get(ItemsVM.class).getUpdate();
                    updateLD.postValue(CustomersVM.ADD);
                }
                break;
            default:
                break;
        }
    }
}
