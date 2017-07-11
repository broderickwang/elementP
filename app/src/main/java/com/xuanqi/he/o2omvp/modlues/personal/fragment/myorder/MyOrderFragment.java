package com.xuanqi.he.o2omvp.modlues.personal.fragment.myorder;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.modlues.personal.activity.orderdetails.OrderDetailsActivity;
import com.xuanqi.he.o2omvp.modlues.personal.activity.orderevaluation.OrderEvaluationActivity;
import com.xuanqi.he.o2omvp.modlues.personal.adapter.MyOrderAdapter;
import com.xuanqi.he.o2omvp.modlues.personal.bean.MyOrderBean;
import com.xuanqi.he.o2omvp.mvp.MVPLazyLoadFragment;
import com.xuanqi.he.o2omvp.utils.LogUtils;
import com.xuanqi.he.o2omvp.widget.RecyclerViewLoadCreator;
import com.xuanqi.he.o2omvp.widget.RecyclerViewRefreshCreator;
import com.xuanqi.he.o2omvp.widget.recycler.LoadRefreshRecyclerView;
import com.xuanqi.he.o2omvp.widget.recycler.OnItemClickListener;
import com.xuanqi.he.o2omvp.widget.recycler.RefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MyOrderFragment extends MVPLazyLoadFragment<MyOrderContract.View, MyOrderPresenter> implements MyOrderContract.View, MyOrderAdapter.OnItemBtnClickListener, OnItemClickListener {

    @BindView(R.id.recycler_view)
    LoadRefreshRecyclerView mRecyclerView;
    private List<MyOrderBean> mList;
    private MyOrderAdapter mAdapter;
    private String TAG = "MyOrderFragment";

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_order;
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mRecyclerView.setOnRefreshListener(new RefreshRecyclerView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.requestData(10);
            }
        });

        mRecyclerView.setOnLoadMoreListener(new LoadRefreshRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoad() {
                mPresenter.requestData(5);
            }
        });
    }

    @Override
    protected void initData() {
        mList = new ArrayList<>();
        mPresenter.requestData(10);
    }

    @Override
    public void onRequestSuccess(List<MyOrderBean> list) {
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

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new MyOrderAdapter(getContext(), mList, R.layout.my_order_list_item);
        mAdapter.setOnItemBtnClickListener(this);
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addRefreshViewCreator(new RecyclerViewRefreshCreator());
        mRecyclerView.addLoadViewCreator(new RecyclerViewLoadCreator());
    }

    @Override
    public void onRequestFailed(String message) {

    }

    @Override
    public void onItemBtnClick(View view, int position) {
        switch (view.getId()) {
            case R.id.btn_after_sale:
                LogUtils.e(TAG, "退款售后->position=" + position);
                break;
            case R.id.btn_evaluation:
                LogUtils.e(TAG, "评价晒单->position=" + position);
                startActivity(new Intent(getContext(), OrderEvaluationActivity.class));
                break;
            case R.id.btn_buy_again:
                LogUtils.e(TAG, "再次购买->position=" + position);
                break;
            case R.id.btn_view_logistics:
                LogUtils.e(TAG, "查看物流->position=" + position);
                break;
            case R.id.btn_immediate_payment:
                LogUtils.e(TAG, "立即付款->position=" + position);
                break;
        }
    }

    @Override
    public void onItemClick(int position) {
        startActivity(new Intent(getContext(), OrderDetailsActivity.class));
    }
}
