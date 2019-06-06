package com.major.beauty.base;

import android.app.Application;

import com.major.base.CommonConfig;
import com.major.base.crash.CrashHandler;

/**
 * @desc: TODO
 * @author: Major
 * @since: 2019/2/17 7:54
 */
public class App extends Application {

    private static App sApp;

    public static App getInstance() {
        return sApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;

        CrashHandler.getInstance().init(this, "crash", true);
        new CommonConfig.Build()
                .setApplication(this)
                .setLogUtil("tag_beauty", true, false)
                .build();
    }
}
