package com.xuanqi.he.o2omvp.modlues.life.bean;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.xuanqi.he.o2omvp.utils.GlideUtils;
import com.xuanqi.he.o2omvp.widget.imageshow.ImageLoader;

/**
 * @author Created by He on 2017/5/17.
 * @description
 */

public class ImageShowLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, String path, ImageView imageView) {
        GlideUtils.loadImage(context, path, imageView);
    }

    @Override
    public void displayImage(Context context, @DrawableRes Integer resId, ImageView imageView) {
        imageView.setImageResource(resId);
    }
}
