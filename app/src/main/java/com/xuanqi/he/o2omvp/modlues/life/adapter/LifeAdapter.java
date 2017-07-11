package com.xuanqi.he.o2omvp.modlues.life.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.widget.ImageView;

import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.modlues.life.bean.LifeBean;
import com.xuanqi.he.o2omvp.utils.GlideUtils;
import com.xuanqi.he.o2omvp.widget.recycler.CommonRecyclerAdapter;
import com.xuanqi.he.o2omvp.widget.recycler.RecyclerViewHolder;

import java.util.List;

/**
 * @author Created by He on 2017/7/1.
 * @description
 */

public class LifeAdapter extends CommonRecyclerAdapter<LifeBean> {

    private Context mContext;

    public LifeAdapter(Context context, List<LifeBean> data, @LayoutRes int layoutId) {
        super(context, data, layoutId);
        this.mContext = context;
    }

    @Override
    public void convert(RecyclerViewHolder holder, int position, LifeBean item) {
        holder.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_time, item.getTime());
        ImageView image = holder.getView(R.id.iv_picture);
        GlideUtils.loadImage(mContext, item.getPic(), image);
    }
}
