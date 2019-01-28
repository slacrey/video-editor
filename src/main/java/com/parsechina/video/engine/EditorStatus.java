package com.parsechina.video.engine;

/**
 * @author linfeng-eqxiu
 * @description //TODO 设计说明
 * @date 2019/1/23
 */
public enum EditorStatus {
    /**
     *
     */
    COMPLETED {
        @Override
        public boolean isOk() {
            return true;
        }
    }, FAILED {
        @Override
        public boolean isOk() {
            return false;
        }
    };

    public boolean isOk() {
        throw new AbstractMethodError();
    }

}
