package com.xuanqi.he.o2omvp.modlues.main.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.modlues.main.bean.LeftTitleBean;
import com.xuanqi.he.o2omvp.widget.recycler.CommonRecyclerAdapter;
import com.xuanqi.he.o2omvp.widget.recycler.RecyclerViewHolder;

import java.util.List;

/**
 * @author Created by He on 2017/7/1.
 * @description
 */

public class CommodityAdapter extends CommonRecyclerAdapter<LeftTitleBean> {

    private Context mContext;

    public CommodityAdapter(Context context, List<LeftTitleBean> data, @LayoutRes int layoutId) {
        super(context, data, layoutId);
        this.mContext = context;
    }

    @Override
    public void convert(RecyclerViewHolder holder, int position, LeftTitleBean item) {
        View box = holder.getView(R.id.item_box);
        View scroll = holder.getView(R.id.item_scroll);
        TextView textView = holder.getView(R.id.item_title);
        textView.setText(item.getTitle());
        if (item.isChecked()) {
            scroll.setVisibility(View.VISIBLE);
            box.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
            textView.setTextSize(14);
            textView.setTextColor(ContextCompat.getColor(mContext, R.color.text_color));
        } else {
            scroll.setVisibility(View.GONE);
            box.setBackgroundColor(ContextCompat.getColor(mContext, R.color.window_background));
            textView.setTextColor(ContextCompat.getColor(mContext, R.color.light_text_color));
            textView.setTextSize(13);
        }
    }
}
