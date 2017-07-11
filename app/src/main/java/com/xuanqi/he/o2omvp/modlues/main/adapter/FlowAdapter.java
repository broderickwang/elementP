package com.xuanqi.he.o2omvp.modlues.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.widget.flowlayout.FlowLayout;
import com.xuanqi.he.o2omvp.widget.flowlayout.TagAdapter;

import java.util.List;


/**
 * @author Created by He on 2017/6/23.
 * @description
 */

public class FlowAdapter extends TagAdapter<String> {


    public FlowAdapter(List<String> datas) {
        super(datas);
    }

    public FlowAdapter(String[] datas) {
        super(datas);
    }

    @Override
    public View getView(FlowLayout parent, int position, String s) {
        TextView textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.search_tv, parent, false);
        textView.setText(s);
        return textView;
    }
}
