package com.xuanqi.he.o2omvp.modlues.main.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.modlues.main.bean.CommodityBean;
import com.xuanqi.he.o2omvp.modlues.main.bean.ShopCart;
import com.xuanqi.he.o2omvp.utils.LogUtils;
import com.xuanqi.he.o2omvp.widget.ShoppingView;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Created by He on 2017/6/13.
 * @description
 */

public class ShoppingCartDialogAdapter extends RecyclerView.Adapter<ShoppingCartDialogAdapter.DialogViewHolder> {

    private ShopCart shopCart;
    private List<CommodityBean> mList;

    public ShoppingCartDialogAdapter(ShopCart shopCart) {
        this.shopCart = shopCart;
        this.mList = new ArrayList<>();
        mList.addAll(shopCart.getShoppingSingleMap().keySet());
    }

    @Override
    public DialogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_cart_dialog_item, parent, false);
        return new DialogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DialogViewHolder holder, final int position) {

        LogUtils.e("position=" + position);
        final CommodityBean commodityBean = mList.get(position);
        holder.itemName.setText(commodityBean.getName());

        final float price = commodityBean.getPrice();
        int num = shopCart.getShoppingSingleMap().get(commodityBean);

        holder.mShoppingView.isRotate(false);
        holder.mShoppingView.setState();

//        LogUtils.e("onBindViewHolder num=" + num);
        holder.mShoppingView.setTextNum(num);

//        LogUtils.e("onBindViewHolder getState=" + holder.mShoppingView.getState());

        holder.mShoppingView.setOnShoppingClickListener(new ShoppingView.ShoppingClickListener() {
            @Override
            public void onAddClick(int num) {
                if (shopCart.addShoppingSingle(commodityBean)) {
                    if (listener != null) {
                        listener.onAddClick(num, commodityBean.getName());
                    }
                }
            }

            @Override
            public void onMinusClick(int num) {
//                LogUtils.e("onMinusClick->num=" + num);
                if (shopCart.subShoppingSingle(commodityBean)) {
                    if (mList.size() > 1 && num <= 0) {
                        mList.remove(position);
                        notifyItemRemoved(position);
                        if (position != mList.size()) {
                            notifyItemRangeChanged(position, mList.size() - position);
//                            notifyItemChanged(position);
                        }
                    }
                    /*if (num <= 0) {
//                        mList.clear();
//                        mList.addAll(shopCart.getShoppingSingleMap().keySet());
                        notifyDataSetChanged();
                    }*/

                    if (listener != null) {
                        listener.onDeleteClick(num, commodityBean.getName());
                    }
                }
            }
        });

        SpannableString spannableString = new SpannableString("¥ " + (price * num));
        spannableString.setSpan(new RelativeSizeSpan(0.7f), 0, 1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        holder.itemMoney.setText(spannableString);

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
//        return shopCart.getDishAccount();
    }

    public class DialogViewHolder extends RecyclerView.ViewHolder {

        TextView itemName, itemMoney;
        ShoppingView mShoppingView;

        public DialogViewHolder(View itemView) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.item_name);
            itemMoney = (TextView) itemView.findViewById(R.id.item_money);
            mShoppingView = (ShoppingView) itemView.findViewById(R.id.shopping_view);
        }
    }

    private OnDialogShoppingAdapterClickListener listener;

    public interface OnDialogShoppingAdapterClickListener {
        void onAddClick(int num, String name);

        void onDeleteClick(int num, String name);
    }

    public void setOnDialogShoppingAdapterClickListener(OnDialogShoppingAdapterClickListener listener) {
        this.listener = listener;
    }

}
