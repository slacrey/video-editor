package com.parsechina.video.editor;

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

}
