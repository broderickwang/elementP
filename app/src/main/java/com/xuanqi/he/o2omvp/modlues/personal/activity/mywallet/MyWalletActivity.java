package com.xuanqi.he.o2omvp.modlues.personal.activity.mywallet;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.modlues.personal.activity.accountrecharge.AccountRechargeActivity;
import com.xuanqi.he.o2omvp.modlues.personal.activity.rechargerecord.RechargeRecordActivity;
import com.xuanqi.he.o2omvp.mvp.MVPBaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MyWalletActivity extends MVPBaseActivity<MyWalletContract.View, MyWalletPresenter> implements MyWalletContract.View {

    @BindView(R.id.m_title)
    TextView mTitle;
    @BindView(R.id.m_back)
    ImageView mBack;
    @BindView(R.id.m_balance)
    TextView mBalance;
    @BindView(R.id.m_my_bankcard)
    TextView mMyBankcard;
    @BindView(R.id.m_want_recharge)
    TextView mWantRecharge;
    @BindView(R.id.m_want_withdraw)
    TextView mWantWithdraw;
    @BindView(R.id.m_recharge_record)
    TextView mRechargeRecord;
    @BindView(R.id.m_withdraw_record)
    TextView mWithdrawRecord;
    @BindView(R.id.activity_my_wallet)
    LinearLayout mRoot;
    private String money;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_wallet;
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    protected void initData() {
        mTitle.setText("我的钱包");

        initProperty();
    }

    private void initProperty() {
        money = "0.00";
        SpannableString mSpan = new SpannableString("我的余额：" + money + "元");
        mSpan.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.text_color)),
                0, 5, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        mBalance.setText(mSpan);
    }

    @OnClick({R.id.m_back, R.id.m_my_bankcard, R.id.m_want_recharge, R.id.m_want_withdraw,
            R.id.m_recharge_record, R.id.m_withdraw_record})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.m_back:
                finish();
                break;
            case R.id.m_my_bankcard:
                //我的银行卡
                break;
            case R.id.m_want_recharge:
                //我要充值
                startActivity(new Intent(this, AccountRechargeActivity.class));
                break;
            case R.id.m_want_withdraw:
                //我要提现
                break;
            case R.id.m_recharge_record:
                //充值记录
                startActivity(new Intent(this, RechargeRecordActivity.class));
                break;
            case R.id.m_withdraw_record:
                //提现记录
                break;
        }
    }
}
