package com.xuanqi.he.o2omvp.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.widget.recycler.CommonRecyclerAdapter;
import com.xuanqi.he.o2omvp.widget.recycler.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by He on 2017/6/15.
 * @description
 */

public class TestPopupWindow extends PopupWindow {

    private View targetView;
    private List<String> mList;
    private List<String> mDatas;
    private Context mContext;
    private final RecyclerView mLeftRv;
    private final RecyclerView mRightRv;

    public TestPopupWindow(View targetView) {
        this.mContext = targetView.getContext();
        this.targetView = targetView;
        View view = LayoutInflater.from(mContext).inflate(R.layout.popup_test, null);
        mLeftRv = (RecyclerView) view.findViewById(R.id.left_recycler_view);
        mRightRv = (RecyclerView) view.findViewById(R.id.right_recycler_view);

        mList = new ArrayList<>();
        mDatas = new ArrayList<>();

        initData();

        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setContentView(view);
        setBackgroundDrawable(new ColorDrawable(0x00000000));
        setOutsideTouchable(true);
    }

    private void initData() {
        for (int i = 'A'; i < 'F'; i++) {
            mList.add("左标题" + (char) i);
        }

        for (int i = 'A'; i < 'K'; i++) {
            mDatas.add("内容" + (char) i);
        }

        mLeftRv.setLayoutManager(new LinearLayoutManager(mContext));
        CommonRecyclerAdapter<String> leftAdapter = new CommonRecyclerAdapter<String>(mContext, mList, android.R.layout.simple_list_item_1) {
            @Override
            public void convert(RecyclerViewHolder holder,int position, String item) {
                holder.setText(android.R.id.text1, item);
            }
        };
        mLeftRv.setAdapter(leftAdapter);

        mRightRv.setLayoutManager(new LinearLayoutManager(mContext));
        CommonRecyclerAdapter<String> rightAdapter = new CommonRecyclerAdapter<String>(mContext, mDatas, android.R.layout.simple_list_item_1) {
            @Override
            public void convert(RecyclerViewHolder holder,int position, String item) {
                holder.setText(android.R.id.text1, item);
            }
        };
        mRightRv.setAdapter(rightAdapter);
    }

    public void showPopupWindow() {
        showAsDropDown(targetView);
    }
}
