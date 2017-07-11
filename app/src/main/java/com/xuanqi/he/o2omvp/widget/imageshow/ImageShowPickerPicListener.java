package com.xuanqi.he.o2omvp.widget.imageshow;

/**
 * Author 姚智胜
 * Version V1.0版本
 * Description: view内部使用的点击监听
 * Date: 2017/4/15
 */

public interface ImageShowPickerPicListener {

    void onDelClickListener(int position);

    void onPicClickListener(int position);

    boolean onPicLongClick(int position);

}
