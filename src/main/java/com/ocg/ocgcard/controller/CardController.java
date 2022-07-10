package com.ocg.ocgcard.controller;

import com.github.houbb.opencc4j.util.ZhConverterUtil;
import com.ocg.ocgcard.Service.CardService;
import com.ocg.ocgcard.dao.CardDAO;
import com.ocg.ocgcard.dataobject.Card;
import com.ocg.ocgcard.dataobject.CardAll;
import com.ocg.ocgcard.dataobject.CardNKN;
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

    static List<CardNKN> nkn1List;

    @Autowired
    CardDAO cardDAO;

    @Autowired
    CardService cardService;

    @GetMapping("getCardByEn")
    @ResponseBody
    public Result<CardResult> getCardByEn(@RequestParam(name = "enName") String enName, @RequestParam(name = "page", required = false) String page) {
        Result<CardResult> result = new Result<>();
        if (page == null || page.replaceAll(" ", "") == "") {
            page = "1";
        }
        List<String> namechar = new ArrayList<>();
        for (int i = 0; i < enName.length(); i++) {
            namechar.add(enName.charAt(i) + "");
        }
        String nameforsearch = String.join("%", namechar);
        int pageint = Integer.parseInt(page);
        List<CardAll> cards = cardDAO.searchByEn(nameforsearch);
        return getCardResult(result, pageint, cards);
    }

    @GetMapping("getCard")
    @ResponseBody
    public Result<CardResult> getCard(@RequestParam(name = "name") String name, @RequestParam(name = "page", required = false) String page) {
        Result<CardResult> result = new Result<>();
        if (!ZhConverterUtil.isSimple(name)){
            name=ZhConverterUtil.toSimple(name);
        }
        if (page == null || page.replaceAll(" ", "") == "") {
            page = "1";
        }
        name=NameMatchUtil.nickNameMath(name);
        int pageint = Integer.parseInt(page);
        List<CardAll> cards = cardDAO.searchBylike(name);
        return getCardResult(result, pageint, cards);
    }

    @GetMapping("searchCardId")
    @ResponseBody
    public Result<CardResult> searchCardId(@RequestParam(name = "cardId") String cardId) {
        Result<CardResult> result = new Result<>();
        List<CardAll> cards = cardDAO.searchByid(cardId);
        getCardOnePageResult(result, cards);
        return result;
    }
    @GetMapping("randomCard")
    @ResponseBody
    public Result<CardResult> randomCard() {
        Result<CardResult> result = new Result<>();
        List<CardAll> cards = cardDAO.randomSearch();
        getCardOnePageResult(result, cards);
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



    //结果返回

    private void getCardOnePageResult(Result<CardResult> result, List<CardAll> cards) {
        CardResult cardResult = cardService.getCardAllResult(cards, 1);
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
    private Result<CardResult> getCardResult(Result<CardResult> result, int pageint, List<CardAll> cards) {
        CardResult cardResult = cardService.getCardAllResult(cards, pageint);
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
}