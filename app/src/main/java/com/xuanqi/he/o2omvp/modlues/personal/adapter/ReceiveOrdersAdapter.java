package com.xuanqi.he.o2omvp.modlues.personal.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.widget.TextView;

import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.modlues.personal.bean.ReceiveOrdersBean;
import com.xuanqi.he.o2omvp.widget.recycler.CommonRecyclerAdapter;
import com.xuanqi.he.o2omvp.widget.recycler.RecyclerViewHolder;

import java.util.List;

/**
 * @author Created by He on 2017/7/6.
 * @description
 */

public class ReceiveOrdersAdapter extends CommonRecyclerAdapter<ReceiveOrdersBean> {

    public ReceiveOrdersAdapter(Context context, List<ReceiveOrdersBean> data, @LayoutRes int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, int position, ReceiveOrdersBean item) {
        holder.setText(R.id.item_name, item.getName())
                .setText(R.id.item_title, item.getTitle());
        TextView tvMoney = holder.getView(R.id.item_money);
        String text = "任务赏金  ¥ " + item.getMoney();
        SpannableString spannableString = new SpannableString(text);
        RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(1.2f);
        spannableString.setSpan(relativeSizeSpan, 5, text.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(ContextCompat.getColor(tvMoney.getContext(), R.color.red));
        spannableString.setSpan(foregroundColorSpan, 5, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvMoney.setText(spannableString);
    }
}
