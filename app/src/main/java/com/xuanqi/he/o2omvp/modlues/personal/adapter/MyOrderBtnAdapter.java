package com.xuanqi.he.o2omvp.modlues.personal.adapter;

import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.modlues.personal.bean.MyOrderBtnBean;
import com.xuanqi.he.o2omvp.widget.filldatalinear.FillDataAdapter;

import java.util.List;

/**
 * @author Created by He on 2017/7/6.
 * @description
 */

public class MyOrderBtnAdapter extends FillDataAdapter<MyOrderBtnBean> implements View.OnClickListener {

    private List<MyOrderBtnBean> datas;

    public MyOrderBtnAdapter(List<MyOrderBtnBean> datas) {
        super(datas);
        this.datas = datas;
    }

    @Override
    public View getView(LinearLayout parent, int position, MyOrderBtnBean btnBean) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_order_btn_item, parent, false);
        TextView tvBtn = (TextView) view.findViewById(R.id.item_btn);
        tvBtn.setText(btnBean.getText());
        tvBtn.setId(btnBean.getId());
        if (position == datas.size() - 1) {
            tvBtn.setBackgroundResource(R.drawable.red_white_shape);
            tvBtn.setTextColor(ContextCompat.getColor(parent.getContext(), R.color.red));
        } else {
            tvBtn.setBackgroundResource(R.drawable.gray_white_shape);
            tvBtn.setTextColor(ContextCompat.getColor(parent.getContext(), R.color.light_gray));
        }
        tvBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            mListener.onOrderClick(v);
        }
    }

    private OnOrderClickListener mListener;

    public interface OnOrderClickListener {
        void onOrderClick(View view);
    }

    public void setOnOrderClickListener(OnOrderClickListener listener) {
        this.mListener = listener;
    }
}
