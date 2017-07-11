package com.xuanqi.he.o2omvp.modlues.personal.activity.accountsettings;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.modlues.personal.activity.modifyloginpassword.ModifyLoginPasswordActivity;
import com.xuanqi.he.o2omvp.modlues.personal.activity.settingpaymentpassword.SettingPaymentPasswordActivity;
import com.xuanqi.he.o2omvp.modlues.personal.activity.usercertification.UserCertificationActivity;
import com.xuanqi.he.o2omvp.mvp.MVPBaseActivity;
import com.xuanqi.he.o2omvp.widget.CircleImageView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class AccountSettingsActivity extends MVPBaseActivity<AccountSettingsContract.View, AccountSettingsPresenter> implements AccountSettingsContract.View {

    @BindView(R.id.m_title)
    TextView mTitle;
    @BindView(R.id.m_back)
    ImageView mBack;
    @BindView(R.id.m_icon)
    CircleImageView mIcon;
    @BindView(R.id.m_nickname)
    TextView mNickname;
    @BindView(R.id.m_modify_nickname)
    RelativeLayout mModifyNickname;
    @BindView(R.id.m_gender)
    TextView mGender;
    @BindView(R.id.m_modify_gender)
    RelativeLayout mModifyGender;
    @BindView(R.id.m_signature)
    TextView mSignature;
    @BindView(R.id.m_modify_signature)
    RelativeLayout mModifySignature;
    @BindView(R.id.m_real_name_status)
    TextView mRealNameStatus;
    @BindView(R.id.m_real_name)
    RelativeLayout mRealName;
    @BindView(R.id.m_phone_number_status)
    TextView mPhoneNumberStatus;
    @BindView(R.id.m_phone_number)
    RelativeLayout mPhoneNumber;
    @BindView(R.id.m_we_chat_status)
    TextView mWeChatStatus;
    @BindView(R.id.m_we_chat)
    RelativeLayout mWeChat;
    @BindView(R.id.m_qq_status)
    TextView mQqStatus;
    @BindView(R.id.m_qq)
    RelativeLayout mQq;
    @BindView(R.id.m_sina_status)
    TextView mSinaStatus;
    @BindView(R.id.m_sina)
    RelativeLayout mSina;
    @BindView(R.id.m_login_password)
    RelativeLayout mLoginPassword;
    @BindView(R.id.m_payment_password_status)
    TextView mPaymentPasswordStatus;
    @BindView(R.id.m_payment_password)
    RelativeLayout mPaymentPassword;
    @BindView(R.id.activity_account_settings)
    LinearLayout mRoot;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_account_settings;
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    protected void initData() {
        mTitle.setText("账户设置");
    }

    @OnClick({R.id.m_back, R.id.m_modify_nickname, R.id.m_modify_gender,
            R.id.m_modify_signature, R.id.m_real_name, R.id.m_phone_number,
            R.id.m_we_chat, R.id.m_qq, R.id.m_sina, R.id.m_login_password,
            R.id.m_payment_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.m_back:
                finish();
                break;
            case R.id.m_modify_nickname:
                break;
            case R.id.m_modify_gender:
                break;
            case R.id.m_modify_signature:
                break;
            case R.id.m_real_name:
                startActivity(new Intent(this,UserCertificationActivity.class));
                break;
            case R.id.m_phone_number:
                break;
            case R.id.m_we_chat:
                break;
            case R.id.m_qq:
                break;
            case R.id.m_sina:
                break;
            case R.id.m_login_password:
                startActivity(new Intent(this,ModifyLoginPasswordActivity.class));
                break;
            case R.id.m_payment_password:
                startActivity(new Intent(this,SettingPaymentPasswordActivity.class));
                break;
        }
    }
}
