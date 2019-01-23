/*
 * Copyright (c) 2018. eqxiu.com 北京中网易企秀科技有限公司  All rights reserved.
 */

package com.parsechina.video.utils;

import org.springframework.util.StringUtils;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author linfeng-eqxiu
 * @description 腾讯云下载地址URL 工具
 * @date 2018/10/31
 */
public final class HttpUrlUtils {

    public static final String URL_PREFIX_TENCENT = "/tencent";
    private static final String URL_PREFIX_HTTPS = "https://";
    private static final String URL_PREFIX_HTTP = "http://";
    private static final String URL_PREFIX_HTTP_NO_STUFF = "http:";
    public static final String URL_PREFIX_NO_PROTO = "//";
    public static final String URL_PREFIX_SPLIT = "/";
    private static final String COS_MYQCLOUD_COM = ".myqcloud.com";

    public static String md5Url(String url) {
        if (StringUtils.isEmpty(url)) {
            return url;
        }
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("md5");
            messageDigest.update(url.getBytes(Charset.forName("UTF-8")));
            byte[] digest = messageDigest.digest();
            for (byte b : digest) {
                // 转成了 16 机制
                String hexStr = Integer.toHexString(b & 0xff);
                //不足补0
                sb.append(hexStr.length() == 0 ? "0" + hexStr : hexStr);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String getCommonHttpUrl(String url) {
        if (url.startsWith(URL_PREFIX_HTTPS)) {
            return url;
        } else if (url.startsWith(URL_PREFIX_HTTP)) {
            return url;
        } else if (url.startsWith(URL_PREFIX_NO_PROTO)) {
            return URL_PREFIX_HTTP_NO_STUFF + url;
        } else {
            return url;
        }
    }

    public static String getUrlFileName(String url) {
        return url.substring(url.lastIndexOf(URL_PREFIX_SPLIT) + 1);
    }

    /**
     * 根据以往的Key获取上传Key的前缀
     *
     * @param key 上传Key
     * @return Key前缀
     */
    public static String getPrefixCosKey(String key) {
        if (key != null) {
            int index = key.lastIndexOf(URL_PREFIX_SPLIT);
            if (index > -1) {
                return key.substring(0, index);
            }
        }
        return key;
    }

    /**
     * 是否是腾讯的Key
     *
     * @param url 腾讯的Key
     * @return 是否是腾讯云
     */
    public static Boolean isTencentCos(String url) {
        return url.startsWith(URL_PREFIX_TENCENT);
    }

    private static class HttpUtilsHolder {
        private static HttpUrlUtils singleton = new HttpUrlUtils();
    }

    public static HttpUrlUtils getInstance() {
        return HttpUtilsHolder.singleton;
    }

    public static String genUploadKeyPrefix() {
        return IDGenerator.uuid() + randomNumber();
    }

    public static String genUploadKeyPrefix(String url) {
        return md5Url(url) + randomNumber();
    }

    /**
     * 隨機數
     *
     * @return 隨機數
     */
    public static String randomNumber() {
        return String.valueOf((int) ((Math.random() * 9 + 1) * 10000));
    }
}
