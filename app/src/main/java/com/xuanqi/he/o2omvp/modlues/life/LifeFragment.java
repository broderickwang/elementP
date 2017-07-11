package com.xuanqi.he.o2omvp.modlues.life;


import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.modlues.life.adapter.LifeAdapter;
import com.xuanqi.he.o2omvp.modlues.life.bean.LifeBean;
import com.xuanqi.he.o2omvp.mvp.MVPBaseFragment;
import com.xuanqi.he.o2omvp.widget.BannerImageLoader;
import com.xuanqi.he.o2omvp.widget.TestPopupWindow;
import com.xuanqi.he.o2omvp.widget.recycler.LoadRefreshRecyclerView;
import com.xuanqi.he.o2omvp.widget.recycler.decoration.DividerItemDecoration;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LifeFragment extends MVPBaseFragment<LifeContract.View, LifePresenter> implements LifeContract.View {

    @BindView(R.id.tv_posting)
    TextView mTvPosting;
    @BindView(R.id.iv_more)
    ImageView mIvMore;
    @BindView(R.id.recycler_view)
    LoadRefreshRecyclerView mRecyclerView;
    private Banner mBanner;
    private int[] arr = new int[]{R.mipmap.test_img_ad01, R.mipmap.test_img_ad02, R.mipmap.test_img_ad03};
    private List<LifeBean> mList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_life;
    }

    @Override
    protected void initData() {
        mPresenter.requestData();
    }

    @OnClick({R.id.tv_posting, R.id.iv_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_posting:
                TestPopupWindow popupWindow = new TestPopupWindow(view);
                popupWindow.showPopupWindow();
                break;
            case R.id.iv_more:
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        mBanner.stopAutoPlay();
    }

    @Override
    public void onRequestSuccess(List<LifeBean> list) {
        this.mList = list;
        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        LifeAdapter mAdapter = new LifeAdapter(getContext(), mList, R.layout.life_list_item);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);

        List<Integer> imageList = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            imageList.add(arr[i]);
        }
        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.life_list_header, mRecyclerView, false);
        mBanner = (Banner) headerView.findViewById(R.id.banner);
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        mBanner.setImages(imageList);
        mBanner.setImageLoader(new BannerImageLoader());
        mBanner.start();

        mRecyclerView.addHeaderView(headerView);
    }

    @Override
    public void onRequestFailed(String message) {

    }
}
