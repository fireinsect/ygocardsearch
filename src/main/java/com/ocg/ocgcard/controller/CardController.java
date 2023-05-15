package com.ocg.ocgcard.controller;

import com.github.houbb.opencc4j.util.ZhConverterUtil;
import com.ocg.ocgcard.Service.CardService;
import com.ocg.ocgcard.dao.CardDAO;
import com.ocg.ocgcard.dataobject.Card;
import com.ocg.ocgcard.dataobject.CardAll;
import com.ocg.ocgcard.dataobject.CardNKN;
import com.ocg.ocgcard.dataobject.DailyCard;
import com.ocg.ocgcard.pojo.CardResult;
import com.ocg.ocgcard.pojo.GetCardModel;
import com.ocg.ocgcard.pojo.Result;
import com.ocg.ocgcard.pojo.SearchGet;
import com.ocg.ocgcard.util.CardResultUtil;
import com.ocg.ocgcard.util.ForbiddenUtil;
import com.ocg.ocgcard.util.HttpUtil;
import com.ocg.ocgcard.util.NameMatchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class CardController {

    static final List<String> typeList= Stream.of("怪兽","魔法","陷阱").collect(Collectors.toList());

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
        return CardResultUtil.getCardResult(result, pageint, cards);
    }

    @GetMapping("getCard")
    @ResponseBody
    public Result<CardResult> getCard(@RequestParam(name = "name") String name, @RequestParam(name = "type", required = false) String type, @RequestParam(name = "page", required = false) String page) {
        Result<CardResult> result = new Result<>();
        List<CardAll> cards;
        if (page == null || page.replaceAll(" ", "") == "") {
            page = "1";
        }
        int pageint = Integer.parseInt(page);
        //判断是否为纯数字
        if(name.matches("[0-9]+")){
            cards=cardDAO.searchByid(name);
            if (cards.size()==0){
                cards=searchByName(name,type);
            }
        }else{
            cards=searchByName(name,type);
        }
        return CardResultUtil.getCardResult(result, pageint, cards);
    }
    @GetMapping("guessCard")
    @ResponseBody
    public Result<CardResult> guessCard(@RequestParam(name = "name") String name) {
        Result<CardResult> result = new Result<>();
        List<CardAll> cards;
        cards=searchByName(name,null);
        return CardResultUtil.getCardOnePageResult(result, cards);
    }
//多项条件查询
//    @GetMapping("getCard")
//    @ResponseBody
//    public Result<CardResult> getCard(GetCardModel getCardModel) {
//        Result<CardResult> result = new Result<>();
//        List<CardAll> cards;
//        if (getCardModel.getPage() == null || getCardModel.getPage().replaceAll(" ", "") == "") {
//            getCardModel.setPage("1");
//        }
//        int pageint = Integer.parseInt(getCardModel.getPage());
//        if(getCardModel.getName()!=null){
//            if(getCardModel.getName().matches("[0-9]+")){
//                cards=cardDAO.searchByid(getCardModel.getName());
//                if (cards.size()==0){
//                    //条件查询
//                    cards=cardDAO.searchByCondition(getCardModel);
//                }
//            }else{
//                //条件查询
//                cards=cardDAO.searchByCondition(getCardModel);
//            }
//        }else{
//            //条件查询
//            cards=cardDAO.searchByCondition(getCardModel);
//        }
//
//        return getCardResult(result, pageint, cards);
//    }

    @GetMapping("searchCardId")
    @ResponseBody
    public Result<CardResult> searchCardId(@RequestParam(name = "cardId") String cardId) {
        Result<CardResult> result = new Result<>();
        List<CardAll> cards = cardDAO.searchByid(cardId);
        return CardResultUtil.getCardOnePageResult(result, cards);
    }
    @GetMapping("randomCard")
    @ResponseBody
    public Result<CardResult> randomCard() {
        Result<CardResult> result = new Result<>();
        List<CardAll> cards = cardDAO.randomSearch();
        CardResultUtil.getCardOnePageResult(result, cards);
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


    private List<CardAll> searchByName(String name,String type){
        String orgName=name;
        List<CardAll> cards;
        if (!ZhConverterUtil.isSimple(name)){
            name=ZhConverterUtil.toSimple(name);
        }
        name=name.replaceAll(" ","");
        name=NameMatchUtil.nickNameMath(name);
        if(type!=null&&typeList.contains(type)){
            cards = cardDAO.searchBylikeWithType(name,type);
        }else{
            cards = cardDAO.searchBylike(name);
            if (cards.size()==0){
                //如果没找到卡则去白鸽上找
                List<SearchGet> searchGets= HttpUtil.getSearch(orgName);
                if (searchGets!=null){
                    if (searchGets.size()!=0){
                        cards=cardDAO.searchByid(searchGets.get(0).getId()+"");
                    }
                }
            }
        }
        return cards;
    }


}