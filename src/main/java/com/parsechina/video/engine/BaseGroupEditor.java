package com.parsechina.video.engine;

import com.parsechina.video.utils.IDGenerator;

/**
 * @author linfeng-eqxiu
 * @description //TODO 设计说明
 * @date 2019/1/24
 */
public abstract class BaseGroupEditor implements GroupEditor<EditorReport> {

    private Parameter parameter;

    @Override
    public void params(Parameter parameter) {
        this.parameter = parameter;
    }

    @Override
    public String uuid() {
        return IDGenerator.uuid();
    }

}
