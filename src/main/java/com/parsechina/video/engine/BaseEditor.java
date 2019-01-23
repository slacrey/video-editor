package com.parsechina.video.engine;

import com.parsechina.video.Response;
import com.parsechina.video.utils.IDGenerator;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.job.FFmpegJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.File;

/**
 * @author linfeng-eqxiu
 * @description
 * @date 2019/1/23
 */
public abstract class BaseEditor implements Editor<Response> {

    private Logger log = LoggerFactory.getLogger(getClass());
    private FFmpeg ffmpeg;
    private FFprobe ffprobe;
    private String videoHome;
    private String workDir;

    protected BaseEditor() {
        try {
            ffmpeg = new FFmpeg();
            ffprobe = new FFprobe();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        videoHome = System.getProperty("video.home");
    }

    private Parameter request;

    @Override
    public Response call() throws Exception {

        if (enableEditor(request)) {

            editor(request);
        }

        return Response.Builder.ofError();
    }

    @Override
    public String uuid() {
        return IDGenerator.uuid();
    }

    protected String getWorkDir() {
        if (StringUtils.isEmpty(workDir)) {
            workDir = videoHome + File.separator + uuid();
        }
        return workDir;
    }

    /**
     * 初始化参数，由编辑引擎统一设置
     *
     * @param request 请求参数
     */
    private void params(Parameter request) {
        this.request = request;
    }

    /**
     * 是否可以运行编辑
     *
     * @param parameter 参数
     * @return 是否可以执行，true：可以执行；false：不可以执行
     */
    protected abstract boolean enableEditor(Parameter parameter);

    /**
     * 编辑处理
     *
     * @param parameter 参数
     */
    protected abstract void editor(Parameter parameter);

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
