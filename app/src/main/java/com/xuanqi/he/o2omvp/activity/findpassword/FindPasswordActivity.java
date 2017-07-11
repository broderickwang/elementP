package com.xuanqi.he.o2omvp.activity.findpassword;


import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.mvp.MVPBaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class FindPasswordActivity extends MVPBaseActivity<FindPasswordContract.View, FindPasswordPresenter> implements FindPasswordContract.View, View.OnTouchListener {

    @BindView(R.id.m_title)
    TextView mTitle;
    @BindView(R.id.m_back)
    ImageView mBack;
    @BindView(R.id.m_edit_phone)
    EditText mEditPhone;
    @BindView(R.id.m_edit_code)
    EditText mEditCode;
    @BindView(R.id.m_get_code)
    TextView mGetCode;
    @BindView(R.id.m_edit_password)
    EditText mEditPassword;
    @BindView(R.id.m_confirm)
    TextView mConfirm;
    @BindView(R.id.activity_find_password)
    LinearLayout mRoot;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_find_password;
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mEditPhone.setOnTouchListener(this);
        mEditPassword.setOnTouchListener(this);
        mEditCode.setOnTouchListener(this);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.m_back, R.id.m_get_code, R.id.m_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.m_back:
                finish();
                break;
            case R.id.m_get_code:
                //获取验证码
                break;
            case R.id.m_confirm:
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.m_edit_phone:
                mEditPhone.setCursorVisible(true);
                break;
            case R.id.m_edit_password:
                mEditPassword.setCursorVisible(true);
                break;
            case R.id.m_edit_code:
                mEditCode.setCursorVisible(true);
                break;
        }
        return false;
    }

    @Override
    public int[] hideSoftByEditViewIds() {
        return new int[]{R.id.m_edit_phone, R.id.m_edit_password, R.id.m_edit_code};
    }
}
