package com.parsechina.video.engine;

import com.parsechina.video.Response;
import com.parsechina.video.utils.IDGenerator;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.job.FFmpegJob;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;

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

        if (enableEditor()) {

            init();

            editor(request);

            clean();
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
     * 初始化，创建目录
     */
    private void init() {
        if (Files.notExists(Paths.get(getWorkDir()))) {
            try {
                Files.createDirectory(Paths.get(getWorkDir()));
            } catch (FileAlreadyExistsException ex) {
                log.warn("文件已经存在");
            } catch (IOException e) {
                log.error(e.getLocalizedMessage(), e);
            }
        }
    }

    /**
     * 清空目录
     */
    private void clean() {

        FileUtils.deleteQuietly(new File(getWorkDir()));

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
     * @return 是否可以执行，true：可以执行；false：不可以执行
     */
    protected abstract boolean enableEditor();

    /**
     * 编辑处理
     *
     * @param request 参数
     */
    protected abstract void editor(Parameter request);

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
