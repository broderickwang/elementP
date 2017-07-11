package com.xuanqi.he.o2omvp.modlues.main.activity.search;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.se7en.utils.DeviceUtils;
import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.constant.Key;
import com.xuanqi.he.o2omvp.modlues.main.adapter.FlowAdapter;
import com.xuanqi.he.o2omvp.mvp.MVPBaseActivity;
import com.xuanqi.he.o2omvp.utils.KeyBoardUtils;
import com.xuanqi.he.o2omvp.utils.LogUtils;
import com.xuanqi.he.o2omvp.widget.ReboundScrollView;
import com.xuanqi.he.o2omvp.widget.flowlayout.FlowLayout;
import com.xuanqi.he.o2omvp.widget.flowlayout.TagFlowLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SearchActivity extends MVPBaseActivity<SearchContract.View, SearchPresenter> implements SearchContract.View {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_search)
    TextView mTvSearch;
    @BindView(R.id.edit_search)
    EditText mEditSearch;
    @BindView(R.id.ll_box)
    RelativeLayout mLlBox;
    @BindView(R.id.activity_search)
    RelativeLayout mRoot;
    @BindView(R.id.fl_search_box)
    FrameLayout mFlSearchBox;
    @BindView(R.id.flow_layout_popular)
    TagFlowLayout mFlowLayoutPopular;
    @BindView(R.id.flow_layout_history)
    TagFlowLayout mFlowLayoutHistory;
    @BindView(R.id.scroll_view)
    ReboundScrollView mScrollView;
    private int mSearchY;
    private int mBoxHeight;
    private int mDuration = 300;
    private ValueAnimator mAnimator;
    private String[] popularArray = new String[]{"古茗", "蛋糕", "鸡排", "koi", "酸辣粉", "大润发", "凉皮", "四果汤", "拉面", "星巴克"};
    private String[] historyArray = new String[]{"牛肉披萨", "85°C享甜", "85°C享甜(阳光店)", "黄焖鸡米饭", "快乐番薯(市标店)", "豪味快速餐饮", "炸鸡", "考生蚝", "肯德基", "乐客便利店"};
    private String TAG = "SearchActivity";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mEditSearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mEditSearch.setCursorVisible(true);
                return false;
            }
        });
        mFlowLayoutPopular.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                LogUtils.e("click=" + popularArray[position]);
                return false;
            }
        });
        mFlowLayoutHistory.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                LogUtils.e("click=" + historyArray[position]);
                return false;
            }
        });
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        mSearchY = intent.getIntExtra(Key.MAIN_SEARCH_Y, -1);
        mBoxHeight = intent.getIntExtra(Key.MAIN_SEARCH_BOX_HEIGHT, -1);
        ViewGroup.LayoutParams layoutParams = mLlBox.getLayoutParams();
        layoutParams.height = mBoxHeight;
        mLlBox.setLayoutParams(layoutParams);

        LogUtils.e(TAG, "mBoxHeight=" + mBoxHeight);
        mFlSearchBox.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mFlSearchBox.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                setLocation(true);
                KeyBoardUtils.openKeyboard(mEditSearch, SearchActivity.this);
            }
        });

        initFlowLayout();
    }

    private void initFlowLayout() {
        FlowAdapter popularAdapter = new FlowAdapter(popularArray);
        mFlowLayoutPopular.setAdapter(popularAdapter);

        FlowAdapter historyAdapter = new FlowAdapter(historyArray);
        mFlowLayoutHistory.setAdapter(historyAdapter);
    }

    @OnClick({R.id.iv_back, R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_search:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        KeyBoardUtils.closeKeyboard(mEditSearch, this);
        setLocation(false);
    }

    private void setLocation(final boolean flag) {

        int[] location = new int[2];
        mFlSearchBox.getLocationOnScreen(location);
        final int translateY = mSearchY - location[1];

        if (flag) {
            mFlSearchBox.setY(mFlSearchBox.getY() + translateY);
        }

        final float searchBoxY = mFlSearchBox.getY();
        final int mLlBoxHeight = mLlBox.getHeight();
        final int mEditSearchWidth = mEditSearch.getWidth();
        final int mTvSearchWidth = mTvSearch.getWidth();

        if (flag) {
            mAnimator = ValueAnimator.ofFloat(0, 1.0f);
        } else {
            mAnimator = ValueAnimator.ofFloat(1.0f, 0);
        }
        final ViewGroup.LayoutParams params = mLlBox.getLayoutParams();
        final ViewGroup.LayoutParams layoutParams = mEditSearch.getLayoutParams();
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedValue = (float) valueAnimator.getAnimatedValue();
                if (flag) {
                    mFlSearchBox.setY(searchBoxY * (1 - animatedValue) + (DeviceUtils.dip2px(10) + 1) * animatedValue);
                    params.height = (int) (mLlBoxHeight * (1 - animatedValue) + animatedValue * (DeviceUtils.dip2px(55) + 1));
                    layoutParams.width = (int) (mEditSearchWidth * (1 - animatedValue) + animatedValue * (mEditSearchWidth - DeviceUtils.dip2px(45) - mTvSearchWidth));
                } else {
                    mFlSearchBox.setY(searchBoxY * animatedValue + (searchBoxY + translateY) * (1 - animatedValue));
                    params.height = (int) (mLlBoxHeight * animatedValue + mBoxHeight * (1 - animatedValue));
                    layoutParams.width = (int) (mEditSearchWidth * animatedValue + ((DeviceUtils.getScreenWdith() - DeviceUtils.dip2px(30)) * (1 - animatedValue)));
                }
                mLlBox.setLayoutParams(params);
                mEditSearch.setLayoutParams(layoutParams);
            }
        });
        mAnimator.setDuration(mDuration);
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (flag) {
//                    mFlSearchBox.setY(DeviceUtils.dip2px(10));
                } else {
//                    mFlSearchBox.setY(searchBoxY + translateY);
                    finish();
                    overridePendingTransition(0, 0);
                }
            }
        });
        mAnimator.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAnimator != null) {
            mAnimator.cancel();
        }
    }
}
