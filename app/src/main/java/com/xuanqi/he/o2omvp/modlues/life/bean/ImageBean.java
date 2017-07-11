package com.xuanqi.he.o2omvp.modlues.life.bean;

import com.xuanqi.he.o2omvp.widget.imageshow.ImageShowPickerBean;

/**
 * @author Created by He on 2017/5/17.
 * @description
 */

public class ImageBean extends ImageShowPickerBean {

    private String url;
    private int id;

    public ImageBean(String url){
        this.url = url;
    }

    public ImageBean(int id){
        this.id = id;
    }

    @Override
    public String setImageShowPickerUrl() {
        return url;
    }

    @Override
    public int setImageShowPickerDelRes() {
        return id;
    }
}
