package com.eqxiu.video.render.service.bean;

/**
 * @author linfeng-eqxiu
 * @description 音频信息
 * @date 2018/12/10
 */
public class AudioInfo {
    /**
     * 音频本地路径
     */
    private String audioPath;
    /**
     * 音频音量
     */
    private int volume;

    public AudioInfo(String audioPath, int volume) {
        this.audioPath = audioPath;
        this.volume = volume;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public int getVolume() {
        return volume;
    }

}
