package com.xuanqi.he.o2omvp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;
import com.xuanqi.he.o2omvp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * Created by Administrator on 2017/4/13.
 */

public class CustomRefreshHeader extends FrameLayout implements PtrUIHandler {

    @BindView(R.id.refresh_circle)
    TextView refreshCircle;
    @BindView(R.id.av_view)
    AVLoadingIndicatorView avView;
    @BindView(R.id.refresh_completed)
    TextView refreshCompleted;
    @BindView(R.id.refresh_status)
    TextView refreshStatus;
    @BindView(R.id.refresh_right)
    ImageView refreshRight;
    private Context context;
    private String life = "让生活更美好";
    private String completed = "更新成功";
    private String pull = "下拉更新...";
    private String up = "松手更新...";
    private String updating = "更新中...";
    private String TAG = "CustomRefreshHeader";
    private boolean isStart;

    public CustomRefreshHeader(Context context) {
        this(context, null);
    }

    public CustomRefreshHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomRefreshHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.refresh_header, this, true);//参数三一定要为true
        ButterKnife.bind(this, view);
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
        resetView();
    }

    private void resetView() {
        refreshCircle.setVisibility(GONE);
        refreshCompleted.setVisibility(GONE);
        refreshStatus.setVisibility(GONE);
        refreshRight.setVisibility(GONE);
//        avView.setVisibility(GONE);
        avView.hide();
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        refreshRight.setVisibility(GONE);
//        avView.setVisibility(GONE);
        avView.hide();
        refreshCircle.setVisibility(VISIBLE);
        refreshCompleted.setVisibility(VISIBLE);
        refreshStatus.setVisibility(VISIBLE);
        refreshCompleted.setText(life);
        isStart = false;
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        isStart = true;
//        avView.setVisibility(VISIBLE);

        avView.show();
        refreshRight.setVisibility(GONE);
        refreshCircle.setVisibility(GONE);
        refreshCompleted.setVisibility(VISIBLE);
        refreshStatus.setVisibility(VISIBLE);

        refreshStatus.setText(updating);
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
//        avView.setVisibility(GONE);
        avView.hide();
        refreshCircle.setVisibility(GONE);
        refreshStatus.setVisibility(GONE);
        refreshRight.setVisibility(VISIBLE);
        refreshCompleted.setVisibility(VISIBLE);
        refreshCompleted.setText(completed);
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch,
                                   byte status, PtrIndicator ptrIndicator) {
        float currentPercent = ptrIndicator.getCurrentPercent();
        /*if (!isStart) {
            if (currentPercent < 1.0f) {
                refreshStatus.setText(pull);
            } else {
                refreshStatus.setText(up);
            }
        }

        if (currentPercent > 1.0f) {
            currentPercent = 1.0f;
        }
        refreshCircle.setScaleX(currentPercent * 1.0f);
        refreshCircle.setScaleY(currentPercent * 1.0f);
        refreshCircle.setAlpha(currentPercent);*/

        if (!isStart) {
            if (currentPercent < 1.2f) {
                refreshStatus.setText(pull);
            } else {
                refreshStatus.setText(up);
            }
        }

        if (currentPercent > 1.2f) {
            currentPercent = 1.2f;
        }
        refreshCircle.setScaleX(currentPercent * (1.0f / 1.2f));
        refreshCircle.setScaleY(currentPercent * (1.0f / 1.2f));
        refreshCircle.setAlpha(currentPercent * (1.0f / 1.2f));
    }
}
