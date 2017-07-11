package com.xuanqi.he.o2omvp.widget.recycler.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.View;

import com.se7en.utils.DeviceUtils;
import com.xuanqi.he.o2omvp.modlues.main.bean.CommodityBean;

import java.util.List;

/**
 * Created by He on 2017/05/27.
 */

public class StickyDecoration extends RecyclerView.ItemDecoration {

    private TextPaint textPaint;
    private Paint paint;
    private int topHeight;
    private List<CommodityBean> mList;

    public StickyDecoration(Context context, List<CommodityBean> mList, int color) {
        this.mList = mList;

        paint = new Paint();
        paint.setColor(ContextCompat.getColor(context, color));
//        paint.setColor(Color.BLACK);
        textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(DeviceUtils.sp2px(18));
        textPaint.setColor(Color.WHITE);
        textPaint.setTextAlign(Paint.Align.LEFT);
        topHeight = DeviceUtils.dip2px(50);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        if (isFirstInGroup(position)) {
            outRect.top = topHeight;
        } else {
            outRect.top = 0;
        }

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            String textLine = mList.get(position).getCategory();
            if (isFirstInGroup(position)) {
                float top = view.getTop() - topHeight;
                float bottom = view.getTop();

                c.drawRect(left, top, right, bottom, paint);//绘制红色矩形
                Rect mBounds = new Rect();
                textPaint.getTextBounds(textLine, 0, textLine.length(), mBounds);
                Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
                float dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
                float baseLine = (bottom - topHeight / 2) + dy;
//                float baseLine = bottom - ((topHeight - mBounds.height()) / 2);
                c.drawText(textLine, (right - mBounds.width()) / 2, baseLine, textPaint);//绘制文本
            }
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int position = ((LinearLayoutManager) (parent.getLayoutManager())).findFirstVisibleItemPosition();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        c.drawRect(left, 0, right, topHeight, paint);//绘制红色矩形
        String text = mList.get(position).getCategory();
        Rect mBounds = new Rect();
        textPaint.getTextBounds(text, 0, text.length(), mBounds);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        float baseLine = topHeight / 2 + dy;
//        float baseLine = topHeight - ((topHeight - mBounds.height()) / 2);
        //中心线
//        c.drawLine(left, topHeight / 2, right, topHeight / 2, textPaint);
        //基线
//        c.drawLine(left, baseLine, right, baseLine, textPaint);  (right - mBounds.width()) / 2
        c.drawText(text, (right - mBounds.width()) / 2, baseLine, textPaint);//绘制文本
    }

    private boolean isFirstInGroup(int position) {
        boolean isFirst;
        if (position == 0) {
            isFirst = true;
        } else {
            if (mList.get(position).getCategory().
                    equals(mList.get(position - 1).getCategory())) {
                isFirst = false;
            } else {
                isFirst = true;
            }
        }
        return isFirst;
    }
}
