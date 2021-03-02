package com.init.mini.web.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JSONFormatUtil {

    public static <T>JSONObject twoArrToJSON(T[][] arr) throws Exception {
        if (arr == null) return null;

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (int i=0; i<arr.length; i++) {
            JSONArray temp = new JSONArray();
            for (int j=0; j<arr[i].length; j++) {
                temp.add(arr[i][j]);
            }
            jsonArray.add(temp);
        }
        jsonObject.put("row", arr.length);
        jsonObject.put("col",arr[0].length);
        jsonObject.put("arr", jsonArray);

        return jsonObject;
    }
}
