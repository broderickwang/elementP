package com.xuanqi.he.o2omvp.modlues.personal.activity.myfriend;

import com.xuanqi.he.o2omvp.modlues.personal.bean.FriendBean;
import com.xuanqi.he.o2omvp.mvp.BasePresenter;
import com.xuanqi.he.o2omvp.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MyFriendContract {
    interface View extends BaseView {
        void onRequestSuccess(List<FriendBean> list);

        void onRequestFailed(String message);

    }

    interface Presenter extends BasePresenter<View> {
        void requestData();
    }
}
