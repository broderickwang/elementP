package com.xuanqi.he.o2omvp.activity.login;


import android.app.Activity;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.activity.findpassword.FindPasswordActivity;
import com.xuanqi.he.o2omvp.activity.register.RegisterActivity;
import com.xuanqi.he.o2omvp.mvp.MVPBaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class LoginActivity extends MVPBaseActivity<LoginContract.View, LoginPresenter> implements LoginContract.View, View.OnTouchListener {

    @BindView(R.id.m_title)
    TextView mTitle;
    @BindView(R.id.m_back)
    ImageView mBack;
    @BindView(R.id.m_right)
    TextView mRight;
    @BindView(R.id.m_edit_phone)
    EditText mEditPhone;
    @BindView(R.id.m_edit_password)
    EditText mEditPassword;
    @BindView(R.id.m_check_box)
    CheckBox mCheckBox;
    @BindView(R.id.m_forget)
    TextView mForget;
    @BindView(R.id.m_confirm)
    TextView mConfirm;
    @BindView(R.id.m_we_chat)
    ImageView mWeChat;
    @BindView(R.id.m_sina)
    ImageView mSina;
    @BindView(R.id.m_qq)
    ImageView mQq;
    @BindView(R.id.activity_login)
    LinearLayout mRoot;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
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
    }

    @Override
    protected void initData() {
        mRight.setVisibility(View.VISIBLE);
        mTitle.setText("登录");
    }

    @OnClick({R.id.m_back, R.id.m_right, R.id.m_forget, R.id.m_confirm, R.id.m_we_chat,
            R.id.m_sina, R.id.m_qq})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.m_back:
                finish();
                break;
            case R.id.m_right:
                //注册
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.m_forget:
                //忘记密码
                startActivity(new Intent(this, FindPasswordActivity.class));
                break;
            case R.id.m_confirm:
                //立即登录
                break;
            case R.id.m_we_chat:
                //微信登录
                break;
            case R.id.m_sina:
                //新浪登录
                break;
            case R.id.m_qq:
                //qq登录
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
        }
        return false;
    }

    @Override
    public int[] hideSoftByEditViewIds() {
        return new int[]{R.id.m_edit_phone, R.id.m_edit_password};
    }
}
