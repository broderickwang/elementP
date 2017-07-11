package com.xuanqi.he.o2omvp.base;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.utils.AppManager;
import com.xuanqi.he.o2omvp.utils.KeyBoardUtils;
import com.xuanqi.he.o2omvp.utils.StatusBarCompat;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/8.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private static BaseActivity instance;
    private String TAG = "BaseActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isCover()) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            if (Build.VERSION.SDK_INT >= 21) {
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
        }
        setContentView(getLayoutId());
        instance = this;
        AppManager.getAppManager().addActivity(this);
        ButterKnife.bind(this);

        /**
         * 沉浸式状态栏
         */
        if (!isCover()) {
            StatusBarCompat.compat(getActivity(), getStatusBarColor());
        }

        initEvent();
        initData();
    }

    public static BaseActivity getInstance() {
        return instance;
    }

    protected abstract int getLayoutId();

    protected abstract Activity getActivity();

    public boolean isCover() {
        return false;
    }

    /**
     * 监听事件
     */
    public void initEvent() {
    }

    /**
     * 初始化
     */
    protected abstract void initData();


    public int getStatusBarColor() {
        return ContextCompat.getColor(this, R.color.toolbar_color);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }

    /**
     * 点击软键盘其他区域隐藏软键盘
     *
     * @param ev
     * @return
     */
    /*@Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (KeyBoardUtils.isShouldHideInput(v, ev)) {
                if (KeyBoardUtils.hideInputMethod(this, v)) {
//                    return true; //隐藏键盘时，其他控件不响应点击事件==》注释则不拦截点击事件
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }*/

    /**
     * 清除editText的焦点
     *
     * @param v   焦点所在View
     * @param ids 输入框
     */
    public void clearViewFocus(View v, int... ids) {
        if (null != v && null != ids && ids.length > 0) {
            for (int id : ids) {
                if (v.getId() == id) {
                    v.clearFocus();
                    break;
                }
            }
        }
    }

    /**
     * 隐藏键盘
     *
     * @param v   焦点所在View
     * @param ids 输入框
     * @return true代表焦点在edit上
     */
    public boolean isFocusEditText(View v, int... ids) {
        if (v instanceof EditText) {
            EditText tmp_et = (EditText) v;
            for (int id : ids) {
                if (tmp_et.getId() == id) {
                    return true;
                }
            }
        }
        return false;
    }

    //是否触摸在指定view上面,对某个控件过滤
    public boolean isTouchView(View[] views, MotionEvent ev) {
        if (views == null || views.length == 0) return false;
        int[] location = new int[2];
        for (View view : views) {
            view.getLocationOnScreen(location);
            int x = location[0];
            int y = location[1];
            if (ev.getX() > x && ev.getX() < (x + view.getWidth())
                    && ev.getY() > y && ev.getY() < (y + view.getHeight())) {
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (isTouchView(filterViewByIds(), ev)) return super.dispatchTouchEvent(ev);
            if (hideSoftByEditViewIds() == null || hideSoftByEditViewIds().length == 0)
                return super.dispatchTouchEvent(ev);
            View v = getCurrentFocus();
            if (isFocusEditText(v, hideSoftByEditViewIds())) {
                //隐藏键盘
                ((EditText) v).setCursorVisible(false);
                KeyBoardUtils.hideInputForce(this);
                clearViewFocus(v, hideSoftByEditViewIds());
            }
        }
        return super.dispatchTouchEvent(ev);

    }

    /**
     * 传入EditText的Id
     * 没有传入的EditText不做处理
     *
     * @return id 数组
     */
    public int[] hideSoftByEditViewIds() {
        return null;
    }

    /**
     * 传入要过滤的View
     * 过滤之后点击将不会有隐藏软键盘的操作
     *
     * @return id 数组
     */
    public View[] filterViewByIds() {
        return null;
    }
}
