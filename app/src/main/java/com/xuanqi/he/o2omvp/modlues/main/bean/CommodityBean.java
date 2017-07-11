package com.xuanqi.he.o2omvp.modlues.main.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Created by He on 2017/6/9.
 * @description
 */

public class CommodityBean implements Parcelable {

    private String category;//这个代表的是类别，就是是热销单品，还是酒水饮料
    private String name;
    private String count;
    private float price;
    private int num;

    protected CommodityBean(Parcel in) {
        category = in.readString();
        name = in.readString();
        count = in.readString();
        price = in.readFloat();
        num = in.readInt();
    }

    public CommodityBean(){}

    public static final Creator<CommodityBean> CREATOR = new Creator<CommodityBean>() {
        @Override
        public CommodityBean createFromParcel(Parcel in) {
            return new CommodityBean(in);
        }

        @Override
        public CommodityBean[] newArray(int size) {
            return new CommodityBean[size];
        }
    };

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public int hashCode() {
        int code = this.name.hashCode() + (int) this.price;
        return code;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==this)return true;

        return obj instanceof CommodityBean &&
                this.name.equals(((CommodityBean)obj).name) &&
                this.category.equals(((CommodityBean)obj).category) &&
                this.count.equals(((CommodityBean)obj).count) &&
                this.price ==  ((CommodityBean)obj).price &&
                this.num == ((CommodityBean)obj).num;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(category);
        dest.writeString(name);
        dest.writeString(count);
        dest.writeFloat(price);
        dest.writeInt(num);
    }
}
