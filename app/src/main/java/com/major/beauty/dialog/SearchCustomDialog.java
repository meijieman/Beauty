package com.major.beauty.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;

import com.major.base.log.LogUtil;
import com.major.base.rx.rxtask.RxTask;
import com.major.base.util.CommonUtil;
import com.major.beauty.R;
import com.major.beauty.adapter.SearchCustomerAdapter;
import com.major.beauty.bean.Customer;
import com.major.beauty.dao.CustomerDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @desc: 根据顾客名称，电话号码搜索
 * @author: Major
 * @since: 2019/11/2 9:09
 */
public class SearchCustomDialog extends AlertDialog {

    @BindView(R.id.tet_search_text)
    TextInputEditText mSearchText;
    @BindView(R.id.rv_item)
    RecyclerView mRecyclerView;

    private CustomerDao mDao = new CustomerDao();
    private SearchCustomerAdapter mAdapter;

    public SearchCustomDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dlg_search_custom);
        ButterKnife.bind(this);
        setCanceledOnTouchOutside(false);

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE; // 显示dialog的时候,就显示软键盘
        params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND; // 就是这个属性导致不能获取焦点,默认的是FLAG_NOT_FOCUSABLE,故名思义不能获取输入焦点,
        getWindow().setAttributes(params);

        mAdapter = new SearchCustomerAdapter(getContext());
        mRecyclerView.setAdapter(mAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);

        mAdapter.setOnItemClickListener((pos, bean, view) -> {
            if (mListener != null) {
                mListener.onResult(bean);
            }
            dismiss();
        });

        RxTask.doTask(new RxTask.Task<List<Customer>>() {
            @Override
            public List<Customer> onIOThread() {
                return mDao.query();
            }

            @Override
            public void onUIThread(List<Customer> customers) {
                if (CommonUtil.isEmpty(customers)) {
                    LogUtil.d("customers is empty.");
                } else {
                    mAdapter.setData(customers);
                }
            }
        });
    }

    @OnClick({R.id.mb_dialog_commit})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.mb_dialog_commit:
                RxTask.doTask(new RxTask.Task<List<Customer>>() {
                    @Override
                    public List<Customer> onIOThread() {
                        String text = mSearchText.getText().toString();
                        if (CommonUtil.isEmpty(text)) {
                            return mDao.query();
                        }
                        return mDao.queryByNameOrPhone(text);
                    }

                    @Override
                    public void onUIThread(List<Customer> customers) {
                        if (CommonUtil.isEmpty(customers)) {
                            LogUtil.d("customers is empty.");
                            mSearchText.setError("搜索结果为空", null);
                        }
                        mAdapter.setData(customers);
                    }
                });
                break;
            default:
                break;
        }
    }

    private ResultListener mListener;

    public void setResultListener(ResultListener listener) {
        mListener = listener;
    }

    public interface ResultListener {
        void onResult(Customer customer);
    }
}
