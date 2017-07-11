package com.xuanqi.he.o2omvp.modlues.main.adapter;

import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.widget.filldatalinear.FillDataAdapter;

import java.util.List;

/**
 * @author Created by He on 2017/7/3.
 * @description
 */

public class BusinessOfferAdapter extends FillDataAdapter<String> {

    private List<String> mList;

    public BusinessOfferAdapter(List<String> datas) {
        super(datas);
        this.mList = datas;
    }

    @Override
    public View getView(LinearLayout parent, int position, String content) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.business_offer_item, parent, false);
        TextView tvToken = (TextView) view.findViewById(R.id.item_token);
        TextView tvDoing = (TextView) view.findViewById(R.id.item_doing);
        GradientDrawable drawable = (GradientDrawable) tvToken.getBackground();
        if (position == 0) {
            tvToken.setText("惠");
            drawable.setColor(ContextCompat.getColor(tvToken.getContext(), R.color.green));
        } else {
            tvToken.setText("减");
            drawable.setColor(ContextCompat.getColor(tvToken.getContext(), R.color.red));
        }
        tvDoing.setText(content);
        return view;
    }
}
