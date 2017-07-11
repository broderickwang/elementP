package com.xuanqi.he.o2omvp.modlues.personal.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;

import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.modlues.main.adapter.FlowAdapter;
import com.xuanqi.he.o2omvp.modlues.personal.bean.OrderEvaluationBean;
import com.xuanqi.he.o2omvp.widget.flowlayout.TagFlowLayout;
import com.xuanqi.he.o2omvp.widget.recycler.CommonRecyclerAdapter;
import com.xuanqi.he.o2omvp.widget.recycler.RecyclerViewHolder;

import java.util.List;

/**
 * @author Created by He on 2017/7/10.
 * @description
 */

public class OrderEvaluationAdapter extends CommonRecyclerAdapter<OrderEvaluationBean> {
    public OrderEvaluationAdapter(Context context, List<OrderEvaluationBean> data, @LayoutRes int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, int position, OrderEvaluationBean item) {
        holder.setText(R.id.tv_store_name,item.getName())
                .setText(R.id.tv_title,item.getTitle());
        TagFlowLayout flowLayout = holder.getView(R.id.tag_flow_layout);
        FlowAdapter adapter = new FlowAdapter(item.getList());
        flowLayout.setAdapter(adapter);
    }
}
