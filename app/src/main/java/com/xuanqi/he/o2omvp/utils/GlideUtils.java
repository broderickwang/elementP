package com.xuanqi.he.o2omvp.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xuanqi.he.o2omvp.R;


/**
 * Created by ZengLingWen on 2017/6/11.
 */

public class GlideUtils {
    public static void loadImage(Context context,Object url, ImageView imageView){
        Glide.with(context).load(url)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher).into(imageView);
    }
}
