package com.parsechina.video.engine;

import com.parsechina.video.Request;
import com.parsechina.video.Response;
import com.parsechina.video.utils.FilePreconditions;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.job.FFmpegJob;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * @author linfeng-eqxiu
 * @description
 * @date 2019/1/23
 */
public abstract class BaseEditor<T> implements Editor<Response> {

    private FFmpeg ffmpeg;
    private FFprobe ffprobe;
    private String workDir;

    protected BaseEditor() {
        try {
            ffmpeg = new FFmpeg();
            ffprobe = new FFprobe();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        workDir = System.getProperty("user.home");
    }

    private Request<T> request;

    @Override
    public Response call() throws Exception {

        if (enableEditor()) {

            editor(request.getData());
        }

        return Response.Builder.ofError();
    }

    @Override
    public String uuid() {
        return UUID.randomUUID().toString();
    }

    public String getWorkDir() {
        return workDir + File.separator + uuid();
    }

    private void init() {
        FilePreconditions.checkNotExist(getWorkDir(), "");
        try {
            Files.createDirectory(Paths.get(workDir + File.separator + uuid()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化参数，由编辑引擎统一设置
     *
     * @param request 请求参数
     */
    private void params(Request<T> request) {
        this.request = request;
    }

    /**
     * 是否可以运行编辑
     *
     * @return 是否可以执行，true：可以执行；false：不可以执行
     */
    protected abstract boolean enableEditor();

    /**
     * 编辑处理
     *
     * @param request 参数
     */
    protected abstract void editor(T request);

    @Override
    public FFmpeg getFfmpeg() {
        return ffmpeg;
    }

    @Override
    public FFprobe getFfprobe() {
        return ffprobe;
    }

    protected void executorFFmpeg(FFmpegBuilder builder) {
        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
        FFmpegJob fFmpegJob = executor.createJob(builder);
        fFmpegJob.run();
    }

}
