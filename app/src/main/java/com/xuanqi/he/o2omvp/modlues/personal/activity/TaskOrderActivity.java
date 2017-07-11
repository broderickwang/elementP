package com.xuanqi.he.o2omvp.modlues.personal.activity;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.se7en.utils.DeviceUtils;
import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.base.BaseActivity;
import com.xuanqi.he.o2omvp.modlues.personal.adapter.TaskOrderPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class TaskOrderActivity extends BaseActivity {

    @BindView(R.id.m_title)
    TextView mTitle;
    @BindView(R.id.m_back)
    ImageView mBack;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.activity_task_order)
    LinearLayout mRoot;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_task_order;
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    protected void initData() {
        mTitle.setText("任务订单");

        List<CharSequence> mList = new ArrayList<>();
        mList.add("我的接单");
        mList.add("我的发布");
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setSelectedTabIndicatorHeight(DeviceUtils.dip2px(3));
        mTabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.red));

        mTabLayout.setTabTextColors(ContextCompat.getColor(this, R.color.light_text_color),
                ContextCompat.getColor(this, R.color.red));

        TaskOrderPagerAdapter adapter = new TaskOrderPagerAdapter(getSupportFragmentManager(), mList);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @OnClick(R.id.m_back)
    public void onViewClicked() {
        finish();
    }
}
