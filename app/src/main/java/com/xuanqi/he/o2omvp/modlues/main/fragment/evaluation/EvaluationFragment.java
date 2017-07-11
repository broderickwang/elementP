package com.xuanqi.he.o2omvp.modlues.main.fragment.evaluation;

import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.modlues.main.adapter.EvaluationAdapter;
import com.xuanqi.he.o2omvp.modlues.main.bean.EvaluationBean;
import com.xuanqi.he.o2omvp.mvp.MVPLazyLoadFragment;
import com.xuanqi.he.o2omvp.widget.RecyclerViewLoadCreator;
import com.xuanqi.he.o2omvp.widget.recycler.LoadRefreshRecyclerView;
import com.xuanqi.he.o2omvp.widget.recycler.decoration.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class EvaluationFragment extends MVPLazyLoadFragment<EvaluationContract.View, EvaluationPresenter> implements EvaluationContract.View {

    @BindView(R.id.recycler_view)
    LoadRefreshRecyclerView mRecyclerView;
    private List<EvaluationBean> mList;
    private EvaluationAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_evaluation;
    }

    @Override
    public void initEvent() {
        super.initEvent();
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

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new EvaluationAdapter(getContext(), mList, R.layout.evaluation_list_item);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

        mRecyclerView.setAdapter(mAdapter);

        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.evaluation_list_header, mRecyclerView, false);
        mRecyclerView.addHeaderView(headerView);

        mRecyclerView.addLoadViewCreator(new RecyclerViewLoadCreator());

    }

    @Override
    public void onRequestSuccess(List<EvaluationBean> list) {
        mList.addAll(list);
        if (mRecyclerView.getChildCount() == 0) {
            initRecyclerView();
        } else {
            mRecyclerView.onStopLoad(list.size(),10);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRequestFailed(String message) {

    }
}
