package com.ocg.ocgcard.dataobject;

import lombok.Data;

import java.io.Serializable;

@Data
public class DailyCard implements Serializable {
    int id;
    String name;
    String text;
    String type;
    int nums;
}
