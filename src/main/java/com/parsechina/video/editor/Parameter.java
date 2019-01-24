package com.parsechina.video.editor;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.parsechina.video.utils.IDGenerator;

import java.util.Map;

/**
 * @author linfeng-eqxiu
 * @description
 * @date 2019/1/23
 */
public class Parameter {

    private static final String MEDIA_INFO_KEY = "media_info";
    private final String id;
    private final Map<String, Object> params;

    public Parameter() {
        id = IDGenerator.uuid();
        params = Maps.newHashMapWithExpectedSize(10);
    }

    public String getId() {
        return id;
    }

    public void put(String key, Object value) {
        if (key != null && value != null) {
            params.put(key, value);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T getParamAsObject(String key) {
        String finalKey = Preconditions.checkNotNull(key, "Get value of params's key is null").intern();
        return (T) Preconditions.checkNotNull(params.get(finalKey), "The value corresponding to the key[%s] in the parameter does not exist", finalKey);
    }

    public void put(MediaInfo mediaInfo) {
        put(MEDIA_INFO_KEY, mediaInfo);
    }

    public MediaInfo getMediaInfo() {
        return getParamAsObject(MEDIA_INFO_KEY);
    }
}
