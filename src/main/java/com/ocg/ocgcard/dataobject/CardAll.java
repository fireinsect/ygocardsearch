package com.ocg.ocgcard.dataobject;

import lombok.Data;

import java.io.Serializable;

@Data
public class CardAll extends Card implements Serializable {
    String jpName;
    String enName;
    String forbidden="-";
}
