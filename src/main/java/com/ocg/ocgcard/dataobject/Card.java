package com.ocg.ocgcard.dataobject;

import lombok.Data;

import java.io.Serializable;

@Data
public class Card implements Serializable {
    private int id;
    private String name;
    private String typeMain;
    private String type;
    private String effect;
    private String meta;
}
