package com.ocg.ocgcard.Service;

import com.ocg.ocgcard.dataobject.Card;
import com.ocg.ocgcard.dataobject.CardAll;
import com.ocg.ocgcard.pojo.CardResult;

import java.util.List;

public interface CardService {
//    CardResult getCardResult(List<Card> cards, int page);

    CardResult getCardAllResult(List<CardAll> cards, int page);
    List<CardAll> searchByEn(String enName);
}
