package com.parsechina.video.editor;

import com.parsechina.video.engine.BaseEditor;
import com.parsechina.video.engine.MediaInfo;
import com.parsechina.video.engine.Parameter;
import com.parsechina.video.utils.FilePathUtils;
import net.bramp.ffmpeg.builder.FFmpegBuilder;

/**
 * @author linfeng-eqxiu
 * @description //TODO 设计说明
 * @date 2019/1/30
 */
public class BlankAudioEditor extends BaseEditor {
    @Override
    protected boolean enableEditor(Parameter parameter) {
        return false;
    }

    @Override
    protected MediaInfo editor(MediaInfo mediaInfo, Parameter parameter) {

        String outPath = FilePathUtils.getFileNameOfMp4(mediaInfo.getFilePath());
        FFmpegBuilder fFmpegBuilder = getFfmpeg().builder();
        fFmpegBuilder.addInput("aevalsrc=0")
                .setFormat("lavfi")
                .addOutput(outPath)
                .addExtraArgs("-t", mediaInfo.getDuration() + "")
                .setAudioQuality(9)
                .setAudioCodec("libmp3lame");
        executorFFmpeg(fFmpegBuilder);
        return null;
    }

    @Override
    public String name() {
        return null;
    }
}
