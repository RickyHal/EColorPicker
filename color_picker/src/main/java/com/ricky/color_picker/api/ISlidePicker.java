package com.ricky.color_picker.api;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * @author RickyHal
 * @date 2021/2/7
 */
public interface ISlidePicker {
    /**
     * 是否有指示条
     */
    boolean hasSlideLine = true;
    /**
     * 指示条paint
     */
    Paint slideLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    /**
     * 指示条颜色
     */
    int slideLineColor = Color.WHITE;
    /**
     * 指示条宽度
     */
    int slideLineWidth = 5;

    void setPosition(float ratio);
}
