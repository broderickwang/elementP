package com.xuanqi.he.o2omvp.modlues.personal.activity.orderevaluation;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.modlues.personal.adapter.OrderEvaluationAdapter;
import com.xuanqi.he.o2omvp.modlues.personal.bean.OrderEvaluationBean;
import com.xuanqi.he.o2omvp.mvp.MVPBaseActivity;
import com.xuanqi.he.o2omvp.widget.recycler.WrapRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class OrderEvaluationActivity extends MVPBaseActivity<OrderEvaluationContract.View, OrderEvaluationPresenter> implements OrderEvaluationContract.View {

    @BindView(R.id.m_title)
    TextView mTitle;
    @BindView(R.id.m_back)
    ImageView mBack;
    @BindView(R.id.recycler_view)
    WrapRecyclerView mRecyclerView;
    @BindView(R.id.root)
    LinearLayout mRoot;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_evaluation;
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    protected void initData() {
        mTitle.setText("订单评价");
        mPresenter.requestData();
    }

    @OnClick({R.id.m_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.m_back:
                finish();
                break;
        }
    }

    @Override
    public void onRequestSuccess(List<OrderEvaluationBean> list) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        OrderEvaluationAdapter adapter = new OrderEvaluationAdapter(this, list, R.layout.order_evaluation_list_item);
        mRecyclerView.setAdapter(adapter);
        View footerView = LayoutInflater.from(this).inflate(R.layout.order_evaluation_footer, mRecyclerView, false);
        mRecyclerView.addFooterView(footerView);
    }

    @Override
    public void onRequestFailed(String message) {

    }
}
