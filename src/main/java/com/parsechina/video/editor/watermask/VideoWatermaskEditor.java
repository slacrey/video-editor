package com.parsechina.video.editor.watermask;

import com.parsechina.video.engine.BaseEditor;
import com.parsechina.video.engine.MediaInfo;
import com.parsechina.video.engine.Parameter;
import com.parsechina.video.utils.FilePathUtils;
import com.parsechina.video.utils.FilePreconditions;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;

/**
 * @author linfeng-eqxiu
 * @description //TODO 设计说明
 * @date 2019/1/24
 */
@Component
@Scope("prototype")
public class VideoWatermaskEditor extends BaseEditor {


    @Override
    protected boolean enableEditor(Parameter parameter) {
        return true;
    }

    @Override
    protected MediaInfo editor(MediaInfo mediaInfo, Parameter parameter) {

        String complex = "[0]scale=w=ceil(iw/2)*2:h=ceil(ih/2)*2[s0];[1]scale=w="
                + mediaInfo.getWidth() + ":h=" + mediaInfo.getHeight() + "[s1];[s0][s1]overlay=" + VideoWatermaskPosition.RIGHT_TOP.getValue();

        String finalInputPath = FilePreconditions.checkNotExist(mediaInfo.getFilePath(), "Input file not exist: %s", mediaInfo.getFilePath());
        String outPath = FilePathUtils.getFileNameOfMp4(Paths.get(finalInputPath).getParent().toString());

        String waterMaskPath = parameter.getParamAsObject(name());

        FFmpegBuilder fFmpegBuilder = getFfmpeg().builder();
        fFmpegBuilder.setInput(mediaInfo.getFilePath())
                .addInput(waterMaskPath)
                .setComplexFilter(complex)
                .addOutput(outPath)
                .setVideoCodec("libx264")
                .setAudioCodec("aac")
                .setVideoFrameRate(30)
                .setConstantRateFactor(18)
                .addExtraArgs("-max_muxing_queue_size", "1024");

        executorFFmpeg(fFmpegBuilder);

        return getMediaInfo(outPath);

    }

    @Override
    public String name() {
        return "watermask";
    }


}
