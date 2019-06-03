package com.major.beauty.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @desc: SharedPreferences 工具类
 * @author: Major
 * @since: 2019/3/2 12:05
 */
public class SPUtil {

    public static SharedPreferences sSp;

    public static void init(Context context){
        sSp = context.getSharedPreferences("ework", Context.MODE_PRIVATE);
    }

    public static String getString(String key){
        checkNotNull();
        return sSp.getString(key, null);
    }

    public static void putString(String key, String val) {
        checkNotNull();
        sSp.edit().putString(key, val).commit();
    }



    private static void checkNotNull(){
        if (sSp == null) {
            throw new RuntimeException("please invoke init() first");
        }
    }
}
