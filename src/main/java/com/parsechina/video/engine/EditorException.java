package com.parsechina.video.engine;

/**
 * @author linfeng-eqxiu
 * @description 编辑异常
 * @date 2019/1/23
 */
public class EditorException extends RuntimeException {

    public EditorException(String message) {
        super(message);
    }

    public EditorException(String message, Throwable cause) {
        super(message, cause);
    }
}
