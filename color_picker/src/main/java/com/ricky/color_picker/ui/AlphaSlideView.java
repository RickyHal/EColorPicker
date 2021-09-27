package com.ricky.color_picker.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.HapticFeedbackConstants;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

import com.ricky.color_picker.api.ISlidePicker;
import com.ricky.color_picker.api.OnAlphaSelectedListener;
import com.ricky.color_picker.base.BaseColorPickerView;

/**
 * @author RickyHal
 * @date 2021/2/7
 */
public class AlphaSlideView extends BaseColorPickerView implements ISlidePicker {
    private OnAlphaSelectedListener alphaListener;

    private float mAlpha = 0f;

    public AlphaSlideView(Context context) {
        super(context);
    }

    public AlphaSlideView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AlphaSlideView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AlphaSlideView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        AlphaGridDrawable drawable = new AlphaGridDrawable();
        setBackground(drawable);
        slideLinePaint.setStrokeWidth(slideLineWidth);
        slideLinePaint.setColor(slideLineColor);
        int startColor = mCurrentColor;
        int endColor = Color.TRANSPARENT;
        Shader shader = new LinearGradient(0, 0, getWidth() * 1f, getHeight() * 1f, startColor, endColor, Shader.TileMode.CLAMP);
        mPaint.setShader(shader);
        if (mTouchX == -1 || mTouchY == -1) {
            setPosition(0f);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        slideLinePaint.setColor(slideLineColor);
        canvas.drawRect(0, 0, getWidth() * 1f, getHeight(), mPaint);
        drawSlideLine(canvas);
    }

    /**
     * 画指示线
     *
     * @param canvas
     */
    private void drawSlideLine(Canvas canvas) {
        if (hasSlideLine) {
            if (mTouchX < slideLineWidth / 2) {
                mTouchX = slideLineWidth / 2;
            }
            if (mTouchX > getWidth() - slideLineWidth / 2) {
                mTouchX = getWidth() - slideLineWidth / 2;
            }
            canvas.drawLine(mTouchX, 0, mTouchX, getHeight(), slideLinePaint);
        }
    }

    /**
     * 设置基色
     *
     * @param color
     */
    public void setBaseColor(@ColorInt int color) {
        this.mCurrentColor = color;
        int startColor = mCurrentColor;
        int endColor = Color.TRANSPARENT;
        Shader shader = new LinearGradient(0, 0, getWidth() * 1f, getHeight() * 1f, startColor, endColor, Shader.TileMode.CLAMP);
        mPaint.setShader(shader);
        postInvalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 计算当前颜色
     */
    @Override
    protected void computeCurrent() {
        if (mAlpha == getCurrentAlpha()) return;
        mAlpha = getCurrentAlpha();
        int r = Color.red(mCurrentColor);
        int g = Color.green(mCurrentColor);
        int b = Color.blue(mCurrentColor);
        int a = (int) (255 * mAlpha);
        int color = (a << 24) | (r << 16) | (g << 8) | b;
        if (mAlpha == 0 || mAlpha == 1) {
            performHapticFeedback(
                    HapticFeedbackConstants.LONG_PRESS,
                    HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING
            );
        }
        if (mIsRelease) {
            if (mCurrentColor != -1) {
                if (mListener != null) mListener.onColorSelected(color);
                if (alphaListener != null) alphaListener.onAlphaSelected(1 - mAlpha);
            }
        } else {
            if (mCurrentColor != -1) {
                if (mListener != null) mListener.onColorSelected(color);
                if (alphaListener != null) alphaListener.onAlphaSelecting(1 - mAlpha);
            }
        }
    }

    /**
     * 获取点对应的颜色
     *
     * @return
     */
    private float getCurrentAlpha() {
        float alpha = mTouchX * 1f / getWidth();
        if (alpha >= 1f) alpha = 1f;
        if (alpha <= 0f) alpha = 0f;
        return alpha;
    }

    @Override
    public void setPosition(float ratio) {
        mTouchX = getMeasuredWidth() * ratio;
        mAlpha = getCurrentAlpha();
        postInvalidate();
    }

    public void setOnAlphaSelectedListener(OnAlphaSelectedListener listener) {
        this.alphaListener = listener;
    }
}
