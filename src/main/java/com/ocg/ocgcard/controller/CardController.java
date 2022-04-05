package com.ocg.ocgcard.controller;

import com.ocg.ocgcard.Service.CardService;
import com.ocg.ocgcard.dao.CardDAO;
import com.ocg.ocgcard.dataobject.Card;
import com.ocg.ocgcard.pojo.CardResult;
import com.ocg.ocgcard.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CardController {

    @Autowired
    CardDAO cardDAO;

    @Autowired
    CardService cardService;

    @GetMapping("getCard")
    @ResponseBody
    public Result<CardResult> getCard(@RequestParam( name="name")String name, @RequestParam( name="page",required = false)String page){
        Result<CardResult> result=new Result<>();
        if (page==null||page.replaceAll(" ","")==""){
            page="1";
        }
        int pageint=Integer.parseInt(page);
        List<Card> cards=cardDAO.searchBylike(name);
        CardResult cardResult=cardService.getCardResult(cards,pageint);
        if(cardResult.getCards()==null){
            result.setStatus(500);
            result.setSuccess(false);
            result.setData(cardResult);
            result.setMsg("获取失败");
        }else{
            result.setStatus(200);
            result.setSuccess(true);
            result.setData(cardResult);
            result.setMsg("获取成功");
        }
        return result;
    }
}
