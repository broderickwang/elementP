package com.xuanqi.he.o2omvp.modlues.personal.activity.orderdetails;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.mvp.MVPBaseActivity;
import com.xuanqi.he.o2omvp.widget.CircleImageView;
import com.xuanqi.he.o2omvp.widget.filldatalinear.FillDataLinearLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class OrderDetailsActivity extends MVPBaseActivity<OrderDetailsContract.View, OrderDetailsPresenter> implements OrderDetailsContract.View {

    @BindView(R.id.m_title)
    TextView mTitle;
    @BindView(R.id.m_back)
    ImageView mBack;
    @BindView(R.id.iv_icon)
    CircleImageView mIvIcon;
    @BindView(R.id.tv_status)
    TextView mTvStatus;
    @BindView(R.id.tv_honorifics)
    TextView mTvHonorifics;
    @BindView(R.id.ll_btn_box)
    FillDataLinearLayout mLlBtnBox;
    @BindView(R.id.tv_store_name)
    TextView mTvStoreName;
    @BindView(R.id.ll_goods_box)
    FillDataLinearLayout mLlGoodsBox;
    @BindView(R.id.tv_delivery_fee)
    TextView mTvDeliveryFee;
    @BindView(R.id.ll_offer_box)
    FillDataLinearLayout mLlOfferBox;
    @BindView(R.id.tv_real_money)
    TextView mTvRealMoney;
    @BindView(R.id.rl_btn_contact_merchant)
    RelativeLayout mRlBtnContactMerchant;
    @BindView(R.id.tv_delivery_time)
    TextView mTvDeliveryTime;
    @BindView(R.id.tv_shipping_address)
    TextView mTvShippingAddress;
    @BindView(R.id.tv_delivery_method)
    TextView mTvDeliveryMethod;
    @BindView(R.id.tv_delivery_personnel)
    TextView mTvDeliveryPersonnel;
    @BindView(R.id.rl_btn_contact_dispatcher)
    RelativeLayout mRlBtnContactDispatcher;
    @BindView(R.id.tv_order_number)
    TextView mTvOrderNumber;
    @BindView(R.id.tv_copy_number)
    TextView mTvCopyNumber;
    @BindView(R.id.tv_payment_method)
    TextView mTvPaymentMethod;
    @BindView(R.id.tv_order_time)
    TextView mTvOrderTime;
    @BindView(R.id.root)
    LinearLayout mRoot;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_details;
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.m_back, R.id.rl_btn_contact_merchant, R.id.rl_btn_contact_dispatcher, R.id.tv_copy_number})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.m_back:
                finish();
                break;
            case R.id.rl_btn_contact_merchant:
                break;
            case R.id.rl_btn_contact_dispatcher:
                break;
            case R.id.tv_copy_number:
                break;
        }
    }
}
