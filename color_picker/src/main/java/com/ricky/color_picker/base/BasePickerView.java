package com.ricky.color_picker.base;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author RickyHal
 * @date 2021/2/7
 */
abstract class BasePickerView extends View {
    /**
     * 触摸点X坐标
     */
    protected float mTouchX = -1;
    /**
     * 触摸点Y坐标
     */
    protected float mTouchY = -1;

    /**
     * 自定义画笔，用于绘制控件
     */
    protected Paint mPaint;
    /**
     * 是否触点释放
     */
    protected boolean mIsRelease = false;

    public BasePickerView(Context context) {
        super(context);
        init();
    }

    public BasePickerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BasePickerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BasePickerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    /**
     * 初始化
     */
    protected void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
    }

    abstract protected void computeCurrent();

    /**
     * 转dp值
     *
     * @param value
     * @return
     */
    protected float getDpValue(float value) {
        DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
        return value * dm.density;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        getParent().requestDisallowInterceptTouchEvent(true);
        mTouchX = event.getX();
        mTouchY = event.getY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                mIsRelease = true;
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_DOWN:
                mIsRelease = false;
                break;
        }
        computeCurrent();
        if (mIsRelease)
            postInvalidateDelayed(700);
        else
            postInvalidate();
        return true;
    }
}
