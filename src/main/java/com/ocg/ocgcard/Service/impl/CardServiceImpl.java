package com.ocg.ocgcard.Service.impl;

import com.ocg.ocgcard.Service.CardService;
import com.ocg.ocgcard.dao.CardDAO;
import com.ocg.ocgcard.dao.CardNameDAO;
import com.ocg.ocgcard.dataobject.Card;
import com.ocg.ocgcard.dataobject.CardAll;
import com.ocg.ocgcard.pojo.CardResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CardDAO cardDAO;

    @Autowired
    CardNameDAO cardNameDAO;

    private static int pagesize=5;

//    public CardResult getCardResult(List<Card> cards, int page) {
//        CardResult cardResult=new CardResult();
//        float cardsize=cards.size();
//        cardResult.setAmount(cards.size());
//        cardResult.setPageNum((int)Math.ceil(cardsize/pagesize));
//        if(page>cardResult.getPageNum()){
//            cardResult.setCards(null);
//        }else if(page==cardResult.getPageNum()){
//            List<Card> re=cards.subList(5*(page-1),cards.size());
//            cardResult.setCards(re);
//        }else{
//            List<Card> re=cards.subList(5*(page-1),5*page);
//            cardResult.setCards(re);
//        }
//        return cardResult;
//    }

    public CardResult getCardAllResult(List<CardAll> cards, int page) {
        CardResult cardResult=new CardResult();
        float cardsize=cards.size();
        cardResult.setAmount(cards.size());
        cardResult.setPageNum((int)Math.ceil(cardsize/pagesize));
        if(page>cardResult.getPageNum()){
            cardResult.setCards(null);
        }else if(page==cardResult.getPageNum()){
            List<CardAll> re=cards.subList(5*(page-1),cards.size());
            cardResult.setCards(re);
        }else{
            List<CardAll> re=cards.subList(5*(page-1),5*page);
            cardResult.setCards(re);
        }
        return cardResult;
    }

    @Override
    public List<CardAll> searchByEn(String enName) {
        return null;
    }
}
