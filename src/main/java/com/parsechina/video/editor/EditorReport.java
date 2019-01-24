package com.parsechina.video.editor;

/**
 * @author linfeng-eqxiu
 * @description 任务结果
 * @date 2018/10/24
 */
public interface EditorReport<T> {

    /**
     * Get work execution status.
     *
     * @return execution status
     */
    EditorStatus getStatus();

    /**
     * Get error if any.
     *
     * @return error
     */
    Throwable getError();

    /**
     * 执行结果
     *
     * @return 结果
     */
    T getData();

}
