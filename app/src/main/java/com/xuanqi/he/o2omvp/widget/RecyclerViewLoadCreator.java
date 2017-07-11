package com.xuanqi.he.o2omvp.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;
import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.widget.recycler.LoadRefreshRecyclerView;
import com.xuanqi.he.o2omvp.widget.recycler.LoadViewCreator;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Created by He on 2017/5/17.
 * @description 上拉加载
 */

public class RecyclerViewLoadCreator extends LoadViewCreator {

    @BindView(R.id.foot_loading)
    LinearLayout footLoading;
    @BindView(R.id.foot_put)
    LinearLayout footPut;
    @BindView(R.id.foot_tv)
    TextView footTv;
    @BindView(R.id.loading_view)
    AVLoadingIndicatorView loadingView;
    private final String pull = "继续拖拽加载更多";
    private final String up = "松开加载更多";
    private final String noData = "暂无更多数据";

    @Override
    public View getLoadView(Context context, ViewGroup parent) {
        View loadView = LayoutInflater.from(context).inflate(R.layout.loadmore_footer, parent, false);
        ButterKnife.bind(this, loadView);
        return loadView;
    }

    @Override
    public void onPull(int currentDragHeight, int loadViewHeight, int currentLoadStatus) {
        switch (currentLoadStatus) {
            case LoadRefreshRecyclerView.LOAD_STATUS_PULL_DOWN_REFRESH:
                footTv.setText(pull);
                break;
            case LoadRefreshRecyclerView.LOAD_STATUS_LOOSEN_LOADING:
                footTv.setText(up);
                break;
        }
    }

    @Override
    public void onLoading() {
        footPut.setVisibility(View.GONE);
        footLoading.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.VISIBLE);
//        loadingView.show();
    }

    @Override
    public void onStopLoad() {
        loadingView.setVisibility(View.GONE);
        loadingView.hide();
        footLoading.setVisibility(View.GONE);
        footPut.setVisibility(View.VISIBLE);
        footTv.setText(pull);
    }

    @Override
    public void onNoData() {
        loadingView.setVisibility(View.GONE);
        loadingView.hide();
        footLoading.setVisibility(View.GONE);
        footPut.setVisibility(View.VISIBLE);
        footTv.setText(noData);
    }
}
