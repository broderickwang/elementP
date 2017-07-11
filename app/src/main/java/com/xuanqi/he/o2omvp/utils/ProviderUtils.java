package com.xuanqi.he.o2omvp.utils;

import android.content.Context;

/**
 * Created by Administrator on 2017/5/8.
 */

public class ProviderUtils {

    public static String getFileProviderName(Context context) {
        return context.getPackageName() + ".provider";
    }
}
