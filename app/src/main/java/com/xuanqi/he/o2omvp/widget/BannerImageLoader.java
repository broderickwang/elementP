package com.xuanqi.he.o2omvp.widget;

import android.content.Context;
import android.widget.ImageView;

import com.xuanqi.he.o2omvp.utils.GlideUtils;
import com.youth.banner.loader.ImageLoader;

/**
 * @author Created by He on 2017/6/20.
 * @description
 */

public class BannerImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        GlideUtils.loadImage(context, path, imageView);
    }
}
