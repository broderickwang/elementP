package com.xuanqi.he.o2omvp.modlues.main.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.se7en.utils.DeviceUtils;
import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.base.BaseActivity;
import com.xuanqi.he.o2omvp.constant.Key;
import com.xuanqi.he.o2omvp.modlues.main.activity.submitorder.SubmitOrderActivity;
import com.xuanqi.he.o2omvp.modlues.main.adapter.BusinessOfferAdapter;
import com.xuanqi.he.o2omvp.modlues.main.adapter.BusinessmenPagerAdapter;
import com.xuanqi.he.o2omvp.modlues.main.bean.CommodityBean;
import com.xuanqi.he.o2omvp.modlues.main.bean.ShopCart;
import com.xuanqi.he.o2omvp.modlues.main.dialog.ShopCartDialog;
import com.xuanqi.he.o2omvp.modlues.main.fragment.commodity.CommodityFragment;
import com.xuanqi.he.o2omvp.utils.LogUtils;
import com.xuanqi.he.o2omvp.widget.filldatalinear.FillDataLinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class BusinessmenActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    @BindView(R.id.app_bar_layout)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.coordinator_layout)
    CoordinatorLayout mCoordinatorLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.iv_car)
    ImageView mIvCar;
    @BindView(R.id.iv_more)
    ImageView mIvMore;
    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.ll_detail)
    LinearLayout mLlDetail;
    @BindView(R.id.tv_cart_total)
    TextView mTvCartTotal;
    @BindView(R.id.tv_settle_accounts)
    TextView mTvSettleAccounts;
    @BindView(R.id.rl_cart_bottom)
    RelativeLayout mRlCartBottom;
    @BindView(R.id.tv_activity)
    TextView mTvActivity;
    @BindView(R.id.iv_cart)
    ImageView mIvCart;
    @BindView(R.id.fl_cart_layout)
    FrameLayout mFlCartLayout;
    @BindView(R.id.tv_cart_total_num)
    TextView mTvCartTotalNum;
    @BindView(R.id.root)
    RelativeLayout mRoot;
    @BindView(R.id.rl_cart_box)
    RelativeLayout mRlCartBox;
    @BindView(R.id.ll_offer_list)
    FillDataLinearLayout mLlOfferList;
    private ShopCart shopCart;
    private SpannableString mSpanSec;
    private String textOne = "商品";
    private String textTwo = "评价(0.0分)";
    private BusinessmenPagerAdapter mAdapter;
    private List<SpannableString> mList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_businessmen;
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int progress = Math.abs(verticalOffset);
                int total = appBarLayout.getTotalScrollRange();
                mLlDetail.setAlpha(1.0f - ((progress * 1.0f) / (total * 1.0f)));
                if (progress == total) {
                    if (mLlDetail.getVisibility() != View.INVISIBLE) {
                        mLlDetail.setVisibility(View.INVISIBLE);
                    }
                } else {
                    if (mLlDetail.getVisibility() != View.VISIBLE) {
                        mLlDetail.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        mViewPager.addOnPageChangeListener(this);
    }

    @Override
    protected void initData() {
        setSupportActionBar(mToolbar);

        initProperty();

        mList = new ArrayList<>();

        SpannableString spanFirst = new SpannableString(textOne);
        mList.add(spanFirst);

        mSpanSec = new SpannableString(textTwo);
        mSpanSec.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.blue)), 2, textTwo.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mList.add(mSpanSec);

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setSelectedTabIndicatorHeight(DeviceUtils.dip2px(3));
        mTabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.toolbar_color));

        mTabLayout.setTabTextColors(ContextCompat.getColor(this, R.color.text_color),
                ContextCompat.getColor(this, R.color.toolbar_color));


        mAdapter = new BusinessmenPagerAdapter(getSupportFragmentManager(), mList);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initProperty() {
//        mTvCartTotal.getPaint().setFakeBoldText(true);
        List<String> mList = new ArrayList<>();
        mList.add("满22减10，满45减20");
        mList.add("新用户下单，满45减20");
        BusinessOfferAdapter adapter = new BusinessOfferAdapter(mList);
        mLlOfferList.setAdapter(adapter);
    }

    @OnClick({R.id.iv_back, R.id.iv_car, R.id.iv_more, R.id.tv_settle_accounts,
            R.id.fl_cart_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_car:
                LogUtils.e("购物车");
                break;
            case R.id.iv_more:
                LogUtils.e("更多");
                break;
            case R.id.tv_settle_accounts:
                if (shopCart != null && shopCart.getShoppingAccount() > 0) {
                    Intent intent = new Intent(this, SubmitOrderActivity.class);
                    ArrayList<CommodityBean> list = new ArrayList<>();
                    list.addAll(shopCart.getShoppingSingleMap().keySet());
                    intent.putParcelableArrayListExtra(Key.GOODS_LIST_KEY, list);
                    intent.putExtra(Key.TOTAL_PRICE_KEY, shopCart.getShoppingTotalPrice());
                    startActivity(intent);
                }
                break;
            case R.id.fl_cart_layout:
                showBottomDialog();
                break;
        }
    }

    private void showBottomDialog() {
        if (shopCart != null && shopCart.getShoppingAccount() > 0) {
            ShopCartDialog dialog = new ShopCartDialog(this, shopCart, R.style.cartDialog);
            Window window = dialog.getWindow();
            dialog.setOnShoppingDialogClickListener(new ShopCartDialog.OnShoppingDialogClickListener() {
                @Override
                public void onAddClick(int num, String name) {
                    restCommodityRightAdapter(num, name);
                }

                @Override
                public void onDeleteClick(int num, String name) {
                    LogUtils.e("name=" + name);
                    restCommodityRightAdapter(num, name);
                }

                @Override
                public void clear() {
                    refreshCommodityRightAdapter();
                }
            });
            dialog.setCanceledOnTouchOutside(true);
            dialog.setCancelable(true);
            dialog.show();
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            params.gravity = Gravity.BOTTOM;
            params.dimAmount = 0.5f;
            window.setAttributes(params);
        }
    }

    private void restCommodityRightAdapter(int num, String name) {
        showTotalPrice(shopCart);
        ((CommodityFragment) getSupportFragmentManager().getFragments().get(0)).notifyRightAdapter(num, name);
    }

    private void refreshCommodityRightAdapter() {
        showTotalPrice(shopCart);
        ((CommodityFragment) getSupportFragmentManager().getFragments().get(0)).refreshRightAdapter();
    }

    public int[] getCartLocation() {
        int[] cartLocation = new int[2];
        mIvCart.getLocationInWindow(cartLocation);
        return cartLocation;
    }

    public void showTotalPrice(ShopCart shopCart) {
        this.shopCart = shopCart;
        GradientDrawable drawable = (GradientDrawable) mFlCartLayout.getBackground();
        if (shopCart != null && shopCart.getShoppingTotalPrice() > 0) {
            mTvCartTotal.setText("¥ " + shopCart.getShoppingTotalPrice());
            if (mTvCartTotalNum.getVisibility() != View.VISIBLE) {
                mTvCartTotalNum.setVisibility(View.VISIBLE);
            }
            mTvCartTotalNum.setText("" + shopCart.getShoppingAccount());
            drawable.setColor(ContextCompat.getColor(this, R.color.blue));
            mTvSettleAccounts.setText("去结算");
            mTvSettleAccounts.setBackgroundColor(ContextCompat.getColor(this, R.color.blue));
        } else {
            mTvCartTotal.setText("未选购");
            if (mTvCartTotalNum.getVisibility() != View.GONE) {
                mTvCartTotalNum.setVisibility(View.GONE);
            }
            drawable.setColor(ContextCompat.getColor(this, R.color.black));
            mTvSettleAccounts.setText("20元起送");
            mTvSettleAccounts.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewPager.removeOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 0) {
            startAnimation(true);
        } else {
            startAnimation(false);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void startAnimation(final boolean isEnter) {
        Animation animation;
        if (isEnter) {
            animation = AnimationUtils.loadAnimation(this, R.anim.translate_enter);
        } else {
            animation = AnimationUtils.loadAnimation(this, R.anim.translate_exit);
        }
        mRlCartBox.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (isEnter) {
                    mRlCartBox.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (!isEnter) {
                    mRlCartBox.setVisibility(View.GONE);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
