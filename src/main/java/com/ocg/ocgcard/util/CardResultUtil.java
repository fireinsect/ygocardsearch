package com.ocg.ocgcard.util;

import com.ocg.ocgcard.Service.CardService;
import com.ocg.ocgcard.dataobject.CardAll;
import com.ocg.ocgcard.pojo.CardResult;
import com.ocg.ocgcard.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CardResultUtil {
    private static CardService cardService;

    @Autowired
    public CardResultUtil(CardService cardService){
        CardResultUtil.cardService=cardService;
    }

    public static Result<CardResult> getCardResult(Result<CardResult> result, int pageint, List<CardAll> cards) {
        CardResult cardResult = cardService.getCardAllResult(cards, pageint);
        ForbiddenUtil.forbiddenChange(cardResult.getCards());
        if (cardResult.getCards() == null) {
            result.setStatus(500);
            result.setSuccess(false);
            result.setData(cardResult);
            result.setMsg("获取失败");
        } else {
            cardResult.setNowNum(pageint);
            result.setStatus(200);
            result.setSuccess(true);
            result.setData(cardResult);
            result.setMsg("获取成功");
            Thread thread=new Thread(()->{
                resultRecord(result);
            });
            thread.start();
        }
        return result;
    }
    public static Result<CardResult> getCardOnePageResult(Result<CardResult> result, List<CardAll> cards) {
        CardResult cardResult = cardService.getCardAllResult(cards, 1);
        ForbiddenUtil.forbiddenChange(cardResult.getCards());
        if (cardResult.getCards() == null) {
            result.setStatus(500);
            result.setSuccess(false);
            result.setData(cardResult);
            result.setMsg("获取失败");
        } else {
            cardResult.setNowNum(1);
            result.setStatus(200);
            result.setSuccess(true);
            result.setData(cardResult);
            result.setMsg("获取成功");
        }
        return result;
    }
    public static boolean resultRecord(Result<CardResult> result){
        List<CardAll> cardAlls=result.getData().getCards();
        for (CardAll cardAll:cardAlls){
            cardService.doCardRecord(cardAll.getCardId());
        }
        return true;
    }
}
