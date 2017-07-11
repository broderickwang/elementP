package com.xuanqi.he.o2omvp.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import com.se7en.utils.DeviceUtils;
import com.xuanqi.he.o2omvp.R;

import java.util.List;

/**
 * Created by He on 16/7/26.
 */
public class SideView extends View {


    public interface OnTextClickListener {
        void showTextView(String textView, boolean dismiss, int position);
    }

    private OnTextClickListener listener;

    public void setOnTextClickListener(OnTextClickListener listener) {
        this.listener = listener;
    }

    private float mWidth, mHeight, mTextHeight;
    private Paint paint;
    private Paint selectPaint;
    //    private Paint rectPaint;
    private Rect mBound;
    private int yDown, yMove, xMove, mTouchSlop, position;
    private boolean isSlide;
    private String selectTxt;
    private List<String> pinyinList;
    private int textSize = DeviceUtils.dip2px(12);

    public SideView(Context context) {
        this(context, null);
    }

    public SideView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        /*pinyinList = new ArrayList<>();
        for (int i = 'A'; i <= 'Z'; i++) {
            pinyinList.add((char) i + "");
        }
        pinyinList.add("#");*/
    }

    public void setData(List<String> list) {
        this.pinyinList = list;
    }


    private void initView() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setTextSize(textSize);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.text_color));
        mBound = new Rect();

        selectPaint = new Paint();
        selectPaint.setAntiAlias(true);
        selectPaint.setDither(true);
        selectPaint.setColor(Color.MAGENTA);
        selectPaint.setTextSize(textSize);

//        rectPaint = new Paint();
//        rectPaint.setAntiAlias(true);
//        rectPaint.setDither(true);
//        rectPaint.setColor(Color.GREEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        LogUtils.e("onDraw");
//        canvas.drawRect(0, 0, mWidth, mHeight, rectPaint);
        for (int i = 0; i < pinyinList.size(); i++) {
            String textView = pinyinList.get(i);
            if (i == position - 1) {
                selectTxt = pinyinList.get(i);
                if (listener != null) {
                    listener.showTextView(selectTxt, false, position - 1);
                }
                float baseLine = getBaseLine(textView, selectPaint, i + 1);
//                selectPaint.getTextBounds(textView, 0, textView.length(), mBound);
                canvas.drawText(textView, (mWidth - mBound.width()) / 2, baseLine, selectPaint);
            } else {
                float baseLine = getBaseLine(textView, paint, i + 1);
//                paint.getTextBounds(textView, 0, textView.length(), mBound);
                canvas.drawText(textView, (mWidth - mBound.width()) / 2, baseLine, paint);
            }
//            mTextHeight += mHeight / pinyinList.size();
        }
    }

    private float getBaseLine(String textView, Paint paint, int index) {
        paint.getTextBounds(textView, 0, textView.length(), mBound);
//        LogUtils.e("mBound.width()=" + mBound.width() + "mBound.height()=" + mBound.height());
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        float baseLine = (mTextHeight * index - mTextHeight / 2) + dy;
//        LogUtils.e("mHeight=" + mHeight + " mTextHeight=" + mTextHeight + " dy=" + dy + " baseLine=" + baseLine);
        return baseLine;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        int action = ev.getAction();
        int y = (int) ev.getY();
        int x = (int) ev.getX();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                yDown = y;
                break;
            case MotionEvent.ACTION_MOVE:
                yMove = y;
                xMove = x;
                int dy = yMove - yDown;
//                LogUtils.e("yMove="+yMove);
                if (yMove >= mHeight || yMove <= 0 || xMove >= mWidth || xMove <= 0) {
//                    LogUtils.e("出去了");
                    isSlide = false;
                    //如果是竖直方向滑动
                } else if (Math.abs(dy) > mTouchSlop) {
                    isSlide = true;
                } else {
                    isSlide = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int y = (int) event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
//                mTextHeight = mHeight / pinyinList.size();
                position = (int) (y / (mHeight / (pinyinList.size() + 1)));
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                if (isSlide) {
//                    mTextHeight = mHeight / pinyinList.size();
                    position = (int) (y / (mHeight / pinyinList.size() + 1) + 1);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
//                mTextHeight = mHeight / pinyinList.size();
                if (listener != null) {
                    listener.showTextView(selectTxt, true, position - 1);
                }
                position = 0;
                invalidate();
                break;
        }
        return true;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = widthSize * 1 / 2;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = heightSize * 1 / 2;
        }
        mWidth = width;
        mHeight = height;
        mTextHeight = mHeight / pinyinList.size();
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        if (visibility != VISIBLE) {
            mTextHeight = mHeight / pinyinList.size();
        }
    }
}
