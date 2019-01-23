package com.parsechina.video.engine;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.gson.JsonObject;
import com.parsechina.video.utils.IDGenerator;

import java.util.Map;

/**
 * @author linfeng-eqxiu
 * @description
 * @date 2019/1/23
 */
public class Parameter {

    private JsonObject jsonObject;

    private final String id;
    private final Map<String, String> params;

    public Parameter() {
        id = IDGenerator.uuid();
        params = Maps.newHashMapWithExpectedSize(10);
    }

    public void put(String key, String value) {
        if (key != null && value != null) {
            params.put(key, value);
        }
    }

    public String getParamAsString(String key) {
        String finalKey = Preconditions.checkNotNull(key, "Get value of params's key is null").intern();
        return Preconditions.checkNotNull(params.get(finalKey), "The value corresponding to the {} in the parameter does not exist", finalKey);
    }

    public String getId() {
        return id;
    }

}
