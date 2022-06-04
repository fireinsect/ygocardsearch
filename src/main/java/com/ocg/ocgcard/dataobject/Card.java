package com.ocg.ocgcard.dataobject;

import lombok.Data;

import java.io.Serializable;

@Data
public class Card implements Serializable {
    int id;
    int cardId;
    String name;
    String jpName;
    String enName;
    String effect;
    String zz;
    String mainType;
    String type;
    String level;
    String attribute;
    String atk;
    String def;
}
