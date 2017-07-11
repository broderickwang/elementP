package com.xuanqi.he.o2omvp.modlues.main;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.baidu.location.Poi;
import com.se7en.utils.DeviceUtils;
import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.baidu.BaiDuLocationListener;
import com.xuanqi.he.o2omvp.baidu.LocationService;
import com.xuanqi.he.o2omvp.constant.Key;
import com.xuanqi.he.o2omvp.modlues.main.activity.BusinessmenActivity;
import com.xuanqi.he.o2omvp.modlues.main.activity.search.SearchActivity;
import com.xuanqi.he.o2omvp.modlues.main.adapter.MainAdapter;
import com.xuanqi.he.o2omvp.modlues.main.adapter.MainRefreshCreator;
import com.xuanqi.he.o2omvp.modlues.main.bean.MainBean;
import com.xuanqi.he.o2omvp.mvp.MVPBaseFragment;
import com.xuanqi.he.o2omvp.utils.LogUtils;
import com.xuanqi.he.o2omvp.widget.RecyclerViewLoadCreator;
import com.xuanqi.he.o2omvp.widget.recycler.LoadRefreshRecyclerView;
import com.xuanqi.he.o2omvp.widget.recycler.OnItemClickListener;
import com.xuanqi.he.o2omvp.widget.recycler.RefreshRecyclerView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MainFragment extends MVPBaseFragment<MainContract.View, MainPresenter> implements MainContract.View, BaiDuLocationListener.OnLocationCompletedListener {

    @BindView(R.id.m_recycler_view)
    LoadRefreshRecyclerView mRecyclerView;
    @BindView(R.id.tv_search)
    TextView mTvSearch;
    @BindView(R.id.rl_header_cover)
    RelativeLayout mRlHeaderCover;
    @BindView(R.id.root)
    RelativeLayout mRoot;
    @BindView(R.id.fl_cart_layout)
    FloatingActionButton mFlShoppingCar;
    private List<MainBean> mList;
    private int totalDy;
    private View mHeaderView, mHeaderView2;
    private TextView mHeaderAddress;
    private ImageView mHeaderNotice;
    private TextView mHeaderSearch;
    private TextView mHeaderGoods;
    private int addressHeight = DeviceUtils.dip2px(35);
    private int tenHeight = DeviceUtils.dip2px(10);
    private int rlHeight = DeviceUtils.dip2px(120);
    private boolean isCompleted = true;
    private boolean isFirst = false;
    private boolean isTrueCompleted = false;
    private ValueAnimator animator;
    private MyHandler mHandler;
    private ViewFlipper mViewFlipper;
    private LocationService mLocationService;
    private BaiDuLocationListener mListener;
    private MainAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                boolean isScroll = mRecyclerView.canScrollVertically(1);
                if (isScroll) {
                    if (isFirst && isCompleted) {
                        isCompleted = false;
                        transformation(true);
                    }
                    isFirst = true;
                } else {
//                    LogUtils.e("到底了dy=" + dy);
//                    if (isDrag) {
//                        totalDy -= dy;
//                    }
//                    isDrag = false;
                }
                totalDy = mHeaderView.getTop() - 1;
//                LogUtils.e("dy=" + dy + " totalDy=" + totalDy);
                if (totalDy <= 0) {
                    setGradient();
                } else {
                    totalDy = 0;
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    LogUtils.e("newState->" + isTrueCompleted);
                    Message message = mHandler.obtainMessage();
                    mHandler.sendMessageDelayed(message, 1500);
                }

            }
        });
//        mRecyclerView.setFocusable(false);
        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mHandler.removeCallbacksAndMessages(null);
                        break;
                    case MotionEvent.ACTION_UP:
