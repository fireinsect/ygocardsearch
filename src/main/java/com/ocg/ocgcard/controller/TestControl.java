package com.ocg.ocgcard.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.ocg.ocgcard.pojo.SearchGet;
import com.ocg.ocgcard.util.HttpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestControl {

    @GetMapping("/test")
    @ResponseBody
    public String test(String name){
        String a= HttpUtil.jsonGet(name);
        JSONObject jsonObject= JSON.parseObject(a);
        String b=JSONObject.toJSONString(jsonObject.get("result"));
        List<SearchGet> searchGets=JSONObject.parseArray(b,SearchGet.class);
        return a;
    }
}
