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

    public static String checkNotExist(String arg, String errorMessage) {
        boolean empty = Strings.isNullOrEmpty(arg) || Files.notExists(Paths.get(arg));
        checkArgument(!empty, errorMessage);
        return arg;
    }

}
