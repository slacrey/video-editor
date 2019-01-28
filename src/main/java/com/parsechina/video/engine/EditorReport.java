package com.parsechina.video.engine;

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


    class Default implements EditorReport<MediaInfo> {
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

        private Default(EditorStatus status) {
            this.status = status;
        }

        private Default(EditorStatus status, Exception error) {
            this.status = status;
            this.error = error;
        }

        private Default(EditorStatus status, MediaInfo mediaInfo) {
            this.status = status;
            this.mediaInfo = mediaInfo;
        }

        public static Default ofError() {
            return new Default(EditorStatus.FAILED);
        }

        public static Default ofError(String message) {
            return new Default(EditorStatus.FAILED, new Exception(message));
        }

        public static Default ofError(Exception error) {
            return new Default(EditorStatus.FAILED, error);
        }

        public static Default ofOk(MediaInfo mediaInfo) {
            return new Default(EditorStatus.COMPLETED, mediaInfo);
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

}
