package com.ricky.color_picker.base;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.ricky.color_picker.api.OnColorSelectedListener;

/**
 * @author RickyHal
 * @date 2021/2/7
 */
public abstract class BaseColorPickerView extends BasePickerView {
    /**
     * 放大镜paint
     */
    protected Paint mMirrorLinePaint;
    /**
     * 控件中心点X坐标
     */
    protected int mCenterX;
    /**
     * 控件中心点Y坐标
     */
    protected int mCenterY;
    /**
     * 当前选中的颜色
     */
    protected int mCurrentColor = -1;
    /**
     * 颜色放大镜的半径
     */
    protected int mMirrorRadius = 80;
    /**
     * 是否有颜色放大镜
     */
    protected boolean mHasScaleMirror = true;
    /**
     * 颜色选择监听器
     */
    protected OnColorSelectedListener mListener;

    public BaseColorPickerView(Context context) {
        super(context);
    }

    public BaseColorPickerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseColorPickerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BaseColorPickerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void init() {
        super.init();
        mMirrorLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMirrorLinePaint.setStyle(Paint.Style.FILL);
    }

    /**
     * 获取放大镜半径
     *
     * @return 放大镜半径
     */
    public int getMirrorRadius() {
        return mMirrorRadius;
    }

    /**
     * 设置颜色放大镜的半径
     *
     * @param radius 半径大小
     */
    public void setMirrorRadius(int radius) {
        if (radius <= 0 || mMirrorRadius == radius) return;
        this.mMirrorRadius = radius;
        postInvalidate();
    }

    /**
     * 获取是否有放大镜
     *
     * @return
     */
    public boolean getHasScaleMirror() {
        return mHasScaleMirror;
    }

    /**
     * 设置是否有颜色放大镜
     *
     * @param hasScaleMirror
     */
    public void setHasScaleMirror(boolean hasScaleMirror) {
        if (this.mHasScaleMirror == hasScaleMirror) return;
        this.mHasScaleMirror = hasScaleMirror;
        postInvalidate();
    }

    /**
     * 设置颜色选择监听器
     *
     * @param listener
     */
    public void setOnColorSelectedListener(OnColorSelectedListener listener) {
        mListener = listener;
    }
}
