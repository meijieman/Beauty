package com.major.beauty.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Desc: TODO
 * <p>
 * Author: meijie
 * PackageName: com.major.beauty.util
 * ProjectName: Beauty
 * Date: 2019/10/28 10:42
 */
public class TimeUtil {

    private static final DateFormat DEFAULT_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());

    public static String format(long millis) {
        return DEFAULT_FORMAT.format(new Date(millis));
    }
}
