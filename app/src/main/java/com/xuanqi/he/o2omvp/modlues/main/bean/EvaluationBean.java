package com.xuanqi.he.o2omvp.modlues.main.bean;

import java.util.List;

/**
 * @author Created by He on 2017/6/17.
 * @description
 */

public class EvaluationBean {

    private int icon;
    private String name;
    private String time;
    private float score;
    private String content;
    private List<Integer> imageList;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Integer> getImageList() {
        return imageList;
    }

    public void setImageList(List<Integer> imageList) {
        this.imageList = imageList;
    }
}
