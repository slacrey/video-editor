package com.parsechina.video.engine;

import com.google.common.base.Preconditions;
import com.parsechina.video.utils.FilePathUtils;
import com.parsechina.video.utils.FilePreconditions;
import com.parsechina.video.utils.IDGenerator;
import com.parsechina.video.utils.MediaUtils;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.job.FFmpegJob;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.File;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.concurrent.TimeUnit;

/**
 * @author linfeng-eqxiu
 * @description
 * @date 2019/1/23
 */
public abstract class BaseEditor implements Editor<EditorReport<MediaInfo>> {

    private Logger log = LoggerFactory.getLogger(getClass());
    private FFmpeg ffmpeg;
    private FFprobe ffprobe;
    private NumberFormat numberFormat;
    private String videoHome;
    private String workDir;

    private Parameter parameter;

    protected BaseEditor() {
        try {
            ffmpeg = new FFmpeg();
            ffprobe = new FFprobe();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        videoHome = System.getProperty("video.home");
        numberFormat = NumberFormat.getNumberInstance();
        // 保留两位小数
        numberFormat.setMaximumFractionDigits(2);
        // 如果不需要四舍五入，可以使用RoundingMode.DOWN
        numberFormat.setRoundingMode(RoundingMode.UP);

    }

    @Override
    public EditorReport<MediaInfo> call() throws Exception {

        try {
            MediaInfo mediaInfo = null;
            if (enableEditor(parameter)) {
                Preconditions.checkNotNull(parameter.getMediaInfo(), "input media info is null");
                FilePreconditions.checkNotExist(parameter.getMediaInfo().getFilePath(), "Input file path of media info not exist: %s", parameter.getMediaInfo().getFilePath());

                mediaInfo = editor(parameter.getMediaInfo(), parameter);
            }
            return EditorReport.Default.ofOk(mediaInfo);
        } catch (Exception e) {
            return EditorReport.Default.ofError(e);
        }

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
     * @param parameter 请求参数
     */
    @Override
    public void params(Parameter parameter) {
        this.parameter = parameter;
    }

    /**
     * 得到ffmpeg实例
     *
     * @return ffmpeg实例
     */
    public FFmpeg getFfmpeg() {
        return ffmpeg;
    }


    /**
     * 得到ffprobe实例
     *
     * @return ffprobe实例
     */
    public FFprobe getFfprobe() {
        return ffprobe;
    }

    protected void executorFFmpeg(FFmpegBuilder builder) {
        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
        FFmpegJob fFmpegJob = executor.createJob(builder);
        fFmpegJob.run();
    }

    private FFmpegProbeResult getProbeResult(String filePath) {
        FFmpegProbeResult result;
        try {
            result = ffprobe.probe(filePath);
        } catch (Exception e) {
            log.warn(e.getLocalizedMessage(), e);
            throw new EditorException("获取视频信息失败", e);
        }
        return result;
    }

    /**
     * 获得媒体信息
     *
     * @param filePath 文件路径
     * @return 媒体信息
     */
    protected MediaInfo getMediaInfo(String filePath) {
        //等待文件写操作完成
        FilePathUtils.waitFileTransferComplete(filePath, 50, TimeUnit.MILLISECONDS);
        FFmpegProbeResult result = getProbeResult(filePath);
        return MediaUtils.ofProbeResult(result);
    }

    protected NumberFormat getNumberFormat() {
        return numberFormat;
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
     * @param mediaInfo 媒体信息
     * @param parameter 参数
     */
    protected abstract MediaInfo editor(MediaInfo mediaInfo, Parameter parameter);

}
