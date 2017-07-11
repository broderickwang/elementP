package com.xuanqi.he.o2omvp.modlues.main.fragment.commodity;

import android.os.Handler;

import com.xuanqi.he.o2omvp.modlues.main.bean.CommodityBean;
import com.xuanqi.he.o2omvp.mvp.BasePresenterImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class CommodityPresenter extends BasePresenterImpl<CommodityContract.View> implements CommodityContract.Presenter{

    private String[] categarys = {"热销单品", "酒水饮料", "特价套餐", "精品套餐",
            "单点菜品", "美味汉堡", "面食小吃", "活动菜品", "滋补汤品", "特色小吃",
            "鲜榨果汁", "售罄菜品"};

    @Override
    public void requestData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<CommodityBean> mData = new ArrayList<>();
                for (int i = 0; i < categarys.length; i++) {
                    int count = new Random().nextInt(5) + 1;
                    for (int j = 1; j <= count; j++) {
                        CommodityBean commodityBean = new CommodityBean();
                        commodityBean.setCategory(categarys[i]);
                        commodityBean.setName(categarys[i] + j);
                        commodityBean.setCount("月售" + new Random().nextInt(100) + "份");
                        commodityBean.setPrice(((new Random().nextInt(10)) * 1.0f) + 1.0f);
                        commodityBean.setNum(0);
                        mData.add(commodityBean);
                    }
                }
                mView.onRequestSuccess(mData);
            }
        },2000);
    }
}
