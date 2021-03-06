package com.xuanqi.he.o2omvp.widget.recycler;

/**
 * Created by He on 2016/12/28.
 * Description:  多布局支持接口
 */
public interface MultiTypeSupport<T> {
    // 根据当前位置或者条目数据返回布局
    int getLayoutId(T item, int position);
}
