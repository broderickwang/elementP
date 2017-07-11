package com.xuanqi.he.o2omvp.modlues.main.bean;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by cheng on 16-11-12.
 */
public class ShopCart {
    private int shoppingAccount;//商品总数
    private float shoppingTotalPrice;//商品总价钱
    private Map<CommodityBean, Integer> shoppingSingle;//单个物品的总价价钱

    public ShopCart() {
        this.shoppingAccount = 0;
        this.shoppingTotalPrice = 0;
//        this.shoppingSingle = new HashMap<>();
        this.shoppingSingle = new LinkedHashMap<>();
    }

    public int getShoppingAccount() {
        return shoppingAccount;
    }

    public double getShoppingTotalPrice() {
        return shoppingTotalPrice;
    }

    public Map<CommodityBean, Integer> getShoppingSingleMap() {
        return shoppingSingle;
    }

    public boolean addShoppingSingle(CommodityBean dish) {
        int remain = dish.getNum();
        if (remain < 0)
            return false;

        int num = 0;
        if (shoppingSingle.containsKey(dish)) {
            num = shoppingSingle.get(dish);
        }
        num += 1;
        dish.setNum(num);
        shoppingSingle.put(dish, num);

        shoppingTotalPrice += dish.getPrice();
        shoppingAccount++;
        return true;
    }

    public boolean subShoppingSingle(CommodityBean dish) {
        int remain = dish.getNum();
        if (remain < 0)
            return false;

        int num = 0;
        if (shoppingSingle.containsKey(dish)) {
            num = shoppingSingle.get(dish);
        }
        num--;
        dish.setNum(num);
        shoppingSingle.put(dish, num);
        if (num == 0) shoppingSingle.remove(dish);

        shoppingTotalPrice -= dish.getPrice();
        shoppingAccount--;
        return true;
    }

    public int getDishAccount() {
        return shoppingSingle.size();
    }

    public void clear() {
        this.shoppingAccount = 0;
        this.shoppingTotalPrice = 0;
        this.shoppingSingle.clear();
    }
}
