/*
 * Copyright (c) 2018. eqxiu.com 北京中网易企秀科技有限公司  All rights reserved.
 */

package com.parsechina.video.editor.watermask;

/**
 * @author linfeng-eqxiu
 * @description 视频水印位置
 * @date 2018/10/29
 */
public enum VideoWatermaskPosition {

    /**
     * 左上角
     */
    LEFT_TOP("10:10"),
    /**
     * 右上角
     */
    RIGHT_TOP("main_w-overlay_w-10:10"),
    /**
     * 左下角
     */
    LEFT_BOTTOM("10:main_h-overlay_h-10"),
    /**
     * 右下角
     */
    RIGHT_BOTTOM("main_w-overlay_w-10:main_h-overlay_h-10");

    private String value;

    VideoWatermaskPosition(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
