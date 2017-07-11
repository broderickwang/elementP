package com.xuanqi.he.o2omvp.modlues.personal.activity.modifyloginpassword;

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
 *  邮箱 784787081@qq.com
 */

public class ModifyLoginPasswordActivity extends MVPBaseActivity<ModifyLoginPasswordContract.View, ModifyLoginPasswordPresenter> implements ModifyLoginPasswordContract.View, View.OnTouchListener {

    @BindView(R.id.m_title)
    TextView mTitle;
    @BindView(R.id.m_back)
    ImageView mBack;
    @BindView(R.id.m_edit_password)
    EditText mEditPassword;
    @BindView(R.id.m_edit_again_password)
    EditText mEditAgainPassword;
    @BindView(R.id.m_confirm)
    TextView mConfirm;
    @BindView(R.id.activity_modify_login_password)
    LinearLayout mRoot;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modify_login_password;
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mEditPassword.setOnTouchListener(this);
        mEditAgainPassword.setOnTouchListener(this);
    }

    @Override
    protected void initData() {
        mTitle.setText("修改登录密码");
    }

    @OnClick({R.id.m_back, R.id.m_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.m_back:
                finish();
                break;
            case R.id.m_confirm:
                break;
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (view.getId()) {
            case R.id.m_edit_password:
                mEditPassword.setCursorVisible(true);
                break;
            case R.id.m_edit_again_password:
                mEditAgainPassword.setCursorVisible(true);
                break;
        }
        return false;
    }

    @Override
    public int[] hideSoftByEditViewIds() {
        return new int[]{R.id.m_edit_again_password, R.id.m_edit_password};
    }
}
