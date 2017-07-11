package com.xuanqi.he.o2omvp.modlues.personal.activity;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.se7en.utils.DeviceUtils;
import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.base.BaseActivity;
import com.xuanqi.he.o2omvp.constant.Key;
import com.xuanqi.he.o2omvp.modlues.personal.adapter.MyOrderPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MyOrderActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_notice)
    ImageView mTvNotice;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.activity_my_order)
    LinearLayout mRoot;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_order;
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    protected void initData() {
        int position = getIntent().getIntExtra(Key.MY_ORDER_ID_KEY, -1);

        List<CharSequence> mList = new ArrayList<>();
        mList.add("全部");
        mList.add("待付款");
        mList.add("待收货");
        mList.add("待评价");
        mList.add("售后");
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setSelectedTabIndicatorHeight(DeviceUtils.dip2px(3));
        mTabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.toolbar_color));

        mTabLayout.setTabTextColors(ContextCompat.getColor(this, R.color.light_text_color),
                ContextCompat.getColor(this, R.color.toolbar_color));


        MyOrderPagerAdapter adapter = new MyOrderPagerAdapter(getSupportFragmentManager(), mList);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);

        mViewPager.setOffscreenPageLimit(mList.size());
        mViewPager.setCurrentItem(position);
    }

    @OnClick({R.id.iv_back, R.id.tv_notice})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_notice:
                break;
        }
    }
}
