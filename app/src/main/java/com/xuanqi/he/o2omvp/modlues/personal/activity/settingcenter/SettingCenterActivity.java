package com.xuanqi.he.o2omvp.modlues.personal.activity.settingcenter;

import android.app.Activity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.mvp.MVPBaseActivity;
import com.xuanqi.he.o2omvp.widget.SwitchButton;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SettingCenterActivity extends MVPBaseActivity<SettingCenterContract.View, SettingCenterPresenter> implements SettingCenterContract.View {

    @BindView(R.id.m_title)
    TextView mTitle;
    @BindView(R.id.m_back)
    ImageView mBack;
    @BindView(R.id.m_switch_button)
    SwitchButton mSwitchButton;
    @BindView(R.id.m_phone_number)
    TextView mPhoneNumber;
    @BindView(R.id.m_consumer_hotline)
    RelativeLayout mConsumerHotline;
    @BindView(R.id.m_cache)
    TextView mCache;
    @BindView(R.id.m_clear_cache)
    RelativeLayout mClearCache;
    @BindView(R.id.m_version)
    TextView mVersion;
    @BindView(R.id.m_about_us)
    RelativeLayout mAboutUs;
    @BindView(R.id.m_exit)
    TextView mExit;
    @BindView(R.id.activity_setting_center)
    LinearLayout mRoot;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting_center;
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mSwitchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Toast.makeText(SettingCenterActivity.this, "开关开了", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SettingCenterActivity.this, "开关关了", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    protected void initData() {
        mTitle.setText("设置中心");
    }

    @OnClick({R.id.m_back, R.id.m_consumer_hotline, R.id.m_clear_cache,
            R.id.m_about_us, R.id.m_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.m_back:
                finish();
                break;
            case R.id.m_consumer_hotline:
                break;
            case R.id.m_clear_cache:
                break;
            case R.id.m_about_us:
                break;
            case R.id.m_exit:
                break;
        }
    }
}
