package com.parsechina.video.editor;

import com.parsechina.video.engine.BaseEditor;
import com.parsechina.video.engine.Parameter;
import com.parsechina.video.utils.FilePathUtils;
import com.parsechina.video.utils.FilePreconditions;
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
public class VideoCompressEditor extends BaseEditor {

    @Override
    protected boolean enableEditor(Parameter parameter) {
        return true;
    }

    @Override
    public void editor(Parameter parameter) {

        String inputPath = parameter.getParamAsString("");

        FilePreconditions.checkNotExist(inputPath, "Input file not found: {}" + inputPath);

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

        FilePreconditions.checkNotExist(outPath, "Output file not found: {}" + outPath);

    }

    @Override
    public String name() {
        return "compile";
    }

}
