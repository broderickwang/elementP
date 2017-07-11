package com.xuanqi.he.o2omvp.modlues.personal.fragment.myorder;

import android.os.Handler;

import com.xuanqi.he.o2omvp.modlues.personal.bean.MyOrderBean;
import com.xuanqi.he.o2omvp.mvp.BasePresenterImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MyOrderPresenter extends BasePresenterImpl<MyOrderContract.View> implements MyOrderContract.Presenter {

    @Override
    public void requestData(final int count) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<MyOrderBean> list = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                    MyOrderBean bean = new MyOrderBean();
                    bean.setTitle("百姓便利超市" + i);
                    bean.setStatus(new Random().nextInt(3));
                    int random = new Random().nextInt(3) + 1;
                    List<MyOrderBean.Commodity> commodityList = new ArrayList<>();
                    for (int j = 0; j < random; j++) {
                        MyOrderBean.Commodity commodity = new MyOrderBean.Commodity();
                        commodity.setTitle("商品" + j);
                        commodity.setCount(new Random().nextInt(30) + 1);
                        commodityList.add(commodity);
                    }
                    bean.setList(commodityList);
                    list.add(bean);
                }
                mView.onRequestSuccess(list);
            }
        }, 2000);
    }
}
