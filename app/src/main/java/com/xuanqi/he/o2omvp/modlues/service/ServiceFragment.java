package com.xuanqi.he.o2omvp.modlues.service;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.mvp.MVPBaseFragment;
import com.xuanqi.he.o2omvp.widget.CircleImageView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ServiceFragment extends MVPBaseFragment<ServiceContract.View, ServicePresenter> implements ServiceContract.View {

    @BindView(R.id.m_title)
    TextView mTitle;
    @BindView(R.id.m_back)
    ImageView mBack;
    @BindView(R.id.m_right)
    TextView mRight;
    @BindView(R.id.iv_icon)
    CircleImageView mIvIcon;
    @BindView(R.id.tv_company_name)
    TextView mTvCompanyName;
    @BindView(R.id.tv_phone_number)
    TextView mTvPhoneNumber;
    @BindView(R.id.tv_description)
    TextView mTvDescription;
    @BindView(R.id.tv_property_payment)
    TextView mTvPropertyPayment;
    @BindView(R.id.tv_online_repair)
    TextView mTvOnlineRepair;
    @BindView(R.id.tv_property_notice)
    TextView mTvPropertyNotice;
    @BindView(R.id.tv_residential_information)
    TextView mTvResidentialInformation;
    @BindView(R.id.tv_pay_parking)
    TextView mTvPayParking;
    @BindView(R.id.tv_my_complaint)
    TextView mTvMyComplaint;
    @BindView(R.id.tv_intelligent_access_control)
    TextView mTvIntelligentAccessControl;
    @BindView(R.id.tv_recreational_facilities)
    TextView mTvRecreationalFacilities;
    @BindView(R.id.tv_life_payment)
    TextView mTvLifePayment;
    @BindView(R.id.tv_community_service)
    TextView mTvCommunityService;
    @BindView(R.id.tv_my_courier)
    TextView mTvMyCourier;
    @BindView(R.id.tv_supermarket_selection)
    TextView mTvSupermarketSelection;
    @BindView(R.id.iv_advertising)
    ImageView mIvAdvertising;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_service;
    }

    @Override
    protected void initData() {
        mBack.setVisibility(View.GONE);
        mTitle.setText("服务中心");
        mRight.setVisibility(View.VISIBLE);
        mRight.setText("任务中心");
    }

    @OnClick({R.id.m_right, R.id.tv_property_payment, R.id.tv_online_repair,
            R.id.tv_property_notice, R.id.tv_residential_information,
            R.id.tv_pay_parking, R.id.tv_my_complaint,
            R.id.tv_intelligent_access_control, R.id.tv_recreational_facilities,
            R.id.tv_life_payment, R.id.tv_community_service, R.id.tv_my_courier,
            R.id.tv_supermarket_selection, R.id.iv_advertising})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.m_right:
                //任务中心
                break;
            case R.id.tv_property_payment:
                //物业缴费
                break;
            case R.id.tv_online_repair:
                //线上报修
                break;
            case R.id.tv_property_notice:
                //物业通知
                break;
            case R.id.tv_residential_information:
                //住宅信息
                break;
            case R.id.tv_pay_parking:
                //缴费停车
                break;
            case R.id.tv_my_complaint:
                //我的投诉
                break;
            case R.id.tv_intelligent_access_control:
                //智能门禁
                break;
            case R.id.tv_recreational_facilities:
                //娱乐设施
                break;
            case R.id.tv_life_payment:
                //生活缴费
                break;
            case R.id.tv_community_service:
                //小区服务
                break;
            case R.id.tv_my_courier:
                //我的快递
                break;
            case R.id.tv_supermarket_selection:
                //超市代选
                break;
            case R.id.iv_advertising:
                //任务大厅(广告)
                break;
        }
    }
}
