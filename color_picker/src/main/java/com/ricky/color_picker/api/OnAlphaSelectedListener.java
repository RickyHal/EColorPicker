package com.ricky.color_picker.api;

/**
 * @author RickyHal
 * @date 2021/2/7
 */

/**
 * 透明度选择监听器
 *
 * @author RickyHal
 */
public interface OnAlphaSelectedListener {
    /**
     * 回传当前选中的透明度，触摸时会多次调用
     *
     * @param alpha 0-1f
     */
    public void onAlphaSelecting(float alpha);

    /**
     * 回传最终的透明度，手指弹起时调用
     *
     * @param alpha 0-1f
     */
    public void onAlphaSelected(float alpha);
}