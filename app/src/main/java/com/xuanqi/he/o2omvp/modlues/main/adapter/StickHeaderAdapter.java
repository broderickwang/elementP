package com.xuanqi.he.o2omvp.modlues.main.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.modlues.main.bean.CommodityBean;
import com.xuanqi.he.o2omvp.modlues.main.bean.ShopCart;
import com.xuanqi.he.o2omvp.utils.GlideUtils;
import com.xuanqi.he.o2omvp.utils.LogUtils;
import com.xuanqi.he.o2omvp.widget.ShoppingView;
import com.xuanqi.he.o2omvp.widget.recycler.CommonRecyclerAdapter;
import com.xuanqi.he.o2omvp.widget.recycler.RecyclerViewHolder;
import com.xuanqi.he.o2omvp.widget.recycler.decoration.SectionBean;

import java.util.List;


/**
 * @author Created by He on 2017/6/9.
 * @description
 */

public class StickHeaderAdapter extends CommonRecyclerAdapter<CommodityBean> {

    private List<CommodityBean> mDatas;
    private Context mContext;
    private ShopCart shopCart;
    private int mLayoutId;

    public StickHeaderAdapter(Context context, List<CommodityBean> data, ShopCart shopCart, @LayoutRes int layoutId) {
        super(context, data, layoutId);
        this.mDatas = data;
        this.mContext = context;
        this.shopCart = shopCart;
        this.mLayoutId = layoutId;
    }


    @Override
    public void convert(final RecyclerViewHolder holder, int position, final CommodityBean item) {
        holder.setText(R.id.item_name, item.getName())
                .setText(R.id.item_count, item.getCount());
        TextView tvPrice = holder.getView(R.id.item_price);
        SpannableString spannableString = new SpannableString("¥ " + item.getPrice());
        spannableString.setSpan(new RelativeSizeSpan(0.7f), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvPrice.setText(spannableString);
        final ShoppingView shoppingView = holder.getView(R.id.shopping_view);
        shoppingView.setState();
//        LogUtils.e("适配器更新->" + holder.getAdapterPosition());
        if (item.getNum() >= 0) {
            shoppingView.setTextNum(item.getNum());
        }


        final float price = mDatas.get(holder.getLayoutPosition()).getPrice();
        shoppingView.setOnShoppingClickListener(new ShoppingView.ShoppingClickListener() {
            @Override
            public void onAddClick(int num) {
                LogUtils.e("onAddClick", "" + num + " item=" + item.getNum());
                if (shopCart.addShoppingSingle(item)) {
                    if (listener != null) {
                        listener.onAddClick(shoppingView, num, price);
                    }
                }
            }

            @Override
            public void onMinusClick(int num) {
                LogUtils.e("onMinusClick", "" + num);
                if (shopCart.subShoppingSingle(item)) {
                    if (listener != null) {
                        listener.onDeleteClick(shoppingView, num, price);
                    }
                }
            }
        });
        ImageView imageView = holder.getView(R.id.item_icon);
        GlideUtils.loadImage(mContext, R.mipmap.test_home_img01, imageView);
        generateTag(holder, holder.getAdapterPosition());
    }

    private void generateTag(RecyclerViewHolder holder, int position) {
        SectionBean tag;
        // 没有tag的话 new 一个, 有的话 复用
        if (holder.itemView.getTag() == null) {
            tag = new SectionBean();
        } else {
            tag = (SectionBean) holder.itemView.getTag();
        }
        // 判断当前position的开始结束状态
        if (position == 0) {
            tag.setGroupStart(true);
            tag.setGroupEnd(!mDatas.get(position).getCategory().equals(mDatas.get(position + 1).getCategory()));
        } else if (position == mDatas.size() - 1) {
            tag.setGroupStart(!mDatas.get(position).getCategory().equals(mDatas.get(position - 1).getCategory()));
            tag.setGroupEnd(true);
        } else {
            tag.setGroupStart(!mDatas.get(position).getCategory().equals(mDatas.get(position - 1).getCategory()));
            tag.setGroupEnd(!mDatas.get(position).getCategory().equals(mDatas.get(position + 1).getCategory()));
        }
        holder.itemView.setTag(tag);
    }

    private OnShoppingClickListener listener;


    public interface OnShoppingClickListener {
        void onAddClick(View view, int position, float price);

        void onDeleteClick(View view, int position, float price);
    }

    public void setOnShoppingClickListener(OnShoppingClickListener listener) {
        this.listener = listener;
    }
}
