package com.xuanqi.he.o2omvp.modlues.main.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.widget.RatingBar;

import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.modlues.main.bean.MainBean;
import com.xuanqi.he.o2omvp.widget.recycler.CommonRecyclerAdapter;
import com.xuanqi.he.o2omvp.widget.recycler.RecyclerViewHolder;

import java.util.List;

/**
 * @author Created by He on 2017/7/1.
 * @description
 */

public class MainAdapter extends CommonRecyclerAdapter<MainBean> {

    public MainAdapter(Context context, List<MainBean> data, @LayoutRes int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, int position, MainBean item) {
        holder.setImageResource(R.id.item_icon, item.getIcon())
                .setText(R.id.item_name, item.getName())
                .setText(R.id.item_score, item.getScore() + "")
                .setText(R.id.item_count, "月售" + item.getCount() + "单");
        RatingBar ratingBar = holder.getView(R.id.item_rating_bar);
        ratingBar.setRating(item.getScore());
    }
}
