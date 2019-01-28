package com.parsechina.video.utils;

import com.google.common.base.Preconditions;
import com.parsechina.video.editor.MediaInfo;
import net.bramp.ffmpeg.probe.FFmpegFormat;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import net.bramp.ffmpeg.probe.FFmpegStream;

/**
 * @author linfeng-eqxiu
 * @description 媒体文件信息工具类
 * @date 2018/11/26
 */
public final class MediaUtils {

    /**
     * 构造媒体信息
     *
     * @param result ffmpeg信息对象
     * @return 媒体信息
     */
    public static MediaInfo ofProbeResult(FFmpegProbeResult result) {

        Preconditions.checkNotNull(result, "FFmpegProbeResult is null");

        FFmpegFormat fFmpegFormat = result.getFormat();
        MediaInfo mediaInfo = new MediaInfo();
        mediaInfo.setFilePath(fFmpegFormat.filename);
        mediaInfo.setSize(fFmpegFormat.size);
        mediaInfo.setDuration(fFmpegFormat.duration);

        int tempWidth;
        int tempHeight;
        for (FFmpegStream stream : result.getStreams()) {
            if (FFmpegStream.CodecType.VIDEO.equals(stream.codec_type)) {
                if (stream.tags != null) {
                    String rotate = stream.tags.get("rotate");
                    boolean equalsFlag = "90".equals(rotate);
                    tempWidth = equalsFlag ? stream.height : stream.width;
                    tempHeight = equalsFlag ? stream.width : stream.height;
                } else {
                    tempWidth = stream.width;
                    tempHeight = stream.height;
                }

                mediaInfo.setHeight(tempHeight);
                mediaInfo.setWidth(tempWidth);
                mediaInfo.setVideoCodecName(stream.codec_name);
                mediaInfo.setHasVideo(true);
            } else if (FFmpegStream.CodecType.AUDIO.equals(stream.codec_type)) {
                mediaInfo.setHasAudio(true);
                mediaInfo.setAudioCodecName(stream.codec_name);
            }
        }

        return mediaInfo;
    }


}
