package com.xuanqi.he.o2omvp.activity.main;


import android.app.Activity;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.baidu.mapapi.SDKInitializer;
import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.baidu.BaiDuSDKReceiver;
import com.xuanqi.he.o2omvp.modlues.life.LifeFragment;
import com.xuanqi.he.o2omvp.modlues.main.MainFragment;
import com.xuanqi.he.o2omvp.modlues.personal.PersonalFragment;
import com.xuanqi.he.o2omvp.modlues.service.ServiceFragment;
import com.xuanqi.he.o2omvp.mvp.MVPBaseActivity;

import butterknife.BindView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MainActivity extends MVPBaseActivity<MainContract.View, MainPresenter> implements MainContract.View {

    @BindView(R.id.m_frame_layout)
    FrameLayout mFrameLayout;
    @BindView(R.id.m_btn_main)
    RadioButton mBtnMain;
    @BindView(R.id.m_btn_life)
    RadioButton mBtnLife;
    @BindView(R.id.m_btn_service)
    RadioButton mBtnService;
    @BindView(R.id.m_btn_personal)
    RadioButton mBtnPersonal;
    @BindView(R.id.m_radio_group)
    RadioGroup mRadioGroup;
    @BindView(R.id.activity_main)
    RelativeLayout mRoot;
    private RadioButton mBtnLast;
    private Fragment[] mFragments;
    private FragmentManager mFragmentManager;
    private int lastIndex = -1;
    public String TAG = "MainFragment";
    private BaiDuSDKReceiver mReceiver;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
//                LogUtils.e(TAG, "mRadioGroup被点击了......");
                int position;
                switch (checkedId) {
                    case R.id.m_btn_main:
                        mBtnLast = mBtnMain;
                        position = 0;
                        addFragment(position);
                        break;
                    case R.id.m_btn_life:
                        mBtnLast = mBtnLife;
                        position = 1;
                        addFragment(position);
                        break;
                    case R.id.m_btn_service:
                        mBtnLast = mBtnService;
                        position = 2;
                        addFragment(position);
                        break;
                    case R.id.m_btn_personal:
                        mBtnLast = mBtnPersonal;
                        position = 3;
                        addFragment(position);
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {
        mFragments = new Fragment[4];
        mFragmentManager = getSupportFragmentManager();

        addFragment(0);
        mBtnLast = mBtnMain;

        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK);
        iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
        iFilter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
        mReceiver = new BaiDuSDKReceiver();
        registerReceiver(mReceiver, iFilter);
    }

    private void addFragment(int position) {
        if (lastIndex == position) {
            return;
        }
        FragmentTransaction transaction =
                mFragmentManager.beginTransaction();

        if (lastIndex != -1) {
            if (mFragments[lastIndex] != null) {
                transaction.hide(mFragments[lastIndex]);
            }
        }
        if (mFragments[position] == null) {
            switch (position) {
                case 0:
                    mFragments[position] = new MainFragment();
                    break;
                case 1:
                    mFragments[position] = new LifeFragment();
                    break;
                case 2:
                    mFragments[position] = new ServiceFragment();
                    break;
                case 3:
                    mFragments[position] = new PersonalFragment();
                    break;
                default:
                    break;
            }
            transaction.add(R.id.m_frame_layout, mFragments[position]);
        } else {
            transaction.show(mFragments[position]);
        }
        transaction.commit();
        lastIndex = position;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }
}
