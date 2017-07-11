package com.xuanqi.he.o2omvp.baidu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.xuanqi.he.o2omvp.utils.LogUtils;

/**
 * @author Created by He on 2017/6/27.
 * @description
 */

public class BaiDuSDKReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String s = intent.getAction();
        LogUtils.e("onReceive->" + s);
        if (s.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)) {
            Toast.makeText(context, "key 验证出错! 错误码 :" + intent.getIntExtra
                    (SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE, 0)
                    + " ; 请在 AndroidManifest.xml 文件中检查 key 设置", Toast.LENGTH_SHORT).show();
        } else if (s.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK)) {
            Toast.makeText(context, "key 验证成功! 功能可以正常使用", Toast.LENGTH_SHORT).show();
        } else if (s.equals(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR)) {
            Toast.makeText(context, "网络出错", Toast.LENGTH_SHORT).show();
        }
    }
}
