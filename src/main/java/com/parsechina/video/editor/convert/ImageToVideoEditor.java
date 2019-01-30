package com.parsechina.video.editor.convert;

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
 * @author linfeng
 * @date 2019/1/24
 **/
@Component
@Scope("prototype")
public class ImageToVideoEditor extends BaseEditor {

    @Override
    protected boolean enableEditor(Parameter parameter) {
        return true;
    }

    @Override
    protected MediaInfo editor(MediaInfo mediaInfo, Parameter parameter) {


        String finalInputPath = FilePreconditions.checkNotExist(mediaInfo.getFilePath(), "Input file not exist: %s", mediaInfo.getFilePath());
        String outPath = FilePathUtils.getFileNameOfMp4(Paths.get(finalInputPath).getParent().toString());

        String duration = getNumberFormat().format(mediaInfo.getDuration());

        FFmpegBuilder fFmpegBuilder = getFfmpeg().builder();
        fFmpegBuilder.setInput(finalInputPath).addExtraArgs("-loop", "1")
                .addOutput(outPath)
                .setVideoCodec("libx264")
                .setAudioCodec("aac")
                .setConstantRateFactor(18)
                .setVideoFrameRate(24)
                .setVideoWidth(mediaInfo.getWidth())
                .setVideoHeight(mediaInfo.getHeight())
                .addExtraArgs("-t", duration)
                .addExtraArgs("-pix_fmt", "yuv420p")
                .addExtraArgs("-max_muxing_queue_size", "1024");

        executorFFmpeg(fFmpegBuilder);

        return getMediaInfo(outPath);

    }

    @Override
    public String name() {
        return "imageToVideo";
    }

}
