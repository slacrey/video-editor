/*
 * Copyright (c) 2018. eqxiu.com 北京中网易企秀科技有限公司  All rights reserved.
 */

package com.parsechina.video.engine;

import java.io.Serializable;

/**
 * 媒体信息
 *
 * @author linfeng-eqxiu
 * @date 2018/11/7
 */
public class MediaInfo implements Serializable {

    private static final long serialVersionUID = 5160788698125999570L;

    public static final String DEFAULT_VIDEO_CODEC = "h264";
    public static final String DEFAULT_AUDIO_CODEC = "aac";
    public static final String DEFAULT_R_FRAME_RATE = "25/1";
    public static final int DEFAULT_SD_CRF = 25;
    public static final int DEFAULT_HD_CRF = 10;

    private String filePath;
    private String codecType;
    private double duration;
    /**
     * File size in bytes
     */
    private long size;
    private int width;
    private int height;

    private String videoCodecName = DEFAULT_VIDEO_CODEC;
    private String audioCodecName = DEFAULT_AUDIO_CODEC;

    private boolean hasAudio;
    private boolean hasVideo;


    public String getFilePath() {
        return filePath;
    }

    public String getCodecType() {
        return codecType;
    }

    public double getDuration() {
        return duration;
    }

    public long getSize() {
        return size;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setCodecType(String codecType) {
        this.codecType = codecType;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getVideoCodecName() {
        return videoCodecName;
    }

    public void setVideoCodecName(String videoCodecName) {
        this.videoCodecName = videoCodecName;
    }

    public String getAudioCodecName() {
        return audioCodecName;
    }

    public void setAudioCodecName(String audioCodecName) {
        this.audioCodecName = audioCodecName;
    }

    public static String getVideoCodeC(MediaInfo mediaInfo) {
//        return DEFAULT_VIDEO_CODEC.equalsIgnoreCase(mediaInfo.getVideoCodecName()) ? "copy" : DEFAULT_VIDEO_CODEC;
        return DEFAULT_VIDEO_CODEC;
    }

    public static String getAudioCodeC(MediaInfo mediaInfo) {
//        return DEFAULT_AUDIO_CODEC.equalsIgnoreCase(mediaInfo.getVideoCodecName()) ? "copy" : DEFAULT_AUDIO_CODEC;
        return DEFAULT_AUDIO_CODEC;
    }

    public boolean isHasAudio() {
        return hasAudio;
    }

    public void setHasAudio(boolean hasAudio) {
        this.hasAudio = hasAudio;
    }

    public void setHasVideo(boolean hasVideo) {
        this.hasVideo = hasVideo;
    }

    public boolean isHasVideo() {
        return hasVideo;
    }
}
