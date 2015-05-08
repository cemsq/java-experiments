package com;

import org.json.*;

/**
 * Created by César Mora on 09.10.2014.
 */
public class JSonTest {

    public void parseJSON(String json) {
        JSONObject obj = new JSONObject(json);
        String pageName = obj.getJSONObject("pageInfo").getString("pageName");

        JSONArray arr = obj.getJSONArray("posts");
        for (int i = 0; i < arr.length(); i++)
        {
            String post_id = arr.getJSONObject(i).getString("post_id");

        }
    }
}