//                        LogUtils.e("up->" + isTrueCompleted);
                        if (isTrueCompleted) {
                            Message message = mHandler.obtainMessage();
                            mHandler.sendMessageDelayed(message, 1500);
                        }
                        break;
                }
                return false;
            }
        });

        mRecyclerView.setOnLoadMoreListener(new LoadRefreshRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoad() {
                mPresenter.requestData(5);
            }
        });

        mRecyclerView.setOnRefreshListener(new RefreshRecyclerView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.requestData(10);
            }
        });
    }

    private void setGradient() {
        int y = Math.abs(totalDy);
        int factor = addressHeight - (addressHeight - mHeaderAddress.getHeight()) / 2 + 1;
        if (y <= factor) {
//                    LogUtils.e("totalDy=" + totalDy+"->one");
            if (y == 0) {
                mHeaderGoods.setAlpha(1.0f);
            }
            hideCover();
            mHeaderAddress.setAlpha(1.0f - ((y * 1.0f) / (factor * 1.0f)));
            mHeaderNotice.setAlpha(1.0f - ((y * 1.0f) / (factor * 1.0f)));
        } else if (y >= addressHeight + 1 && y < (addressHeight + 1 + tenHeight + mHeaderGoods.getHeight())) {
            showCover();
//                    LogUtils.e("totalDy=" + totalDy+"->two");
            mHeaderGoods.setAlpha(1.0f - (((y - (addressHeight + 1)) * 1.0f) / ((tenHeight + mHeaderGoods.getHeight()) * 1.0f)));
            if (mRlHeaderCover.getHeight() != DeviceUtils.dip2px(45) + 1) {
                ViewGroup.LayoutParams layoutParams = mRlHeaderCover.getLayoutParams();
                layoutParams.height = DeviceUtils.dip2px(45) + 1;
                mRlHeaderCover.setLayoutParams(layoutParams);
            }
        } else if (y >= (addressHeight + 1 + tenHeight + mHeaderGoods.getHeight())) {
            showCover();
//                    LogUtils.e("totalDy=" + totalDy+"->three");
            if (mRlHeaderCover.getHeight() != DeviceUtils.dip2px(55) + 1) {
                ViewGroup.LayoutParams layoutParams = mRlHeaderCover.getLayoutParams();
                layoutParams.height = DeviceUtils.dip2px(55) + 1;
                mRlHeaderCover.setLayoutParams(layoutParams);
            }
        } else {
//                    LogUtils.e("totalDy=" + totalDy+"->four");
            hideCover();
        }
    }

    @Override
    public void onSuccess(String addr, List<Poi> list) {
        mLocationService.stop();
        if (list != null) {
            mHeaderAddress.setText(list.get(0).getName());
        }
        LogUtils.e("定位成功");
    }

    @Override
    public void onError(String message) {
        mLocationService.stop();
        LogUtils.e("定位失败->" + message);
    }

    @Override
    public void onRequestSuccess(List<MainBean> list) {
        if (mRecyclerView.isRefreshing()) {
            mList.clear();
        }
        mRecyclerView.onStopRefresh();
        mList.addAll(list);
        if (mRecyclerView.getChildCount() == 0) {
            initRecyclerView();
        } else {
            mRecyclerView.onStopLoad(list.size(), 10);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRequestFailed(String message) {

    }

    private static class MyHandler extends Handler {
        private WeakReference<Fragment> mReference;

        public MyHandler(Fragment fragment) {
            mReference = new WeakReference<>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MainFragment fragment = (MainFragment) mReference.get();
            if (fragment != null) {
                if (fragment.isTrueCompleted) {
                    fragment.isTrueCompleted = false;
                    fragment.transformation(false);
                }
            }
        }
    }

    private void transformation(final boolean flag) {
        if (flag) {
            animator = ValueAnimator.ofFloat(0, 1.0f).setDuration(200);
        } else {
            animator = ValueAnimator.ofFloat(1.0f, 0).setDuration(200);
        }
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress = (float) animation.getAnimatedValue();
                mFlShoppingCar.setTranslationX(progress * mFlShoppingCar.getWidth());
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (flag) {
//                    transformation(false);
                    isTrueCompleted = true;
                } else {
                    isCompleted = true;
                }
            }
        });
        animator.start();
    }

    private void hideCover() {
        if (mRlHeaderCover.getVisibility() == View.VISIBLE) {
            mRlHeaderCover.setVisibility(View.GONE);
        }
    }

    private void showCover() {
        if (mRlHeaderCover.getVisibility() == View.GONE) {
            mRlHeaderCover.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void initData() {
        mRlHeaderCover.setPadding(DeviceUtils.dip2px(15), DeviceUtils.dip2px(10) + 1, DeviceUtils.dip2px(15), 0);
        initProperty();

        initLocation();
    }

    private void initRecyclerView() {
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mAdapter = new MainAdapter(getContext(), mList, R.layout.main_list_item);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startActivity(new Intent(getContext(), BusinessmenActivity.class));
            }
        });

