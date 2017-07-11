package com.xuanqi.he.o2omvp.modlues.personal.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.modlues.personal.bean.MyOrderBean;
import com.xuanqi.he.o2omvp.widget.filldatalinear.FillDataAdapter;

import java.util.List;

/**
 * @author Created by He on 2017/7/7.
 * @description
 */

public class MyOrderCommodityAdapter extends FillDataAdapter<MyOrderBean.Commodity> {

    public MyOrderCommodityAdapter(List<MyOrderBean.Commodity> datas) {
        super(datas);
    }

    @Override
    public View getView(LinearLayout parent, int position, MyOrderBean.Commodity commodity) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_order_commodity_item, parent, false);
        TextView tvTitle = (TextView) view.findViewById(R.id.item_title);
        TextView tvCount = (TextView) view.findViewById(R.id.item_count);
        tvTitle.setText(commodity.getTitle());
        tvCount.setText("× " + commodity.getCount());
        return view;
    }
}
