package com.parsechina.video;

/**
 * @author linfeng-eqxiu
 * @description //TODO 设计说明
 * @date 2019/1/23
 */
public interface Response<T> {

    /**
     * 执行状态
     *
     * @return 执行状态
     */
    EditorState getState();

    String getMessage();

    T getData();

    class Builder implements Response<String> {

        private final EditorState state;
        private final String message;

        private Builder(EditorState state, String message) {
            this.state = state;
            this.message = message;
        }

        @Override
        public EditorState getState() {
            return state;
        }

        @Override
        public String getMessage() {
            return message;
        }

        @Override
        public String getData() {
            return null;
        }

        public static Response ofError() {
            return new Builder(EditorState.FAILED, "");
        }

        public static Response ofError(String message) {
            return new Builder(EditorState.FAILED, message);
        }
    }


}
