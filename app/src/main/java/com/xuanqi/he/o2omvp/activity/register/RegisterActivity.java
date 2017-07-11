package com.xuanqi.he.o2omvp.activity.register;


import android.app.Activity;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.mvp.MVPBaseActivity;
import com.xuanqi.he.o2omvp.utils.LogUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class RegisterActivity extends MVPBaseActivity<RegisterContract.View, RegisterPresenter> implements RegisterContract.View, View.OnTouchListener {

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
    @BindView(R.id.m_check_box)
    CheckBox mCheckBox;
    @BindView(R.id.m_terms_of_service)
    TextView mTermsOfService;
    @BindView(R.id.m_confirm)
    TextView mConfirm;
    @BindView(R.id.activity_register)
    LinearLayout mRoot;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
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
        initProperty();
    }

    private void initProperty() {
        mTitle.setText("注册");
        String text = "我已阅读并接受《社区O2O注册服务条款》";
        SpannableString mSpan = new SpannableString(text);
        mSpan.setSpan(new ClickableSpan() {
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(ContextCompat.getColor(RegisterActivity.this, R.color.toolbar_color));
                ds.setUnderlineText(false);
            }

            @Override
            public void onClick(View view) {
                LogUtils.e("服务条款");
            }
        }, 7, text.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        mTermsOfService.setText(mSpan);
        mTermsOfService.setMovementMethod(LinkMovementMethod.getInstance());
        mTermsOfService.setHighlightColor(Color.TRANSPARENT);
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
                //注册
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
