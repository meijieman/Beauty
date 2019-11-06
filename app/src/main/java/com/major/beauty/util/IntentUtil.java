package com.major.beauty.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * @desc: TODO
 * @author: Major
 * @since: 2019/11/6 21:31
 */
public class IntentUtil {

    /**
     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
     *
     * @param phone 电话号码
     */
    public static void callPhone(Context context, String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phone);
        intent.setData(data);
        context.startActivity(intent);
    }
}
