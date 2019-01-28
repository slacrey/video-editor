package com.parsechina.video.editor.compress;

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
 * @date 2019/1/23
 */
@Component
@Scope("prototype")
public class VideoCompressEditor extends BaseEditor {

    @Override
    public String name() {
        return "compile";
    }

    @Override
    protected boolean enableEditor(Parameter parameter) {
        return true;
    }

    @Override
    public MediaInfo editor(MediaInfo mediaInfo, Parameter parameter) {

        String finalInputPath = FilePreconditions.checkNotExist(mediaInfo.getFilePath(), "Input file not exist: %s", mediaInfo.getFilePath());

        String outPath = FilePathUtils.getFileNameOfMp4(Paths.get(finalInputPath).getParent().toString());
        FFmpegBuilder fFmpegBuilder = getFfmpeg().builder();
        fFmpegBuilder.setInput(finalInputPath)
                .addOutput(outPath)
                .setVideoCodec("libx264")
                .setAudioCodec("aac")
                .setVideoFrameRate(30)
                .setConstantRateFactor(18)
                .addExtraArgs("-pix_fmt", "yuv420p")
                .addExtraArgs("-max_muxing_queue_size", "1024");

        executorFFmpeg(fFmpegBuilder);

        String finalOutPath = FilePreconditions.checkNotExist(outPath, "Output file not exist: %s", outPath);

        return getMediaInfo(finalOutPath);

    }

}
