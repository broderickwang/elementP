package com.xuanqi.he.o2omvp.modlues.personal.activity.myfriend;

import com.xuanqi.he.o2omvp.modlues.personal.bean.FriendBean;
import com.xuanqi.he.o2omvp.mvp.BasePresenterImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MyFriendPresenter extends BasePresenterImpl<MyFriendContract.View> implements MyFriendContract.Presenter{

    @Override
    public void requestData() {
        List<FriendBean> mList = new ArrayList<>();
        for (int i = 'A'; i <= 'Z'; i++) {
            int j = new Random().nextInt(5) + 1;
            for (int k = 0; k < j; k++) {
                FriendBean friendBean = new FriendBean();
                friendBean.setCategory(((char) i) + "");
                friendBean.setName("诸葛小前锋" + ((char) i));
                friendBean.setSignature("简介：真正男子汉" + ((char) i));
                mList.add(friendBean);
            }
        }

        mView.onRequestSuccess(mList);
    }
}
