package com.parsechina.video.engine;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFprobe;

import java.util.concurrent.Callable;

/**
 * @author linfeng-eqxiu
 * @description 视频编辑接口
 * @date 2019/1/22
 */
interface Editor<T> extends Callable<T> {

    /**
     * 得到ffmpeg实例
     *
     * @return ffmpeg实例
     */
    FFmpeg getFfmpeg();

    /**
     * 得到ffprobe实例
     *
     * @return ffprobe实例
     */
    FFprobe getFfprobe();

    /**
     * 单元名称
     *
     * @return 单元名称
     */
    String name();

    /**
     * 唯一码
     *
     * @return 唯一码
     */
    String uuid();
}
