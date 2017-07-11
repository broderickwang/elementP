package com.xuanqi.he.o2omvp.modlues.personal;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.activity.login.LoginActivity;
import com.xuanqi.he.o2omvp.constant.Key;
import com.xuanqi.he.o2omvp.modlues.personal.activity.MyOrderActivity;
import com.xuanqi.he.o2omvp.modlues.personal.activity.TaskOrderActivity;
import com.xuanqi.he.o2omvp.modlues.personal.activity.accountsettings.AccountSettingsActivity;
import com.xuanqi.he.o2omvp.modlues.personal.activity.bindphonenumber.BindPhoneNumberActivity;
import com.xuanqi.he.o2omvp.modlues.personal.activity.myfriend.MyFriendActivity;
import com.xuanqi.he.o2omvp.modlues.personal.activity.mywallet.MyWalletActivity;
import com.xuanqi.he.o2omvp.modlues.personal.activity.settingcenter.SettingCenterActivity;
import com.xuanqi.he.o2omvp.mvp.MVPBaseFragment;
import com.xuanqi.he.o2omvp.widget.CircleImageView;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class PersonalFragment extends MVPBaseFragment<PersonalContract.View, PersonalPresenter> implements PersonalContract.View {

    @BindView(R.id.m_set)
    ImageView mSet;
    @BindView(R.id.m_message)
    ImageView mMessage;
    @BindView(R.id.m_icon)
    CircleImageView mIcon;
    @BindView(R.id.m_nickname)
    TextView mNickname;
    @BindView(R.id.m_phone_number)
    TextView mPhoneNumber;
    @BindView(R.id.m_signature)
    TextView mSignature;
    @BindView(R.id.m_personal_information)
    LinearLayout mPersonalInformation;
    @BindView(R.id.m_balance)
    TextView mBalance;
    @BindView(R.id.m_discount)
    TextView mDiscount;
    @BindView(R.id.m_integral)
    TextView mIntegral;
    @BindView(R.id.m_look_all)
    TextView mLookAll;
    @BindView(R.id.m_obligation)
    TextView mObligation;
    @BindView(R.id.m_goods_receipt)
    TextView mGoodsReceipt;
    @BindView(R.id.m_be_evaluated)
    TextView mBeEvaluated;
    @BindView(R.id.m_after_sale)
    TextView mAfterSale;
    @BindView(R.id.m_my_wallet)
    TextView mMyWallet;
    @BindView(R.id.m_shipping_address)
    TextView mShippingAddress;
    @BindView(R.id.m_my_collection)
    TextView mMyCollection;
    @BindView(R.id.m_my_message)
    TextView mMyMessage;
    @BindView(R.id.m_my_friend)
    TextView mMyFriend;
    @BindView(R.id.m_task_order)
    TextView mTaskOrder;
    @BindView(R.id.m_member_centre)
    TextView mMemberCentre;
    @BindView(R.id.m_points_mall)
    TextView mPointsMall;
    @BindView(R.id.m_root)
    LinearLayout mRoot;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.m_set, R.id.m_message, R.id.m_icon, R.id.m_personal_information,
            R.id.ll_balance_box, R.id.ll_discount_box, R.id.ll_integral_box, R.id.m_look_all,
            R.id.m_obligation, R.id.m_goods_receipt, R.id.m_be_evaluated, R.id.m_after_sale,
            R.id.m_my_wallet, R.id.m_shipping_address, R.id.m_my_collection,
            R.id.m_my_message, R.id.m_my_friend, R.id.m_task_order, R.id.m_member_centre,
            R.id.m_points_mall})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.m_set:
                //设置
                intent = new Intent(getContext(), SettingCenterActivity.class);
                startActivity(intent);
                break;
            case R.id.m_message:
                //信息
                intent = new Intent(getContext(), BindPhoneNumberActivity.class);
                startActivity(intent);
                break;
            case R.id.m_icon:
                //头像
                intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.m_personal_information:
                //个人资料
                intent = new Intent(getContext(), AccountSettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_balance_box:
                //我的余额
                break;
            case R.id.ll_discount_box:
                //我的优惠
                break;
            case R.id.ll_integral_box:
                //我的积分
                break;
            case R.id.m_look_all:
                //查看全部
                intent = new Intent(getContext(), MyOrderActivity.class);
                intent.putExtra(Key.MY_ORDER_ID_KEY, 0);
                startActivity(intent);
                break;
            case R.id.m_obligation:
                //待付款
                intent = new Intent(getContext(), MyOrderActivity.class);
                intent.putExtra(Key.MY_ORDER_ID_KEY, 1);
                startActivity(intent);
                break;
            case R.id.m_goods_receipt:
                //待收货
                intent = new Intent(getContext(), MyOrderActivity.class);
                intent.putExtra(Key.MY_ORDER_ID_KEY, 2);
                startActivity(intent);
                break;
            case R.id.m_be_evaluated:
                //待评价
                intent = new Intent(getContext(), MyOrderActivity.class);
                intent.putExtra(Key.MY_ORDER_ID_KEY, 3);
                startActivity(intent);
                break;
            case R.id.m_after_sale:
                //退款/售后
                intent = new Intent(getContext(), MyOrderActivity.class);
                intent.putExtra(Key.MY_ORDER_ID_KEY, 4);
                startActivity(intent);
                break;
            case R.id.m_my_wallet:
                //我的钱包
                intent = new Intent(getContext(), MyWalletActivity.class);
                startActivity(intent);
                break;
            case R.id.m_shipping_address:
                //收货地址
                break;
            case R.id.m_my_collection:
                //我的收藏
                break;
            case R.id.m_my_message:
                //我的消息
                break;
            case R.id.m_my_friend:
                //我的好友
                intent = new Intent(getContext(), MyFriendActivity.class);
                startActivity(intent);
                break;
            case R.id.m_task_order:
                //任务订单
                intent = new Intent(getContext(), TaskOrderActivity.class);
                startActivity(intent);
                break;
            case R.id.m_member_centre:
                //会员中心
                break;
            case R.id.m_points_mall:
                //积分商城
                break;
        }
    }
}
