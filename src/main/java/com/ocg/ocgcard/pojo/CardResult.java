package com.ocg.ocgcard.pojo;

import com.ocg.ocgcard.dataobject.Card;
import com.ocg.ocgcard.dataobject.CardAll;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class CardResult implements Serializable {
    List<CardAll> cards;
    int pageNum;
    int amount;
    int nowNum;
}
