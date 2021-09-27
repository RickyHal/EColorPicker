package com.ricky.color_picker.api;

/**
 * @author RickyHal
 * @date 2021/2/7
 */

import androidx.annotation.ColorInt;

/**
 * 颜色选择监听器
 *
 * @author RickyHal
 */
public interface OnColorSelectedListener {
    /**
     * 回传当前选中的颜色，触摸时会多次调用
     *
     * @param color
     */
    void onColorSelecting(@ColorInt int color);

    /**
     * 回传最终的颜色，触摸时会多次调用
     *
     * @param color
     */
    void onColorSelected(@ColorInt int color);
}