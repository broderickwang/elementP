package com.xuanqi.he.o2omvp.net;

/**
 * Created by ZengLingWen on 2017/6/11.
 */

public class Exceptions {
    public static void illegalArgument(String msg, Object... params)
    {
        throw new IllegalArgumentException(String.format(msg, params));
    }

}
