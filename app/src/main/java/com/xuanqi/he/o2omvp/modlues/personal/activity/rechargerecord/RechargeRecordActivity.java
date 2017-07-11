package com.xuanqi.he.o2omvp.modlues.personal.activity.rechargerecord;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.modlues.personal.adapter.RechargeRecordAdapter;
import com.xuanqi.he.o2omvp.modlues.personal.bean.RechargeRecordBean;
import com.xuanqi.he.o2omvp.mvp.MVPBaseActivity;
import com.xuanqi.he.o2omvp.widget.RecyclerViewLoadCreator;
import com.xuanqi.he.o2omvp.widget.RecyclerViewRefreshCreator;
import com.xuanqi.he.o2omvp.widget.recycler.LoadRefreshRecyclerView;
import com.xuanqi.he.o2omvp.widget.recycler.RefreshRecyclerView;
import com.xuanqi.he.o2omvp.widget.recycler.decoration.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class RechargeRecordActivity extends MVPBaseActivity<RechargeRecordContract.View, RechargeRecordPresenter> implements RechargeRecordContract.View {

    @BindView(R.id.m_title)
    TextView mTitle;
    @BindView(R.id.m_back)
    ImageView mBack;
    @BindView(R.id.recycler_view)
    LoadRefreshRecyclerView mRecyclerView;
    @BindView(R.id.root)
    LinearLayout mRoot;
    private List<RechargeRecordBean> mList;
    private RechargeRecordAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recharge_record;
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mRecyclerView.setOnLoadMoreListener(new LoadRefreshRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoad() {
                mPresenter.requestData(10);
            }
        });

        mRecyclerView.setOnRefreshListener(new RefreshRecyclerView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.requestData(5);
            }
        });
    }

    @Override
    protected void initData() {
        mTitle.setText("充值记录");
        mList = new ArrayList<>();
        mPresenter.requestData(10);
    }

    @OnClick(R.id.m_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onRequestSuccess(List<RechargeRecordBean> list) {
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RechargeRecordAdapter(this, mList, R.layout.recharge_record_list_item);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addRefreshViewCreator(new RecyclerViewRefreshCreator());
        mRecyclerView.addLoadViewCreator(new RecyclerViewLoadCreator());
    }

    @Override
    public void onRequestFailed(String message) {

    }
}
