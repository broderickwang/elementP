package com.xuanqi.he.o2omvp.activity;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.activity.main.MainActivity;
import com.xuanqi.he.o2omvp.constant.CodeApi;
import com.xuanqi.he.o2omvp.utils.AppManager;
import com.xuanqi.he.o2omvp.utils.KeyBoardUtils;
import com.xuanqi.he.o2omvp.utils.LogUtils;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;
import com.yanzhenjie.permission.SettingDialog;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WelcomeActivity extends AppCompatActivity {

    @BindView(R.id.activity_welcome)
    RelativeLayout mRoot;
    private MyHandler mHandler;
    private String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA, Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS,
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.READ_PHONE_STATE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_welcome);
        AppManager.getAppManager().addActivity(this);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        if (KeyBoardUtils.hasNavigationBarShow(getWindowManager())) {
            int navigationBarHeight = KeyBoardUtils.getNavigationBarHeight(this);
            mRoot.setPadding(0, 0, 0, navigationBarHeight);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermission();
        } else {
            jumpToMain();
        }
    }

    private void requestPermission() {
        AndPermission.with(this)
                .requestCode(CodeApi.PERMISSION_REQUEST_CODE)
                .permission(Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA,
                        Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS,
                        Manifest.permission.READ_PHONE_STATE)
                .callback(this)
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                        AndPermission.rationaleDialog(WelcomeActivity.this, rationale)
                                .show();
                    }
                })
                .start();
    }

    @PermissionYes(CodeApi.PERMISSION_REQUEST_CODE)
    private void onSuccess(@NonNull List<String> grantedPermissions) {
        if (AndPermission.hasPermission(this, Arrays.asList(permissions))) {
            LogUtils.e("权限申请成功");
            jumpToMain();
        }
    }

    @PermissionNo(CodeApi.PERMISSION_REQUEST_CODE)
    private void onFailure(@NonNull List<String> deniedPermissions) {
        if (AndPermission.hasPermission(this, Arrays.asList(permissions))) {
            LogUtils.e("失败有权限");
            jumpToMain();
        } else if (AndPermission.hasAlwaysDeniedPermission(this, deniedPermissions)) {
            // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
            LogUtils.e("权限申请失败");
            SettingDialog dialog = AndPermission.defaultSettingDialog(this, CodeApi.SETTING_REQUEST_CODE);
            dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    LogUtils.e("点击了取消");
                    WelcomeActivity.this.finish();
                }
            });
            dialog.show();
        }
    }

    private void jumpToMain() {
        mHandler = new MyHandler(this);
        Message message = mHandler.obtainMessage();
        mHandler.sendMessageDelayed(message, 2000);
    }

    private static class MyHandler extends Handler {

        private WeakReference<Activity> mReference;

        public MyHandler(Activity activity) {
            mReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Activity activity = mReference.get();
            if (activity != null) {
                activity.startActivity(new Intent(activity, MainActivity.class));
                activity.finish();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CodeApi.SETTING_REQUEST_CODE){
            if (AndPermission.hasPermission(this, Arrays.asList(permissions))) {
                jumpToMain();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }
}
