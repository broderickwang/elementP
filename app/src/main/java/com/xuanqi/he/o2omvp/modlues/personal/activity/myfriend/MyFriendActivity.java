package com.xuanqi.he.o2omvp.modlues.personal.activity.myfriend;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.se7en.utils.DeviceUtils;
import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.modlues.personal.activity.SearchFriendActivity;
import com.xuanqi.he.o2omvp.modlues.personal.adapter.FriendAdapter;
import com.xuanqi.he.o2omvp.modlues.personal.adapter.FriendHeaderDecoration;
import com.xuanqi.he.o2omvp.modlues.personal.bean.FriendBean;
import com.xuanqi.he.o2omvp.mvp.MVPBaseActivity;
import com.xuanqi.he.o2omvp.widget.CircleTextView;
import com.xuanqi.he.o2omvp.widget.SideView;
import com.xuanqi.he.o2omvp.widget.recycler.WrapRecyclerView;
import com.xuanqi.he.o2omvp.widget.recycler.decoration.SectionBean;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MyFriendActivity extends MVPBaseActivity<MyFriendContract.View, MyFriendPresenter> implements MyFriendContract.View, SideView.OnTextClickListener {

    @BindView(R.id.m_title)
    TextView mTitle;
    @BindView(R.id.m_back)
    ImageView mBack;
    @BindView(R.id.m_right)
    TextView mRight;
    @BindView(R.id.tv_search)
    TextView mTvSearch;
    @BindView(R.id.recycler_view)
    WrapRecyclerView mRecyclerView;
    @BindView(R.id.slide_view)
    SideView mSlideView;
    @BindView(R.id.activity_my_friend)
    LinearLayout mRoot;
    @BindView(R.id.tv_circle)
    CircleTextView mTvCircle;
    private List<FriendBean> mList;
    private LinearLayoutManager mLinearLayoutManager;
    private List<String> mPinyinList;
    private int stickHeight = 25;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_friend;
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mSlideView.setOnTextClickListener(this);
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    protected void initData() {
        mTitle.setText("我的好友");
        mRight.setVisibility(View.VISIBLE);
        mRight.setText("附近的人");

        mPresenter.requestData();
    }

    private void initRecyclerView() {
        mPinyinList = new ArrayList<>();
        Set<String> set = new LinkedHashSet<>();
        for (FriendBean bean : mList) {
            set.add(bean.getCategory());
        }

        mPinyinList.addAll(set);
        mSlideView.setData(mPinyinList);

        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        FriendAdapter adapter = new FriendAdapter(this, mList, R.layout.friend_list_item);
        int color = ContextCompat.getColor(this, R.color.window_background);
        FriendHeaderDecoration decoration = new FriendHeaderDecoration(stickHeight, color, this);
        decoration.setDatas(mList);
        mRecyclerView.addItemDecoration(decoration);
        mRecyclerView.setAdapter(adapter);

        View footerView = LayoutInflater.from(this).inflate(R.layout.friend_list_footer, mRecyclerView, false);
        TextView tvCount = (TextView) footerView.findViewById(R.id.tv_count);
        tvCount.setText(mList.size() + "位好友");
        SectionBean tag = new SectionBean();
        tag.setHeader(true);
        footerView.setTag(tag);
        mRecyclerView.addFooterView(footerView);
    }

    @OnClick({R.id.m_back, R.id.m_right, R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.m_back:
                finish();
                break;
            case R.id.m_right:
                break;
            case R.id.tv_search:
                startActivity(new Intent(this,SearchFriendActivity.class));
                overridePendingTransition(0,0);
                break;
        }
    }

    @Override
    public void showTextView(String textView, boolean dismiss, int position) {
        if (dismiss) {
            mTvCircle.setVisibility(View.GONE);
        } else {
            mTvCircle.setVisibility(View.VISIBLE);
            mTvCircle.setText(textView);
        }

        int selectPosition = 0;
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getCategory().equals(textView)) {
                selectPosition = i;
                break;
            }
        }
        scrollToPosition(selectPosition);
    }

    private void scrollToPosition(int index) {
        int firstPosition = mLinearLayoutManager.findFirstVisibleItemPosition();
        int lastPosition = mLinearLayoutManager.findLastVisibleItemPosition();
        if (index <= firstPosition) {
            mRecyclerView.scrollToPosition(index);
        } else if (index <= lastPosition) {
            int top = mRecyclerView.getChildAt(index - firstPosition).getTop();
            mRecyclerView.scrollBy(0, top - DeviceUtils.dip2px(stickHeight));
        } else {
            mRecyclerView.scrollToPosition(index);
        }
    }

    @Override
    public void onRequestSuccess(List<FriendBean> list) {
        this.mList = list;
        initRecyclerView();
    }

    @Override
    public void onRequestFailed(String message) {

    }
}
