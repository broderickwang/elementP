package com.xuanqi.he.o2omvp.modlues.main.fragment.commodity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.PointF;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.RelativeLayout;

import com.se7en.utils.DeviceUtils;
import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.modlues.main.activity.BusinessmenActivity;
import com.xuanqi.he.o2omvp.modlues.main.adapter.CommodityAdapter;
import com.xuanqi.he.o2omvp.modlues.main.adapter.StickHeaderAdapter;
import com.xuanqi.he.o2omvp.modlues.main.bean.CommodityBean;
import com.xuanqi.he.o2omvp.modlues.main.bean.LeftTitleBean;
import com.xuanqi.he.o2omvp.modlues.main.bean.ShopCart;
import com.xuanqi.he.o2omvp.mvp.MVPLazyLoadFragment;
import com.xuanqi.he.o2omvp.utils.LogUtils;
import com.xuanqi.he.o2omvp.widget.FakeAddImageView;
import com.xuanqi.he.o2omvp.widget.PointFTypeEvaluator;
import com.xuanqi.he.o2omvp.widget.ShoppingView;
import com.xuanqi.he.o2omvp.widget.recycler.OnItemClickListener;
import com.xuanqi.he.o2omvp.widget.recycler.decoration.DividerItemDecoration;
import com.xuanqi.he.o2omvp.widget.recycler.decoration.SectionBean;
import com.xuanqi.he.o2omvp.widget.recycler.decoration.StickyHeaderDecoration;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class CommodityFragment extends MVPLazyLoadFragment<CommodityContract.View, CommodityPresenter> implements CommodityContract.View, StickHeaderAdapter.OnShoppingClickListener {

    @BindView(R.id.title_recycler_view)
    RecyclerView mTitleRecyclerView;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.root)
    RelativeLayout mRoot;
    private List<CommodityBean> mData;
    private LinearLayoutManager mLayoutManager;
    private int selectPosition;
    private boolean isCan;
    private int lastPosition;
    private List<LeftTitleBean> mList;
    private LinearLayoutManager mTitleManager;
    private ShopCart mShopCart;
    private StickHeaderAdapter mAdapter;
    private int stickHeight = 30;
    private int lineHeight;
    private CommodityAdapter mTitleAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_commodity;
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isCan) {
                    int firstPosition = mLayoutManager.findFirstVisibleItemPosition();
                    if (!(mData.get(firstPosition).getCategory()).equals(mList.get(lastPosition).getTitle())) {
                        if (dy > 0) {
                            scrollLeftList(lastPosition, true);
                        } else {
                            scrollLeftList(lastPosition, false);
                        }
                    }
                }
            }
        });
        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                isCan = true;
                return false;
            }
        });
    }

    @Override
    protected void initData() {
        lineHeight = getResources().getDimensionPixelSize(R.dimen.line_height);
        mPresenter.requestData();
    }

    private void initLeftRecyclerView() {
        mList = new ArrayList<>();
        Set<String> set = new LinkedHashSet<>();
        for (CommodityBean bean : mData) {
            set.add(bean.getCategory());
        }

        boolean isFirst = true;
        for (String title : set) {
            LeftTitleBean bean = new LeftTitleBean();
            bean.setTitle(title);
            if (isFirst) {
                isFirst = false;
                bean.setChecked(true);
            } else {
                bean.setChecked(false);
            }
            mList.add(bean);
        }

        mTitleManager = new LinearLayoutManager(getContext());
        mTitleManager.setOrientation(LinearLayoutManager.VERTICAL);
        mTitleRecyclerView.setLayoutManager(mTitleManager);
        mTitleAdapter = new CommodityAdapter(getContext(), mList, R.layout.commodity_left_list_item);
        mTitleAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                isCan = false;
                for (int i = 0; i < mData.size(); i++) {
                    if (mData.get(i).getCategory().equals(mList.get(position).getTitle())) {
                        selectPosition = i;
                        break;
                    }
                }
                scrollPosition(selectPosition, Math.abs(lastPosition - position));

                mList.get(lastPosition).setChecked(false);
                mList.get(position).setChecked(true);
                mTitleAdapter.notifyDataSetChanged();
                lastPosition = position;

            }
        });
        mTitleRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        mTitleRecyclerView.setAdapter(mTitleAdapter);
    }

    private void scrollPosition(int index, int interval) {
        int firstPosition = mLayoutManager.findFirstVisibleItemPosition();
        int lastPosition = mLayoutManager.findLastVisibleItemPosition();
        if (index <= firstPosition) {
            int scrollY = getScrollY(true, firstPosition, lastPosition, index, interval);
            mRecyclerView.scrollBy(0, scrollY);
        } else if (index <= lastPosition) {
            LogUtils.e("中");
            int top = mRecyclerView.getChildAt(index - firstPosition).getTop();
            mRecyclerView.scrollBy(0, top - DeviceUtils.dip2px(stickHeight));
        } else {
            int scrollY = getScrollY(false, firstPosition, lastPosition, index, interval);
            mRecyclerView.scrollBy(0, scrollY);
        }
    }

    private int getScrollY(boolean isUp, int firstPosition, int lastPosition, int index, int interval) {
        int itemHeight = 0;
        for (int i = firstPosition; i < lastPosition; i++) {
            View view = mLayoutManager.findViewByPosition(i);
            SectionBean tag = (SectionBean) view.getTag();
            if (!tag.isGroupStart()) {
                itemHeight = view.getHeight();
                break;
            }
        }
        View firstView = mRecyclerView.getChildAt(0);
        View secView = mRecyclerView.getChildAt(1);
        SectionBean tag = (SectionBean) firstView.getTag();
//        int firstHeight = itemHeight + DeviceUtils.dip2px(stickHeight + lineHeight);
        int firstHeight = itemHeight + DeviceUtils.dip2px(stickHeight)+lineHeight;
        if (tag.isGroupEnd()) {
            firstHeight = itemHeight + DeviceUtils.dip2px(stickHeight) * 2;
        }
        LogUtils.e(isUp + "firstHeight=" + firstHeight + " secView.getTop()=" + secView.getTop());
        int i = Math.abs(index - firstPosition);
        int scrollY;
        if (isUp) {
            scrollY = -(itemHeight * i
                    + DeviceUtils.dip2px(stickHeight) * interval
                    + (firstHeight - secView.getTop())
//                    + (i - interval) * DeviceUtils.dip2px(lineHeight));
                    + (i - interval) * lineHeight);
        } else {
            scrollY = itemHeight * i
                    + DeviceUtils.dip2px(stickHeight) * interval
                    - (firstHeight - secView.getTop())
//                    + (i - interval) * DeviceUtils.dip2px(lineHeight);
                    + (i - interval) * lineHeight;
        }

        return scrollY;
    }

    private void scrollLeftList(int position, boolean isUp) {
        int firstVisibleItemPosition = mTitleManager.findFirstCompletelyVisibleItemPosition();
        int lastVisibleItemPosition = mTitleManager.findLastCompletelyVisibleItemPosition();
        int currentIndex = isUp ? position + 1 : position - 1;
        mList.get(position).setChecked(false);
        mList.get(currentIndex).setChecked(true);
        mTitleAdapter.notifyDataSetChanged();
        if (firstVisibleItemPosition > currentIndex || currentIndex > lastVisibleItemPosition) {
            mTitleManager.scrollToPosition(currentIndex);
        }
        lastPosition = currentIndex;

    }

    private void initRightRecyclerView() {
        mShopCart = new ShopCart();
        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new StickHeaderAdapter(getContext(), mData, mShopCart, R.layout.commodity_right_list_item);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                LogUtils.e("name=" + mData.get(position).getName());
            }
        });
        mAdapter.setOnShoppingClickListener(this);
        int color = ContextCompat.getColor(getContext(), R.color.window_background);
        StickyHeaderDecoration decoration = new StickyHeaderDecoration(stickHeight, color, getContext());
        decoration.setDatas(mData);
        mRecyclerView.addItemDecoration(decoration);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onAddClick(View view, final int position, float price) {
        BusinessmenActivity activity = (BusinessmenActivity) getActivity();
        int[] cartLocation = activity.getCartLocation();
        int[] addLocation = new int[2];
        view.getLocationInWindow(addLocation);
        int[] recyclerLocation = new int[2];
        mRecyclerView.getLocationInWindow(recyclerLocation);

        LogUtils.e("addLocation=" + addLocation[0] + " addLocation[1]=" + addLocation[1]);
        PointF startP = new PointF();
        PointF endP = new PointF();
        PointF controlP = new PointF();

        startP.x = addLocation[0] + (view.getWidth() - view.getHeight());
        startP.y = addLocation[1] - recyclerLocation[1];
        endP.x = cartLocation[0];
        endP.y = cartLocation[1] - recyclerLocation[1];
        controlP.x = endP.x;
        controlP.y = startP.y;

        final FakeAddImageView imageView = new FakeAddImageView(getContext());
        mRoot.addView(imageView);
        imageView.setImageResource(R.mipmap.icon_add);
        imageView.getLayoutParams().width = view.getHeight();
        imageView.getLayoutParams().height = view.getHeight();
        imageView.setVisibility(View.VISIBLE);
        ObjectAnimator addAnimator = ObjectAnimator.ofObject(imageView, "mPointF",
                new PointFTypeEvaluator(controlP), startP, endP);
        addAnimator.setInterpolator(new AccelerateInterpolator());
        addAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                imageView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                imageView.setVisibility(View.GONE);
                mRoot.removeView(imageView);
            }
        });
        addAnimator.setDuration(500);
        addAnimator.start();
        activity.showTotalPrice(mShopCart);

    }

    @Override
    public void onDeleteClick(View view, int position, float price) {
        ((BusinessmenActivity) getActivity()).showTotalPrice(mShopCart);
    }

    public void notifyRightAdapter(int num, String name) {
        for (int i = 0; i < mData.size(); i++) {
            if (name.equals(mData.get(i).getName())) {
                LogUtils.e("notifyRightAdapter i=" + i + "num=" + num);
                View itemView = mLayoutManager.findViewByPosition(i);
                if (itemView != null) {
                    ShoppingView shoppingView = (ShoppingView) itemView.findViewById(R.id.shopping_view);
                    shoppingView.setTextNum(num);
                } else {
                    mAdapter.notifyItemChanged(i);
                }
                break;
            }
        }
    }

    public void refreshRightAdapter() {
        for (int i = 0; i < mData.size(); i++) {
            mData.get(i).setNum(0);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestSuccess(List<CommodityBean> list) {
        this.mData = list;
        initLeftRecyclerView();
        initRightRecyclerView();
    }

    @Override
    public void onRequestFailed(String message) {

    }
}