//        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addRefreshViewCreator(new MainRefreshCreator());
        mRecyclerView.addLoadViewCreator(new RecyclerViewLoadCreator());
        mRecyclerView.addHeaderView(mHeaderView);
        mRecyclerView.addHeaderView(mHeaderView2);
    }

    private void initLocation() {
        mListener = new BaiDuLocationListener(this);
        mLocationService = LocationService.getInstance(getActivity().getApplicationContext());
        mLocationService.registerListener(mListener);
        mLocationService.start();
    }

    private void initProperty() {
        mHeaderView = LayoutInflater.from(getContext()).inflate(R.layout.main_list_header, mRoot, false);
        mHeaderView2 = LayoutInflater.from(getContext()).inflate(R.layout.main_list_header2, mRoot, false);
        mHeaderAddress = (TextView) mHeaderView.findViewById(R.id.tv_address);
        mHeaderNotice = (ImageView) mHeaderView.findViewById(R.id.tv_notice);
        mHeaderSearch = (TextView) mHeaderView.findViewById(R.id.tv_search);
        mHeaderGoods = (TextView) mHeaderView.findViewById(R.id.tv_goods);
        mViewFlipper = (ViewFlipper) mHeaderView.findViewById(R.id.view_flipper);

        mHeaderSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToSearch(v);
            }
        });
        for (int i = 0; i < 3; i++) {
            View view = View.inflate(getContext(), R.layout.view_flipper_item, null);
            TextView tvOne = (TextView) view.findViewById(R.id.tv_one);
            TextView tvTwo = (TextView) view.findViewById(R.id.tv_two);
            if (i == 0) {
                tvOne.setText("是绝对发货速度尽快发送" + i);
                tvTwo.setText("大是大非科技深刻的了房价" + i);
            } else if (i == 1) {
                tvOne.setText("束带结发含税单价第三方好" + i);
                tvTwo.setText("水电费合适的话范德萨发货的" + i);
            } else {
                tvOne.setText("时的合格分数的规划方法v更好" + i);
                tvTwo.setText("水电费更好的韩国的减肥更好的法规和地方" + i);
            }
            mViewFlipper.addView(view);
        }
        mList = new ArrayList<>();

        mHandler = new MyHandler(this);

        mPresenter.requestData(10);
    }

    @OnClick(R.id.tv_search)
    public void onViewClicked() {
        jumpToSearch(mTvSearch);
    }

    private void jumpToSearch(View view) {
        int abs = Math.abs(totalDy);
        LogUtils.e("abs=" + abs + "  DeviceUtils.dip2px(120 - 55)=" + DeviceUtils.dip2px(120 - 55));
        if (abs >= DeviceUtils.dip2px(120 - 55)) {
            rlHeight = DeviceUtils.dip2px(55) + 1;
            LogUtils.e("滑很多rlHeight=" + rlHeight);
        } else {
            rlHeight = DeviceUtils.dip2px(120) + 1 - abs;
            LogUtils.e("滑不多rlHeight=" + rlHeight);
        }
        Intent intent = new Intent(getContext(), SearchActivity.class);
        int[] searchLocation = new int[2];
        view.getLocationOnScreen(searchLocation);
        intent.putExtra(Key.MAIN_SEARCH_X, searchLocation[0]);
        intent.putExtra(Key.MAIN_SEARCH_Y, searchLocation[1]);
        intent.putExtra(Key.MAIN_SEARCH_BOX_HEIGHT, rlHeight);
        startActivity(intent);
        getActivity().overridePendingTransition(0, 0);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            mViewFlipper.stopFlipping();
        } else {
            mViewFlipper.startFlipping();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (animator != null) {
            animator.cancel();
        }
        mHandler.removeCallbacksAndMessages(null);
        mLocationService.unregisterListener(mListener);
        mLocationService.stop();
    }
}
