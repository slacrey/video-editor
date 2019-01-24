package com.parsechina.video.editor;

/**
 * @author linfeng-eqxiu
 * @description 默认工作结果
 * @date 2018/10/24
 */
public class DefaultEditorReport implements EditorReport<MediaInfo> {
    /**
     * 工作状态
     */
    private EditorStatus status;
    /**
     * 异常
     */
    private Exception error;
    /**
     * 媒体信息
     */
    private MediaInfo mediaInfo;

    private DefaultEditorReport(EditorStatus status) {
        this.status = status;
    }

    private DefaultEditorReport(EditorStatus status, Exception error) {
        this.status = status;
        this.error = error;
    }

    private DefaultEditorReport(EditorStatus status, MediaInfo mediaInfo) {
        this.status = status;
        this.mediaInfo = mediaInfo;
    }

    public static DefaultEditorReport ofError() {
        return new DefaultEditorReport(EditorStatus.FAILED);
    }

    public static DefaultEditorReport ofError(String message) {
        return new DefaultEditorReport(EditorStatus.FAILED, new Exception(message));
    }

    public static DefaultEditorReport ofError(Exception error) {
        return new DefaultEditorReport(EditorStatus.FAILED, error);
    }

    public static DefaultEditorReport ofOk(MediaInfo mediaInfo) {
        return new DefaultEditorReport(EditorStatus.COMPLETED, mediaInfo);
    }

    @Override
    public EditorStatus getStatus() {
        return status;
    }

    @Override
    public Throwable getError() {
        return error;
    }

    @Override
    public MediaInfo getData() {
        return mediaInfo;
    }

    @Override
    public String toString() {
        return "DefaultEditorReport {" +
                "status=" + status +
                ", error=" + (error == null ? "''" : error) +
                '}';
    }
}
