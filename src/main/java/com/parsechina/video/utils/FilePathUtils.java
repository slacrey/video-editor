package com.parsechina.video.utils;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @author linfeng-eqxiu
 * @description //TODO 设计说明
 * @date 2019/1/23
 */
public final class FilePathUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilePathUtils.class);

    FilePathUtils() {
        throw new AssertionError("No instances for you!");
    }

    private static String getFileName(String folder, String suffix) {
        return folder + File.separator + IDGenerator.uuid() + IDGenerator.randomNumber() + suffix;
    }

    public static String getFileNameOfMp4(String folder) {
        return getFileName(Preconditions.checkNotNull(folder, "File folder is null"), ".mp4");
    }

    /**
     * 等待文件写操作完成
     *
     * @param watchFilePath 被监控的文件
     * @param interval      监听文件时间间隔
     * @param unit          时间间隔单位
     */
    public static void waitFileTransferComplete(String watchFilePath, long interval, TimeUnit unit) {

        long oldLen = 0;
        long newLen;
        File watchFile = new File(watchFilePath);
        while (true) {
            newLen = watchFile.length();
            if ((newLen - oldLen) > 0) {
                oldLen = newLen;
                try {
                    Thread.sleep(unit.toMillis(interval));
                } catch (InterruptedException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            } else {
                break;
            }
        }

    }

}
