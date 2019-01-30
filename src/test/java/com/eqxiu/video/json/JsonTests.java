package com.eqxiu.video.json;

import com.google.gson.JsonObject;
import org.junit.Test;

/**
 * @author linfeng-eqxiu
 * @description //TODO 设计说明
 * @date 2019/1/24
 */
public class JsonTests {

    private JsonObject jsonObject = new JsonObject();

    @Test
    public void testJson() {

        jsonObject.addProperty("test", "222");
        jsonObject.addProperty("test", "333");

        System.out.println(jsonObject.get("test"));

    }

    @Test
    public void testaa() {
        System.out.println(Math.random());
    }
}
