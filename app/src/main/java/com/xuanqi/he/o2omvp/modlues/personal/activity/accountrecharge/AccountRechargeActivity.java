package com.xuanqi.he.o2omvp.modlues.personal.activity.accountrecharge;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.se7en.utils.DeviceUtils;
import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.mvp.MVPBaseActivity;
import com.xuanqi.he.o2omvp.utils.LogUtils;
import com.xuanqi.he.o2omvp.widget.SecurityCodeView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class AccountRechargeActivity extends MVPBaseActivity<AccountRechargeContract.View, AccountRechargePresenter> implements AccountRechargeContract.View {

    @BindView(R.id.m_title)
    TextView mTitle;
    @BindView(R.id.m_back)
    ImageView mBack;
    @BindView(R.id.tv_bank_name)
    TextView mTvBankName;
    @BindView(R.id.tv_bank_number)
    TextView mTvBankNumber;
    @BindView(R.id.ll_choose_bank)
    LinearLayout mLlChooseBank;
    @BindView(R.id.edit_recharge_amount)
    EditText mEditRechargeAmount;
    @BindView(R.id.tv_immediate_recharge)
    TextView mTvImmediateRecharge;
    @BindView(R.id.activity_account_recharge)
    LinearLayout mRoot;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_account_recharge;
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mEditRechargeAmount.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mEditRechargeAmount.setCursorVisible(true);
                return false;
            }
        });
    }

    @Override
    protected void initData() {
        mTitle.setText("账户充值");
        mEditRechargeAmount.setCursorVisible(false);
    }

    @OnClick({R.id.m_back, R.id.ll_choose_bank, R.id.tv_immediate_recharge})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.m_back:
                finish();
                break;
            case R.id.ll_choose_bank:
                break;
            case R.id.tv_immediate_recharge:
                showDialog();
                break;
        }
    }

    private void showDialog() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.dialog_recharge_password, mRoot, false);
        final AlertDialog mDialog = new AlertDialog.Builder(this)
                .setCancelable(false)
                .create();
        mDialog.show();
        //获得dialog所在的窗体对象
        Window window = mDialog.getWindow();
        //让editText可以弹出软键盘
        window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        //得到参数
        WindowManager.LayoutParams params = window.getAttributes();
        //动态设置dialog的宽度为屏幕宽度的6/7
        params.width = DeviceUtils.getScreenWdith() * 6 / 7;
//        params.x = 500;
//        params.y = -height;
        params.y = -90;
        //把设置好的参数重新设置给dialog
        window.setAttributes(params);
        //最后才添加布局
        window.setContentView(contentView);

        contentView.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });

        SecurityCodeView codeView = (SecurityCodeView) contentView.findViewById(R.id.security_code_view);
        codeView.setInputCompleteListener(new SecurityCodeView.InputCompleteListener() {
            @Override
            public void inputComplete(String password) {
                mDialog.dismiss();
                LogUtils.e("密码=" + password);
            }

            @Override
            public void deleteContent(boolean isDelete) {

            }
        });
    }

    @Override
    public int[] hideSoftByEditViewIds() {
        return new int[]{R.id.edit_recharge_amount};
    }
}
