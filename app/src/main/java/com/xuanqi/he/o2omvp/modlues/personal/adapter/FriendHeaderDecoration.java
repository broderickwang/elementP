package com.xuanqi.he.o2omvp.modlues.personal.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import com.se7en.utils.DeviceUtils;
import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.modlues.personal.bean.FriendBean;
import com.xuanqi.he.o2omvp.widget.recycler.decoration.SectionBean;

import java.util.List;

/**
 * Created by He on 2017/6/9.
 */
public class FriendHeaderDecoration extends RecyclerView.ItemDecoration {

    private int mDividerHeight;
    private Paint mPaint;
    private Paint mTextPaint;
    private List<FriendBean> mDatas;
    private Drawable mDivider;

    public FriendHeaderDecoration(int height, Context context) {
        this(height, Color.GRAY, context);
    }


    public FriendHeaderDecoration(int height, @ColorInt int color, Context context) {
        mDivider = ContextCompat.getDrawable(context, R.drawable.line_space);

        this.mDividerHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, height, context.getResources().getDisplayMetrics());
        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        mPaint.setColor(color);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setDither(true);
        mTextPaint.setFakeBoldText(true);
        int textColor = ContextCompat.getColor(context, R.color.text_color);
        mTextPaint.setColor(textColor);
        mTextPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14, context.getResources().getDisplayMetrics()));
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        SectionBean tag = (SectionBean) view.getTag();
        // 如果是头部,留出顶部空间用作画Header
        /*if (!tag.isHeader() && tag.isGroupStart() && !tag.isGroupEnd()) {
            outRect.set(0, mDividerHeight, 0, mDivider.getIntrinsicHeight());
        } else if (!tag.isHeader() && tag.isGroupStart() && tag.isGroupEnd()) {
            outRect.set(0, mDividerHeight, 0, 0);
        } else if (!tag.isHeader() && !tag.isGroupEnd()) {
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        }*/

        if (!tag.isHeader() && tag.isGroupStart() && tag.isGroupEnd()) {
            outRect.set(0, mDividerHeight, 0, 0);
        } else if (!tag.isHeader() && tag.isGroupStart() && !tag.isGroupEnd()) {
            outRect.set(0, mDividerHeight, 0, mDivider.getIntrinsicHeight());
        } else if (!tag.isHeader() && !tag.isGroupStart() && !tag.isGroupEnd()) {
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        }
        /*if (tag.isGroupEnd()) {
            outRect.set(0, 0, 0, 0);
        } else {
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        }*/
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            // 如果是头,画之
            SectionBean tag = (SectionBean) view.getTag();
            if (position != RecyclerView.NO_POSITION
                    && tag.isGroupStart() && !tag.isHeader()) {
                drawHeader(c, parent, view, position);
            }

            if (position != RecyclerView.NO_POSITION
                    && !tag.isGroupEnd() && !tag.isHeader()) {
                drawVertical(c, parent, view);
            }
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent, View view) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view
                .getLayoutParams();
        final int top = view.getBottom() + params.bottomMargin;
        final int bottom = top + mDivider.getIntrinsicHeight();
        mDivider.setBounds(left, top, right, bottom);
        mDivider.draw(c);
    }

    /**
     * 画头部
     *
     * @param c
     * @param parent
     * @param view
     * @param position
     */
    private void drawHeader(Canvas c, RecyclerView parent, View view, int position) {
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        int bottom = view.getTop() - params.topMargin - Math.round(ViewCompat.getTranslationY(view));
        int top = (bottom - mDividerHeight);
        // 计算文字居中时候的基线
        Rect targetRect = new Rect(left, top, right, bottom);
        Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
        int baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
        c.drawRect(left, top, right, bottom, mPaint);
        c.drawText(mDatas.get(position).getCategory(), left + DeviceUtils.dip2px(15), baseline, mTextPaint);

        mDivider.setBounds(left + DeviceUtils.dip2px(15), view.getBottom() + params.bottomMargin, right, view.getBottom() + params.bottomMargin + mDivider.getIntrinsicHeight());
        mDivider.draw(c);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        View view = parent.getChildAt(0);
        View view2 = parent.getChildAt(1);
        if (view != null && view2 != null) {
            SectionBean section1 = (SectionBean) view.getTag();
            SectionBean section2 = (SectionBean) view2.getTag();

            int position = parent.getChildAdapterPosition(view);
            final int left = parent.getPaddingLeft();
            final int right = parent.getWidth() - parent.getPaddingRight();
            int bottom = mDividerHeight;
            int top = 0;
            // 判断是否达到临界点
            // (第一个可见item是每组的最后一个,第二个可见item是下一组的第一个,并且第一个可见item的底部小于header的高度)
            // 这里直接判断item的底部位置小于header的高度有点欠妥,应该还要考虑paddingtop以及margintop,这里展示不考虑了
            if (!section1.isHeader() && !section2.isHeader() && section1.isGroupEnd() && section2.isGroupStart() && view.getBottom() <= mDividerHeight) {
                bottom = view.getBottom();
                top = (bottom - mDividerHeight);
            }
            // 计算文字居中时候的基线
            Rect targetRect = new Rect(left, top, right, bottom);
            Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
            int baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
            // 背景
            c.drawRect(left, top, right, bottom, mPaint);
            // 文字
            c.drawText(mDatas.get(position).getCategory(), left + DeviceUtils.dip2px(15), baseline, mTextPaint);
        }
    }

    /**
     * 头部的名称
     *
     * @param mDatas
     */
    public void setDatas(List<FriendBean> mDatas) {
        this.mDatas = mDatas;
    }

}
