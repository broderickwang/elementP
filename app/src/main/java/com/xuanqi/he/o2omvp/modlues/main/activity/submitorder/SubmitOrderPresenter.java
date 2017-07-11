package com.xuanqi.he.o2omvp.modlues.main.activity.submitorder;

import com.xuanqi.he.o2omvp.modlues.main.bean.OfferBean;
import com.xuanqi.he.o2omvp.mvp.BasePresenterImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SubmitOrderPresenter extends BasePresenterImpl<SubmitOrderContract.View> implements SubmitOrderContract.Presenter {

    @Override
    public void requestData() {
        List<OfferBean> mList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            OfferBean offerBean = new OfferBean();
            offerBean.setStatus(i);
            int price = new Random().nextInt(5);
            offerBean.setContent("在线支付立减" + price);
            offerBean.setPrice(price * 1.0f);
            mList.add(offerBean);
        }
        mView.onRequestSuccess(mList);
    }
}
