package com.xuanqi.he.o2omvp.modlues.personal.bean;

import java.util.List;

/**
 * @author Created by He on 2017/7/10.
 * @description
 */

public class OrderEvaluationBean {

    private String name;
    private String title;
    private List<String> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
