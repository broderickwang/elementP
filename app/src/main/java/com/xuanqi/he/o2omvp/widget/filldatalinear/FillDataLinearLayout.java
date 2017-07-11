package com.xuanqi.he.o2omvp.widget.filldatalinear;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * @author Created by He on 2017/7/3.
 * @description
 */

public class FillDataLinearLayout extends LinearLayout implements FillDataAdapter.OnDataChangedListener {

    private FillDataAdapter mAdapter;

    public FillDataLinearLayout(Context context) {
        super(context);
    }

    public FillDataLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FillDataLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAdapter(FillDataAdapter adapter) {
        mAdapter = adapter;
        mAdapter.setOnDataChangedListener(this);
        changeAdapter();
    }

    private void changeAdapter() {
        removeAllViews();
        for (int i = 0; i < mAdapter.getCount(); i++) {
            View view = mAdapter.getView(this, i, mAdapter.getItem(i));
            addView(view);
        }
    }

    @Override
    public void onChanged() {
        changeAdapter();
    }
}
