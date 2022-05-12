package com.ocg.ocgcard.controller;

import com.github.houbb.opencc4j.util.ZhConverterUtil;
import com.ocg.ocgcard.Service.CardService;
import com.ocg.ocgcard.dao.CardDAO;
import com.ocg.ocgcard.dataobject.Card;
import com.ocg.ocgcard.dataobject.DailyCard;
import com.ocg.ocgcard.pojo.CardResult;
import com.ocg.ocgcard.pojo.Result;
import com.ocg.ocgcard.util.NameMatchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class CardController {

    @Autowired
    CardDAO cardDAO;

    @Autowired
    CardService cardService;

    @GetMapping("getCard")
    @ResponseBody
    public Result<CardResult> getCard(@RequestParam(name = "name") String name, @RequestParam(name = "page", required = false) String page) {
        Result<CardResult> result = new Result<>();
        if (!ZhConverterUtil.isSimple(name)){
            name=ZhConverterUtil.toSimple(name);
        }
        if (name.equals("不可以涩涩")||name.equals("不可以色色")||name.equals("可以色色")||name.equals("可以涩涩")){
            name="摸鱼的G";
        }
        if (page == null || page.replaceAll(" ", "") == "") {
            page = "1";
        }
        int pageint = Integer.parseInt(page);
        List<String> namechar = new ArrayList<>();
        name = NameMatchUtil.matchName(name.toUpperCase(Locale.ROOT));
        for (int i = 0; i < name.length(); i++) {
            namechar.add(name.charAt(i) + "");
        }
        String nameforsearch = String.join("%", namechar);
        List<Card> cards = cardDAO.searchBylike(nameforsearch);
        CardResult cardResult = cardService.getCardResult(cards, pageint);
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
        }
        return result;
    }

    @GetMapping("searchCardId")
    @ResponseBody
    public Result<CardResult> searchCardId(@RequestParam(name = "cardId") String cardId) {
        Result<CardResult> result = new Result<>();
        List<Card> cards = cardDAO.searchByid(cardId);
        getCardResult(result, cards);
        return result;
    }
    @GetMapping("randomCard")
    @ResponseBody
    public Result<CardResult> randomCard() {
        Result<CardResult> result = new Result<>();
        List<Card> cards = cardDAO.randomSearch();
        getCardResult(result, cards);
        return result;
    }
    @GetMapping("searchDaily")
    @ResponseBody
    public Result<List<DailyCard>> searchDaily() {
        Result<List<DailyCard>> result = new Result<>();
        List<DailyCard> cards = cardDAO.searchAllDaily();
        result.setData(cards);
        return result;
    }


    private void getCardResult(Result<CardResult> result, List<Card> cards) {
        CardResult cardResult = cardService.getCardResult(cards, 1);
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
    }
}