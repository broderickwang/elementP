package com.xuanqi.he.o2omvp.widget.imageshow;

import android.content.Context;
import android.widget.ImageView;

/**
 * Version V1.0版本
 * Description: 加载图片方式
 * Date: 2017/4/6
 */
public abstract class ImageLoader implements ImageLoaderInterface<ImageView> {

    @Override
    public ImageView createImageView(Context context) {
        return new ImageView(context);
    }

}
