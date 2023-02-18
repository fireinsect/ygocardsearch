package com.ocg.ocgcard.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.ocg.ocgcard.dao.CardRecordDAO;
import com.ocg.ocgcard.dataobject.CardRecord;
import com.ocg.ocgcard.pojo.SearchGet;
import com.ocg.ocgcard.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestControl {
    @Autowired
    CardRecordDAO cardRecordDAO;

    @GetMapping("/test")
    @ResponseBody
    public String test(int id){

        return id+"";
    }
}
