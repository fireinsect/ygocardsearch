package com.ocg.ocgcard.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ocg.ocgcard.pojo.SearchGet;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.util.List;

public class HttpUtil {
    final static String url="https://ygocdb.com/api/v0/?search=";

    public static String jsonGet(String name){
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder().url(url+name).build();
        Call call=okHttpClient.newCall(request);
        try {
            String result=call.execute().body().string();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<SearchGet> getSearch(String name){
        String json= jsonGet(name);
        JSONObject jsonObject= JSON.parseObject(json);
        String jsonarray=JSONObject.toJSONString(jsonObject.get("result"));
        List<SearchGet> searchGets=JSONObject.parseArray(jsonarray,SearchGet.class);
        return searchGets;
    }
}
