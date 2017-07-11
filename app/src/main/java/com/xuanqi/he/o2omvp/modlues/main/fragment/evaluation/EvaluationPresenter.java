package com.xuanqi.he.o2omvp.modlues.main.fragment.evaluation;

import android.os.Handler;

import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.modlues.main.bean.EvaluationBean;
import com.xuanqi.he.o2omvp.mvp.BasePresenterImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class EvaluationPresenter extends BasePresenterImpl<EvaluationContract.View> implements EvaluationContract.Presenter{

    @Override
    public void requestData(final int count) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<EvaluationBean> data = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                    EvaluationBean bean = new EvaluationBean();
                    bean.setIcon(R.mipmap.img_add);
                    bean.setName("诸葛小前锋");
                    bean.setContent("是大家发送对话福克斯是绝对返回收款计划考核上岛咖啡汇款时房价很快水电费灰色空间返回闪电发货深刻的发货时刻发挥深刻的积分换科技时代");
                    bean.setScore((new Random().nextInt(6)) * 1.0f);
                    bean.setTime("2017-01-05");
                    List<Integer> list = new ArrayList<>();
                    for (int j = 0; j < new Random().nextInt(5); j++) {
                        list.add(R.mipmap.test_home_img01);
                    }
                    bean.setImageList(list);
                    data.add(bean);
                }
                mView.onRequestSuccess(data);
            }
        },2000);
    }
}
