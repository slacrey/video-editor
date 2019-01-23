package com.parsechina.video.editor;

import com.parsechina.video.engine.BaseEditor;
import com.parsechina.video.utils.FilePathUtils;
import com.parsechina.video.utils.FilePreconditions;
import com.google.gson.JsonObject;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author linfeng-eqxiu
 * @description //TODO 设计说明
 * @date 2019/1/23
 */
@Component
@Scope("prototype")
public class VideoCompileEditor extends BaseEditor<String> {

    private JsonObject params;

    @Override
    protected boolean enableEditor() {
        return true;
    }

    @Override
    public void editor(String inputPath) {

        FilePreconditions.checkNotExist(inputPath, "File not found: {}" + inputPath);

        String outPath = FilePathUtils.getFileNameOfMp4(getWorkDir());
        FFmpegBuilder fFmpegBuilder = getFfmpeg().builder();
        fFmpegBuilder.setInput(inputPath)
                .addOutput(outPath)
                .setVideoCodec("libx264")
                .setAudioCodec("aac")
                .setVideoFrameRate(30)
                .setConstantRateFactor(18)
                .addExtraArgs("-max_muxing_queue_size", "1024");

        executorFFmpeg(fFmpegBuilder);

    }

    @Override
    public String name() {
        return null;
    }

}
