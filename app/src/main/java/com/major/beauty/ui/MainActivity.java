package com.major.beauty.ui;

import android.Manifest;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBaseConfig;
import com.major.base.log.LogUtil;
import com.major.beauty.R;
import com.major.beauty.base.App;
import com.major.beauty.base.BaseActivity;

import java.io.File;

import butterknife.BindView;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends BaseActivity {

    @BindView(R.id.nav_view)
    BottomNavigationView mNavView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private DailyFgt mDailyFgt;
    private AnalyzeFgt mAnalyzeFgt;
    private ManagementFgt mManagementFgt;

    @Override
    protected int getRootView() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

//        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        mNavView.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);

        mNavView.setSelectedItemId(R.id.navigation_home);

//        skipIntent(CustomersActivity.class);
//        skipIntent(CustomerDetailActivity.class);
//        skipIntent(LoginActivity.class);
//        findViewById(R.id.navigation_notifications).performClick();
//        skipIntent(ItemDetailActivity.class); // 自定义控件

        requestPerms();
    }

    @AfterPermissionGranted(100)
    private void requestPerms() {
        LogUtil.i("requestPerms");
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            // 初始化数据库
            LogUtil.i("requestPerms 111");

            String path = Environment.getExternalStorageDirectory() + File.separator + "beauty" + File.separator;
            DataBaseConfig config = new DataBaseConfig(this, path + "mei_beauty.db");
            config.dbVersion = 1;
//        mLiteOrm = LiteOrm.newSingleInstance(config);
            // 有级联操作，需要使用这个
            LiteOrm mLiteOrm = LiteOrm.newCascadeInstance(config);
            mLiteOrm.setDebugged(true);

            App.getInstance().setLiteOrm(mLiteOrm);
            LogUtil.i("requestPerms 222 " + config);
        } else {
            LogUtil.i("requestPerms 333");
            EasyPermissions.requestPermissions(this, "本应用需要获取写外存的权限，请允许！", 100, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
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
