package com.major.beauty.ui;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.major.beauty.R;
import com.major.beauty.base.BaseActivity;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.nav_view)
    BottomNavigationView mNavView;

    private DailyFgt mDailyFgt;
    private AnalyzeFgt mAnalyzeFgt;
    private ManagementFgt mManagementFgt;

    @Override
    protected int getRootView() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        mNavView.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);

        mNavView.setSelectedItemId(R.id.navigation_home);

//        skipIntent(CustomersActivity.class);
        skipIntent(CustomerDetailActivity.class);
//        skipIntent(LoginActivity.class);
    }

    private boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (mDailyFgt != null) {
            transaction.hide(mDailyFgt);
        }
        if (mAnalyzeFgt != null) {
            transaction.hide(mAnalyzeFgt);
        }
        if (mManagementFgt != null) {
            transaction.hide(mManagementFgt);
        }

        switch (item.getItemId()) {
            case R.id.navigation_home: {
                if (mDailyFgt == null) {
                    mDailyFgt = new DailyFgt();
                    transaction.add(R.id.fl_container, mDailyFgt);
                }
                transaction.show(mDailyFgt);
            }
            break;
            case R.id.navigation_dashboard: {
                if (mManagementFgt == null) {
                    mManagementFgt = new ManagementFgt();
                    transaction.add(R.id.fl_container, mManagementFgt);
                }
                transaction.show(mManagementFgt);
            }
            break;
            case R.id.navigation_notifications: {
                if (mAnalyzeFgt == null) {
                    mAnalyzeFgt = new AnalyzeFgt();
                    transaction.add(R.id.fl_container, mAnalyzeFgt);
                }
                transaction.show(mAnalyzeFgt);
            }
            break;
        }
        transaction.commit();

        return true;
    }
}
