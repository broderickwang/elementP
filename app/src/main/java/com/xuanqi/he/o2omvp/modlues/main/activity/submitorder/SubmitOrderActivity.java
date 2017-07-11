package com.xuanqi.he.o2omvp.modlues.main.activity.submitorder;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.se7en.utils.DeviceUtils;
import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.constant.Key;
import com.xuanqi.he.o2omvp.modlues.main.adapter.SubmitOrderGoodsAdapter;
import com.xuanqi.he.o2omvp.modlues.main.adapter.SubmitOrderOfferAdapter;
import com.xuanqi.he.o2omvp.modlues.main.bean.CommodityBean;
import com.xuanqi.he.o2omvp.modlues.main.bean.OfferBean;
import com.xuanqi.he.o2omvp.mvp.MVPBaseActivity;
import com.xuanqi.he.o2omvp.utils.LogUtils;
import com.xuanqi.he.o2omvp.widget.RadiusBackgroundSpan;
import com.xuanqi.he.o2omvp.widget.filldatalinear.FillDataLinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SubmitOrderActivity extends MVPBaseActivity<SubmitOrderContract.View, SubmitOrderPresenter> implements SubmitOrderContract.View {

    @BindView(R.id.m_title)
    TextView mTitle;
    @BindView(R.id.m_back)
    ImageView mBack;
    @BindView(R.id.tv_user_info)
    TextView mTvUserInfo;
    @BindView(R.id.tv_delivery_time)
    TextView mTvDeliveryTime;
    @BindView(R.id.root)
    RelativeLayout mRoot;
    @BindView(R.id.tv_store_name)
    TextView mTvStoreName;
    @BindView(R.id.ll_goods_list)
    FillDataLinearLayout mLlGoodsList;
    @BindView(R.id.tv_delivery_fee)
    TextView mTvDeliveryFee;
    @BindView(R.id.ll_offer_list)
    FillDataLinearLayout mLlOfferList;
    @BindView(R.id.tv_red_envelopes)
    TextView mTvRedEnvelopes;
    @BindView(R.id.rl_choose_red_envelopes)
    RelativeLayout mRlChooseRedEnvelopes;
    @BindView(R.id.tv_vouchers)
    TextView mTvVouchers;
    @BindView(R.id.rl_choose_vouchers)
    RelativeLayout mRlChooseVouchers;
    @BindView(R.id.tv_total_money)
    TextView mTvTotalMoney;
    @BindView(R.id.tv_offer_money)
    TextView mTvOfferMoney;
    @BindView(R.id.tv_real_money)
    TextView mTvRealMoney;
    @BindView(R.id.tv_remarks)
    TextView mTvRemarks;
    @BindView(R.id.rl_choose_remarks)
    RelativeLayout mRlChooseRemarks;
    @BindView(R.id.tv_pay_money)
    TextView mTvPayMoney;
    @BindView(R.id.tv_pay_offer)
    TextView mTvPayOffer;
    @BindView(R.id.tv_to_pay)
    TextView mTvToPay;
    private String TAG = "SubmitOrderActivity";
    private ArrayList<CommodityBean> mGoodsList;
    private double mTotalPrice;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_submit_order;
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    protected void initData() {
        mTitle.setText("提交订单");
        String text = "尽快送达 [预计12:03送达]\n快风配送 准时必达 送货超快";
        SpannableString spannable = new SpannableString(text);
        int color = ContextCompat.getColor(this, R.color.blue);
        for (int i = 0; i < 3; i++) {
            RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(0.8f);
            spannable.setSpan(relativeSizeSpan, text.length() - (14 - i * 5), text.length() - (10 - i * 5), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

            RadiusBackgroundSpan span = new RadiusBackgroundSpan(color, DeviceUtils.dip2px(5), DeviceUtils.dip2px(3));
            spannable.setSpan(span, text.length() - (14 - i * 5), text.length() - (10 - i * 5), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        }
        mTvDeliveryTime.setText(spannable);

        Intent intent = getIntent();
        mGoodsList = intent.getParcelableArrayListExtra(Key.GOODS_LIST_KEY);
        mTotalPrice = intent.getDoubleExtra(Key.TOTAL_PRICE_KEY, -1);
        LogUtils.e(TAG, "goodsList.size()=" + mGoodsList.size() + " totalPrice=" + mTotalPrice);

        String totalPriceText = "总计 ¥ " + mTotalPrice;
        SpannableString spannableString = getSpan(totalPriceText, ContextCompat.getColor(this, R.color.red));
        mTvTotalMoney.setText(spannableString);

        initGoodsList();

        mPresenter.requestData();
    }

    private void initGoodsList() {
        SubmitOrderGoodsAdapter goodsAdapter = new SubmitOrderGoodsAdapter(mGoodsList);
        mLlGoodsList.setAdapter(goodsAdapter);
    }

    @OnClick({R.id.m_back, R.id.rl_choose_red_envelopes, R.id.rl_choose_vouchers, R.id.rl_choose_remarks, R.id.tv_to_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.m_back:
                finish();
                break;
            case R.id.rl_choose_red_envelopes:
                break;
            case R.id.rl_choose_vouchers:
                break;
            case R.id.rl_choose_remarks:
                break;
            case R.id.tv_to_pay:
                break;
        }
    }

    private SpannableString getSpan(String text, int color) {
        SpannableString spannableString = new SpannableString(text);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(color);
        spannableString.setSpan(foregroundColorSpan, 2, text.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(1.2f);
        spannableString.setSpan(relativeSizeSpan, 2, text.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        return spannableString;
    }

    @Override
    public void onRequestSuccess(List<OfferBean> list) {
        LogUtils.e(TAG, "onRequestSuccess.." + list.size());
        SubmitOrderOfferAdapter offerAdapter = new SubmitOrderOfferAdapter(list);
        mLlOfferList.setAdapter(offerAdapter);

        initPrice(list);
    }

    private void initPrice(List<OfferBean> list) {
        float totalOffer = 0;
        for (OfferBean offerBean : list) {
            totalOffer += offerBean.getPrice();
        }

        String totalOfferPriceText = "优惠 ¥ " + totalOffer;
        SpannableString spannableString = getSpan(totalOfferPriceText, ContextCompat.getColor(this, R.color.green));
        mTvOfferMoney.setText(spannableString);
        mTvPayOffer.setText("已优惠¥" + totalOffer);

        String totalRealPriceText = "应付 ¥ " + (mTotalPrice - totalOffer);
        SpannableString span = getSpan(totalRealPriceText, ContextCompat.getColor(this, R.color.red));
        mTvRealMoney.setText(span);
        mTvPayMoney.setText("¥ " + (mTotalPrice - totalOffer));
    }

    @Override
    public void onRequestFailed(String message) {

    }
}
