package com.ocg.ocgcard.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
//获取查卡条件
public class GetCardModel implements Serializable {
    //名称
    String name;
    //属性
    String attribute;
    //等级
    String level;
    //种类
    String type;
    //种族
    String race;
    //页码
    String page;
}
