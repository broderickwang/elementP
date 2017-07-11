package com.xuanqi.he.o2omvp.modlues.main.adapter;

import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.modlues.main.bean.OfferBean;
import com.xuanqi.he.o2omvp.widget.filldatalinear.FillDataAdapter;

import java.util.List;

/**
 * @author Created by He on 2017/7/3.
 * @description
 */

public class SubmitOrderOfferAdapter extends FillDataAdapter<OfferBean> {

    private List<OfferBean> mList;

    public SubmitOrderOfferAdapter(List<OfferBean> datas) {
        super(datas);
        this.mList = datas;
    }

    @Override
    public View getView(LinearLayout parent, int position, OfferBean bean) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.submit_order_offer_list_item, parent, false);
        TextView tvToken = (TextView) view.findViewById(R.id.item_token);
        TextView tvDoing = (TextView) view.findViewById(R.id.item_doing);
        TextView tvPrice = (TextView) view.findViewById(R.id.item_price);
        int status = bean.getStatus();
        GradientDrawable drawable = (GradientDrawable) tvToken.getBackground();
        if (status == 0) {
            tvToken.setText("惠");
            drawable.setColor(ContextCompat.getColor(tvToken.getContext(), R.color.green));
        } else {
            tvToken.setText("减");
            drawable.setColor(ContextCompat.getColor(tvToken.getContext(), R.color.red));
        }
        tvDoing.setText(bean.getContent());
        tvPrice.setText("-¥ " + bean.getPrice());
//        LogUtils.e("getView");
        return view;
    }
}
