package com.xuanqi.he.o2omvp.modlues.personal.activity.bindphonenumber;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
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

public class BindPhoneNumberActivity extends MVPBaseActivity<BindPhoneNumberContract.View, BindPhoneNumberPresenter> implements BindPhoneNumberContract.View, View.OnTouchListener {

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
    @BindView(R.id.m_confirm)
    TextView mConfirm;
    @BindView(R.id.m_description)
    TextView mDescription;
    @BindView(R.id.activity_bind_phone_number)
    LinearLayout mRoot;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bind_phone_number;
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mEditPhone.setOnTouchListener(this);
        mEditCode.setOnTouchListener(this);
    }

    @Override
    protected void initData() {
        mTitle.setText("绑定手机号");
        SpannableString mSpan = new SpannableString("温馨提示：未注册的手机号绑定后将默认注册，手机号即为账号。");
        mSpan.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.toolbar_color)),
                0, 5, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        mDescription.setText(mSpan);
    }

    @OnClick({R.id.m_back, R.id.m_get_code, R.id.m_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.m_back:
                finish();
                break;
            case R.id.m_get_code:
                break;
            case R.id.m_confirm:
                break;
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (view.getId()) {
            case R.id.m_edit_phone:
                mEditPhone.setCursorVisible(true);
                break;
            case R.id.m_edit_code:
                mEditCode.setCursorVisible(true);
                break;
        }
        return false;
    }

    @Override
    public int[] hideSoftByEditViewIds() {
        return new int[]{R.id.m_edit_phone, R.id.m_edit_code};
    }
}
