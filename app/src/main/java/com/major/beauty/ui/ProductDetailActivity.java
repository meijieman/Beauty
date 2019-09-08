package com.major.beauty.ui;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.major.base.log.LogUtil;
import com.major.beauty.R;
import com.major.beauty.base.BaseActivity;
import com.major.beauty.base.Constant;
import com.major.beauty.bean.Product;
import com.major.beauty.dao.ProductDao;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;

/**
 * @desc: 项目详情
 * @author: Major
 * @since: 2019/6/7 23:42
 */
public class ProductDetailActivity extends BaseActivity {

    @BindViews({R.id.tet_name, R.id.tet_price, R.id.tet_unit,
            R.id.tet_comment})
    List<TextInputEditText> mTies;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ctl_product_detail)
    CollapsingToolbarLayout layout;
    @BindView(R.id.fab_product_detail)
    FloatingActionButton mFab;

    private ProductDao mDao = new ProductDao();

    @Override
    protected int getRootView() {
        return R.layout.act_product_detail;
    }

    @Override
    protected void init() {

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        mFab.setImageResource(R.drawable.fab_add);

        for (TextInputEditText tie : mTies) {
            tie.setEnabled(false);
            tie.setTextColor(getResources().getColor(R.color.primary_text));
            tie.setHighlightColor(Color.GREEN);
        }

        long pid = getIntent().getLongExtra(Constant.EXTRA_PID, -1L);
        if (pid != -1) {
            Product product = mDao.queryById(pid, Product.class);
            if (product != null) {
                mTies.get(0).setText(product.getName());
                mTies.get(1).setText(String.valueOf(product.getPrice()));
                mTies.get(2).setText(product.getUnit());
                mTies.get(3).setText(product.getComment());
            } else {
                LogUtil.e("data not found " + pid);
            }
        } else {
            // 新增模式

        }
    }

    @OnClick(R.id.fab_product_detail)
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_product_detail:
                // 编辑

                break;
            default:
                break;
        }
    }
}
