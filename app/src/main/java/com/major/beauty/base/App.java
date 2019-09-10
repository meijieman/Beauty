package com.major.beauty.base;

import android.app.Application;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBaseConfig;
import com.major.base.CommonConfig;
import com.major.base.crash.CrashHandler;
import com.major.beauty.bean.Avatar;

/**
 * @desc: TODO
 * @author: Major
 * @since: 2019/2/17 7:54
 */
public class App extends Application {

    private static App sApp;
    private LiteOrm mLiteOrm;

    public static App getInstance() {
        return sApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;

        CrashHandler.getInstance().init(this, "crash", true);
        DataBaseConfig config = new DataBaseConfig(this, "mei_beauty.db");
        config.dbVersion = 1;
//        mLiteOrm = LiteOrm.newSingleInstance(config);
        // 有级联操作，需要使用这个
        mLiteOrm = LiteOrm.newCascadeInstance(config);
        mLiteOrm.setDebugged(true);

        new CommonConfig.Build()
                .setApplication(this)
                .setLogUtil("tag_beauty", true, false)
                .build();

        test();
    }

    public Avatar mAvatar;

    private void test() {
        // 用户角色
        mAvatar = new Avatar();
        mAvatar.setName("major");
        mAvatar.setGrade(1);

        // 创建用户数据

        // 创建产品数据

        // 创建项目数据

    }

    public LiteOrm getLiteOrm(){
        return mLiteOrm;
    }
}
