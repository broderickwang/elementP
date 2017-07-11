package com.xuanqi.he.o2omvp.modlues.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;
import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.widget.recycler.RefreshRecyclerView;
import com.xuanqi.he.o2omvp.widget.recycler.RefreshViewCreator;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * @author Created by He on 2017/5/17.
 * @description 下拉刷新
 */

public class MainRefreshCreator extends RefreshViewCreator {

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
    private String life = "让生活更美好";
    private String completed = "更新成功";
    private String failed = "更新失败";
    private String pull = "下拉更新...";
    private String up = "松手更新...";
    private String updating = "更新中...";

    @Override
    public View getRefreshView(Context context, ViewGroup parent) {
        View refreshView = LayoutInflater.from(context).inflate(R.layout.main_refresh_header, parent, false);
        ButterKnife.bind(this, refreshView);
        return refreshView;
    }

    @Override
    public void onPull(int currentDragHeight, int refreshViewHeight, int currentRefreshStatus) {

        switch (currentRefreshStatus) {
            case RefreshRecyclerView.REFRESH_STATUS_PULL_DOWN_REFRESH:
                avView.setVisibility(GONE);
                refreshRight.setVisibility(GONE);
                refreshCircle.setVisibility(VISIBLE);
                refreshCompleted.setVisibility(VISIBLE);
                refreshStatus.setVisibility(VISIBLE);
                refreshCompleted.setText(life);
                refreshStatus.setText(pull);
                refreshCircle.setScaleX((currentDragHeight * 1.0f) / (refreshViewHeight * 1.0f));
                refreshCircle.setScaleY((currentDragHeight * 1.0f) / (refreshViewHeight * 1.0f));
                refreshCircle.setAlpha((currentDragHeight * 1.0f) / (refreshViewHeight * 1.0f));
                break;
            case RefreshRecyclerView.REFRESH_STATUS_LOOSEN_REFRESHING:
                refreshStatus.setText(up);
                break;
        }
    }

    @Override
    public void onRefreshing() {
        refreshCircle.setVisibility(GONE);
        refreshRight.setVisibility(GONE);
        avView.setVisibility(VISIBLE);
//        avView.show();
        refreshStatus.setText(updating);
    }

    @Override
    public void onStopRefresh() {
        avView.setVisibility(GONE);
        avView.hide();
        refreshCircle.setVisibility(GONE);
        refreshStatus.setVisibility(GONE);
        refreshRight.setVisibility(VISIBLE);
        refreshCompleted.setVisibility(VISIBLE);
        refreshCompleted.setText(completed);
    }

    @Override
    public void onRefreshFailed() {
        avView.setVisibility(GONE);
        avView.hide();
        refreshCircle.setVisibility(GONE);
        refreshStatus.setVisibility(GONE);
        refreshRight.setVisibility(VISIBLE);
        refreshCompleted.setVisibility(VISIBLE);
        refreshCompleted.setText(failed);
    }
}
