package com.xuanqi.he.o2omvp.modlues.main.activity.submitorder;

import com.xuanqi.he.o2omvp.modlues.main.bean.OfferBean;
import com.xuanqi.he.o2omvp.mvp.BasePresenter;
import com.xuanqi.he.o2omvp.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SubmitOrderContract {
    interface View extends BaseView {
        void onRequestSuccess(List<OfferBean> list);

        void onRequestFailed(String message);
    }

    interface Presenter extends BasePresenter<View> {
        void requestData();
    }
}
