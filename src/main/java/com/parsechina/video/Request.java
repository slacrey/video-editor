package com.parsechina.video;

/**
 * @author linfeng-eqxiu
 * @description
 * @date 2019/1/23
 */
public class Request<T> extends Transfer {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
