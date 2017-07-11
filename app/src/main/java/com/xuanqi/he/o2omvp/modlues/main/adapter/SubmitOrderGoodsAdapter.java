package com.xuanqi.he.o2omvp.modlues.main.adapter;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.modlues.main.bean.CommodityBean;
import com.xuanqi.he.o2omvp.widget.filldatalinear.FillDataAdapter;

import java.util.List;

/**
 * @author Created by He on 2017/7/3.
 * @description
 */

public class SubmitOrderGoodsAdapter extends FillDataAdapter<CommodityBean> {

    private List<CommodityBean> mList;

    public SubmitOrderGoodsAdapter(List<CommodityBean> datas) {
        super(datas);
        this.mList = datas;
    }

    @Override
    public View getView(LinearLayout parent, int position, CommodityBean commodityBean) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.submit_order_goods_list_item, parent, false);
        TextView tvName = (TextView) view.findViewById(R.id.item_name);
        TextView tvCount = (TextView) view.findViewById(R.id.item_count);
        TextView tvPrice = (TextView) view.findViewById(R.id.item_price);
        tvName.setText(commodityBean.getName());
        String count = "x " + commodityBean.getNum();
        SpannableString spannableString = new SpannableString(count);
        spannableString.setSpan(new RelativeSizeSpan(0.9f), 0, 1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        tvCount.setText(spannableString);
        String price = "¥ " + (commodityBean.getPrice() * commodityBean.getNum());
        tvPrice.setText(price);
        return view;
    }
}
