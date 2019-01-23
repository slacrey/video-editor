package com.parsechina.video.utils;

import com.google.common.base.Preconditions;

import java.io.File;

/**
 * @author linfeng-eqxiu
 * @description //TODO 设计说明
 * @date 2019/1/23
 */
public final class FilePathUtils {

    FilePathUtils() {
        throw new AssertionError("No instances for you!");
    }

    public static String getFileName(String folder, String suffix) {
        return folder + File.separator + IDGenerator.uuid() + IDGenerator.randomNumber() + suffix;
    }

    public static String getFileNameOfMp4(String folder) {
        return getFileName(Preconditions.checkNotNull(folder, "File folder is null"), ".mp4");
    }
}
