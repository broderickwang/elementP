package com.xuanqi.he.o2omvp.modlues.main.fragment.evaluation;

import com.xuanqi.he.o2omvp.modlues.main.bean.EvaluationBean;
import com.xuanqi.he.o2omvp.mvp.BasePresenter;
import com.xuanqi.he.o2omvp.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class EvaluationContract {
    interface View extends BaseView {
        void onRequestSuccess(List<EvaluationBean> list);

        void onRequestFailed(String message);
    }

    interface Presenter extends BasePresenter<View> {
        void requestData(int count);
    }
}
