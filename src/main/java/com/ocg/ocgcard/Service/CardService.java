package com.ocg.ocgcard.Service;

import com.ocg.ocgcard.dataobject.Card;
import com.ocg.ocgcard.pojo.CardResult;

import java.util.List;

public interface CardService {
    CardResult getCardResult(List<Card> cards,int page);

}
