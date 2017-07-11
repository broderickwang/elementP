package com.xuanqi.he.o2omvp.widget.filldatalinear;

import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

/**
 * @author Created by He on 2017/7/3.
 * @description
 */

public abstract class FillDataAdapter<T> {

    private List<T> mDatas;
    private OnDataChangedListener mOnDataChangedListener;

    public FillDataAdapter(List<T> datas) {
        mDatas = datas;
    }

    public interface OnDataChangedListener {
        void onChanged();
    }

    public void setOnDataChangedListener(OnDataChangedListener listener) {
        mOnDataChangedListener = listener;
    }


    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public void notifyDataChanged() {
        if (mOnDataChangedListener != null)
            mOnDataChangedListener.onChanged();
    }

    public T getItem(int position) {
        return mDatas.get(position);
    }

    public abstract View getView(LinearLayout parent, int position, T t);
}
