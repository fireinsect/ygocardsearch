package com.ocg.ocgcard.pojo;

import com.ocg.ocgcard.dataobject.Card;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class CardResult implements Serializable {
    List<Card> cards;
    int pageNum;
    int amount;
    int nowNum;
}
