package com.parsechina.video.utils;

import com.google.common.base.Strings;

import java.nio.file.Files;
import java.nio.file.Paths;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author linfeng-eqxiu
 * @description //TODO 设计说明
 * @date 2019/1/23
 */
public final class FilePreconditions {

    /**
     * 检查文件是否存在
     *
     * @param fileName         文件路径
     * @param errorMessage     提示消息
     * @param errorMessageArgs 提示消息的参数
     * @return 经过检查的文件名称
     */
    public static String checkNotExist(String fileName, String errorMessage, Object... errorMessageArgs) {
        boolean empty = Strings.isNullOrEmpty(fileName) || Files.notExists(Paths.get(fileName));
        if (errorMessageArgs == null) {
            checkArgument(!empty, errorMessage);
        } else {
            checkArgument(!empty, errorMessage, errorMessageArgs);
        }
        return fileName;
    }

}
