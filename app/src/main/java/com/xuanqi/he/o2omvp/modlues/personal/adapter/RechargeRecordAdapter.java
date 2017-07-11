package com.xuanqi.he.o2omvp.modlues.personal.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.modlues.personal.bean.RechargeRecordBean;
import com.xuanqi.he.o2omvp.widget.recycler.CommonRecyclerAdapter;
import com.xuanqi.he.o2omvp.widget.recycler.RecyclerViewHolder;

import java.util.List;

/**
 * @author Created by He on 2017/7/1.
 * @description
 */

public class RechargeRecordAdapter extends CommonRecyclerAdapter<RechargeRecordBean> {

    public RechargeRecordAdapter(Context context, List<RechargeRecordBean> data, @LayoutRes int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, int position, RechargeRecordBean item) {
        String status = item.getStatus();
        TextView tvStatus = holder.getView(R.id.item_status);
        if (status.equals("0")) {
            tvStatus.setText("待处理");
            tvStatus.setTextColor(ContextCompat.getColor(tvStatus.getContext(), R.color.red));
        } else {
            tvStatus.setText("已成功");
            tvStatus.setTextColor(ContextCompat.getColor(tvStatus.getContext(), R.color.green));
        }
        holder.setText(R.id.item_money, item.getMoney())
                .setText(R.id.item_time, item.getTime());
    }
}
