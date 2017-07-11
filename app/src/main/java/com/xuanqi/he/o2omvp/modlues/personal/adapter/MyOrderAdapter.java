package com.xuanqi.he.o2omvp.modlues.personal.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.TextView;

import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.modlues.personal.bean.MyOrderBean;
import com.xuanqi.he.o2omvp.modlues.personal.bean.MyOrderBtnBean;
import com.xuanqi.he.o2omvp.widget.filldatalinear.FillDataLinearLayout;
import com.xuanqi.he.o2omvp.widget.recycler.CommonRecyclerAdapter;
import com.xuanqi.he.o2omvp.widget.recycler.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by He on 2017/7/6.
 * @description
 */

public class MyOrderAdapter extends CommonRecyclerAdapter<MyOrderBean> {

    private List<MyOrderBtnBean> mBtnList;

    public MyOrderAdapter(Context context, List<MyOrderBean> data, @LayoutRes int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(final RecyclerViewHolder holder, final int position, MyOrderBean myOrderBean) {
        holder.setText(R.id.item_name, myOrderBean.getTitle());
        FillDataLinearLayout llCommodityBox = holder.getView(R.id.ll_commodity_box);
        MyOrderCommodityAdapter commodityAdapter = new MyOrderCommodityAdapter(myOrderBean.getList());
        llCommodityBox.setAdapter(commodityAdapter);

        TextView tvStatus = holder.getView(R.id.item_status);
        FillDataLinearLayout llBtnBox = holder.getView(R.id.ll_btn_box);
        mBtnList = new ArrayList<>();
        int status = myOrderBean.getStatus();
        switch (status) {
            case 0:
                initOrderCompleted();
                tvStatus.setText("已完成");
                break;
            case 1:
                initOrderPayment();
                tvStatus.setText("已付款");
                break;
            case 2:
                initOrderNoPayment();
                tvStatus.setText("待付款");
                break;
        }
        MyOrderBtnAdapter btnAdapter = new MyOrderBtnAdapter(mBtnList);
        btnAdapter.setOnOrderClickListener(new MyOrderBtnAdapter.OnOrderClickListener() {
            @Override
            public void onOrderClick(View view) {
                if (mListener != null) {
                    mListener.onItemBtnClick(view, position);
                }
            }
        });
        llBtnBox.setAdapter(btnAdapter);
    }

    private OnItemBtnClickListener mListener;

    public interface OnItemBtnClickListener {
        void onItemBtnClick(View view, int position);
    }

    public void setOnItemBtnClickListener(OnItemBtnClickListener listener) {
        this.mListener = listener;
    }

    private void initOrderNoPayment() {
        for (int i = 0; i < 2; i++) {
            MyOrderBtnBean btnBean = new MyOrderBtnBean();
            if (i == 0) {
                btnBean.setId(R.id.btn_after_sale);
                btnBean.setText("退款/售后");
            } else {
                btnBean.setId(R.id.btn_immediate_payment);
                btnBean.setText("立即付款");
            }
            mBtnList.add(btnBean);
        }
    }

    private void initOrderPayment() {
        for (int i = 0; i < 2; i++) {
            MyOrderBtnBean btnBean = new MyOrderBtnBean();
            if (i == 0) {
                btnBean.setId(R.id.btn_after_sale);
                btnBean.setText("退款/售后");
            } else {
                btnBean.setId(R.id.btn_view_logistics);
                btnBean.setText("查看物流");
            }
            mBtnList.add(btnBean);
        }
    }

    private void initOrderCompleted() {
        for (int i = 0; i < 3; i++) {
            MyOrderBtnBean btnBean = new MyOrderBtnBean();
            if (i == 0) {
                btnBean.setId(R.id.btn_after_sale);
                btnBean.setText("退款/售后");
            } else if (i == 1) {
                btnBean.setId(R.id.btn_evaluation);
                btnBean.setText("评价晒单");
            } else {
                btnBean.setId(R.id.btn_buy_again);
                btnBean.setText("再次购买");
            }
            mBtnList.add(btnBean);
        }
    }
}
