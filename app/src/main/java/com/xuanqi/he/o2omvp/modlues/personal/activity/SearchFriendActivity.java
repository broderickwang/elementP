package com.xuanqi.he.o2omvp.modlues.personal.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.se7en.utils.DeviceUtils;
import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.base.BaseActivity;
import com.xuanqi.he.o2omvp.modlues.life.bean.ImageBean;
import com.xuanqi.he.o2omvp.modlues.life.bean.ImageShowLoader;
import com.xuanqi.he.o2omvp.utils.KeyBoardUtils;
import com.xuanqi.he.o2omvp.utils.LogUtils;
import com.xuanqi.he.o2omvp.widget.imageshow.ImageShowPickerBean;
import com.xuanqi.he.o2omvp.widget.imageshow.ImageShowPickerListener;
import com.xuanqi.he.o2omvp.widget.imageshow.ImageShowPickerView;
import com.yanzhenjie.album.Album;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchFriendActivity extends BaseActivity {

    @BindView(R.id.m_title)
    TextView mTitle;
    @BindView(R.id.iv_back)
    ImageView mBack;
    @BindView(R.id.m_right)
    TextView mRight;
    @BindView(R.id.tv_false_search)
    TextView mTvFalseSearch;
    @BindView(R.id.tv_search)
    TextView mTvSearch;
    @BindView(R.id.edit_search)
    EditText mEditSearch;
    @BindView(R.id.activity_search_friend)
    RelativeLayout mRoot;
    @BindView(R.id.ll_animation_box)
    LinearLayout mLlAnimationBox;
    @BindView(R.id.toolbar_box)
    RelativeLayout mToolbarBox;
    @BindView(R.id.rl_color_box)
    RelativeLayout mRlColorBox;
    @BindView(R.id.image_show)
    ImageShowPickerView mImageShowPickerView;
    private ArrayList<String> mImageList;
    private static final int REQUEST_CODE_CHOOSE = 1010;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_friend;
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mLlAnimationBox.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mLlAnimationBox.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                startAnimation(true);
                KeyBoardUtils.openKeyboard(mEditSearch, SearchFriendActivity.this);
            }
        });
        mEditSearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mEditSearch.setCursorVisible(true);
                return false;
            }
        });

        mImageShowPickerView.setPickerListener(new ImageShowPickerListener() {
            @Override
            public void addOnClickListener(int remainNum) {
                Album.album(SearchFriendActivity.this)
//                        .requestCode(REQUEST_CODE_CHOOSE) // 请求码，返回时onActivityResult()的第一个参数。
                        .toolBarColor(ContextCompat.getColor(SearchFriendActivity.this, R.color.toolbar_color)) // Toolbar 颜色，默认蓝色。
                        .statusBarColor(ContextCompat.getColor(SearchFriendActivity.this, R.color.toolbar_color)) // StatusBar 颜色，默认蓝色。
//                        .navigationBarColor(navigationBarColor) // NavigationBar 颜色，默认黑色，建议使用默认。
                        .title("选择图片") // 配置title。
                        .selectCount(9) // 最多选择几张图片。
                        .columnCount(2) // 相册展示列数，默认是2列。
                        .camera(true) // 是否有拍照功能。
                        .checkedList(mImageList != null ? mImageList : new ArrayList<String>()) // 已经选择过得图片，相册会自动选中选过的图片，并计数。
                        .start(REQUEST_CODE_CHOOSE);
            }

            @Override
            public void picOnClickListener(List<ImageShowPickerBean> list, int position, int remainNum) {

            }

            @Override
            public boolean picOnLongClickListener(List<ImageShowPickerBean> list, int position, int remainNum) {
                return false;
            }

            @Override
            public void delOnClickListener(int position, int remainNum) {

            }
        });
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    protected void initData() {
        initProperty();

        List<ImageBean> mList = new ArrayList<>();

        mImageShowPickerView.setImageLoaderInterface(new ImageShowLoader());
        mImageShowPickerView.setNewData(mList);
        mImageShowPickerView.setShowAnim(true);
        mImageShowPickerView.show();
    }

    private void initProperty() {
        mTitle.setText("我的好友");
        mRight.setVisibility(View.VISIBLE);
        mRight.setText("附近的人");
    }

    private void startAnimation(final boolean flag) {
        List<Animator> list = new ArrayList<>();
        final int toolbarBoxHeight = mToolbarBox.getHeight();
        final int tvFalseSearchWidth = mTvFalseSearch.getWidth();
        final int targetWidth;

        ValueAnimator animator = ValueAnimator.ofFloat(0, 1.0f);
        if (flag) {
            targetWidth = DeviceUtils.getScreenWdith() - DeviceUtils.dip2px(110);
        } else {
            mLlAnimationBox.setVisibility(View.VISIBLE);
            targetWidth = DeviceUtils.getScreenWdith() - DeviceUtils.dip2px(30);
        }
        final ViewGroup.LayoutParams layoutParams = mTvFalseSearch.getLayoutParams();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                if (flag) {
                    mLlAnimationBox.setTranslationY(animatedValue * (-toolbarBoxHeight));
                } else {
                    mLlAnimationBox.setTranslationY((1 - animatedValue) * (-toolbarBoxHeight));
                }
                layoutParams.width = (int) (tvFalseSearchWidth * (1 - animatedValue) + targetWidth * animatedValue);
                mTvFalseSearch.setLayoutParams(layoutParams);
            }
        });

        list.add(animator);

        int gray = ContextCompat.getColor(this, R.color.rect_back_color);
        int orange = ContextCompat.getColor(this, R.color.toolbar_color);
        ObjectAnimator colorAnimator;
        if (flag) {
            colorAnimator = ObjectAnimator.ofInt(mRlColorBox, "backgroundColor", gray, orange);
        } else {
            colorAnimator = ObjectAnimator.ofInt(mRlColorBox, "backgroundColor", orange, gray);
        }
        colorAnimator.setEvaluator(new ArgbEvaluator());
        list.add(colorAnimator);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(300);
        animatorSet.playTogether(list);
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (flag) {
                    mLlAnimationBox.setVisibility(View.GONE);
                } else {
                    finish();
                    overridePendingTransition(0, 0);
                }
            }
        });
        animator.start();
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
        startAnimation(false);
        KeyBoardUtils.closeKeyboard(mEditSearch, SearchFriendActivity.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            List<ImageBean> list = new ArrayList<>();
            mImageList = Album.parseResult(data);
            for (String path : mImageList) {
                LogUtils.e(path);
                list.add(new ImageBean(path));
            }
            mImageShowPickerView.clearImage();
            mImageShowPickerView.addData(list);
        }

    }
}
