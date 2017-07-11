package com.xuanqi.he.o2omvp.modlues.personal.bean;

import java.util.List;

/**
 * @author Created by He on 2017/7/6.
 * @description
 */

public class MyOrderBean {

    private String title;
    private int status;
    private List<Commodity> list;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Commodity> getList() {
        return list;
    }

    public void setList(List<Commodity> list) {
        this.list = list;
    }

    public static class Commodity {
        private String title;
        private int count;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
