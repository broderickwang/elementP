package com.xuanqi.he.o2omvp.modlues.personal.activity.usercertification;

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
 *  邮箱 784787081@qq.com
 */

public class UserCertificationActivity extends MVPBaseActivity<UserCertificationContract.View, UserCertificationPresenter> implements UserCertificationContract.View, View.OnTouchListener {

    @BindView(R.id.m_title)
    TextView mTitle;
    @BindView(R.id.m_back)
    ImageView mBack;
    @BindView(R.id.m_edit_real_name)
    EditText mEditRealName;
    @BindView(R.id.m_edit_id_card_number)
    EditText mEditIdCardNumber;
    @BindView(R.id.m_confirm)
    TextView mConfirm;
    @BindView(R.id.m_description)
    TextView mDescription;
    @BindView(R.id.activity_user_certification)
    LinearLayout mRoot;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_certification;
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mEditRealName.setOnTouchListener(this);
        mEditIdCardNumber.setOnTouchListener(this);
    }

    @Override
    protected void initData() {
        mTitle.setText("实名认证");
        String text = "温馨提示：认证成功后身份信息不可修改。";
        SpannableString mSpan = new SpannableString(text);
        mSpan.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.toolbar_color)),
                0, 5, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        mDescription.setText(mSpan);
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
            case R.id.m_edit_real_name:
                mEditRealName.setCursorVisible(true);
                break;
            case R.id.m_edit_id_card_number:
                mEditIdCardNumber.setCursorVisible(true);
                break;
        }
        return false;
    }

    @Override
    public int[] hideSoftByEditViewIds() {
        return new int[]{R.id.m_edit_real_name, R.id.m_edit_id_card_number};
    }
}
