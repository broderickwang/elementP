package com.xuanqi.he.o2omvp.modlues.life;

import com.xuanqi.he.o2omvp.modlues.life.bean.LifeBean;
import com.xuanqi.he.o2omvp.mvp.BasePresenter;
import com.xuanqi.he.o2omvp.mvp.BaseView;

import java.util.List;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class LifeContract {
    interface View extends BaseView {
        void onRequestSuccess(List<LifeBean> list);

        void onRequestFailed(String message);
    }

    interface Presenter extends BasePresenter<View> {
        void requestData();
    }
}
