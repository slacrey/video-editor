package com.parsechina.video.engine;

import java.util.concurrent.Callable;

/**
 * @author linfeng-eqxiu
 * @description 视频编辑接口
 * @date 2019/1/22
 */
public interface Editor<T> extends Callable<T> {

    /**
     * 唯一码
     *
     * @return 唯一码
     */
    String uuid();

    /**
     * 单元名称
     *
     * @return 单元名称
     */
    String name();

    /**
     * 设置变量
     *
     * @param parameter 参数
     */
    void params(Parameter parameter);
}
