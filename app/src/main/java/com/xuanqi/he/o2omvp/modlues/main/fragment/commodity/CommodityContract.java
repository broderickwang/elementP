package com.xuanqi.he.o2omvp.modlues.main.fragment.commodity;

import com.xuanqi.he.o2omvp.modlues.main.bean.CommodityBean;
import com.xuanqi.he.o2omvp.mvp.BasePresenter;
import com.xuanqi.he.o2omvp.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class CommodityContract {
    interface View extends BaseView {
        void onRequestSuccess(List<CommodityBean> list);

        void onRequestFailed(String message);
    }

    interface  Presenter extends BasePresenter<View> {
        void requestData();
    }
}
