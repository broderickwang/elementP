package com.xuanqi.he.o2omvp.modlues.personal.fragment.receiveorders;


import android.support.v7.widget.LinearLayoutManager;
import android.widget.LinearLayout;

import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.modlues.personal.adapter.ReceiveOrdersAdapter;
import com.xuanqi.he.o2omvp.modlues.personal.bean.ReceiveOrdersBean;
import com.xuanqi.he.o2omvp.mvp.MVPLazyLoadFragment;
import com.xuanqi.he.o2omvp.utils.LogUtils;
import com.xuanqi.he.o2omvp.widget.RecyclerViewLoadCreator;
import com.xuanqi.he.o2omvp.widget.RecyclerViewRefreshCreator;
import com.xuanqi.he.o2omvp.widget.recycler.LoadRefreshRecyclerView;
import com.xuanqi.he.o2omvp.widget.recycler.RefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ReceiveOrdersFragment extends MVPLazyLoadFragment<ReceiveOrdersContract.View, ReceiveOrdersPresenter> implements ReceiveOrdersContract.View {

    @BindView(R.id.recycler_view)
    LoadRefreshRecyclerView mRecyclerView;
    @BindView(R.id.root)
    LinearLayout mRoot;
    private List<ReceiveOrdersBean> mList;
    private ReceiveOrdersAdapter mAdapter;
    private String TAG = "ReceiveOrdersFragment";

    @Override
    protected int getLayoutId() {
        return R.layout.fragmnet_receive_orders;
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mRecyclerView.setOnRefreshListener(new RefreshRecyclerView.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                mPresenter.requestFailed();
                mPresenter.requestData(10);
            }
        });
        mRecyclerView.setOnLoadMoreListener(new LoadRefreshRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoad() {
//                mPresenter.requestFailed();
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
    public void onRequestSuccess(List<ReceiveOrdersBean> list) {
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
        mAdapter = new ReceiveOrdersAdapter(getContext(), mList, R.layout.task_order_list_item);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addRefreshViewCreator(new RecyclerViewRefreshCreator());
        mRecyclerView.addLoadViewCreator(new RecyclerViewLoadCreator());
    }

    @Override
    public void onRequestFailed(String message) {
        LogUtils.e(TAG, "onRequestFailed:" + message);
        if (mRecyclerView.isRefreshing()) {
            mRecyclerView.onStopRefreshFailed();
        }
        mRecyclerView.onStopLoad();
    }
}
