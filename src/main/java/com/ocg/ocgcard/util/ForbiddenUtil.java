package com.ocg.ocgcard.util;

import com.ocg.ocgcard.dao.ForbiddenDAO;
import com.ocg.ocgcard.dataobject.CardAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
public class ForbiddenUtil {
    @Autowired
    ForbiddenDAO forbiddenDAO;

    //禁止卡表（仅仅记录名称）
    public static List<String> bans;

    //限制卡表
    public static List<String> rests;

    //准限制卡表
    public static List<String> pres;

    @PostConstruct
    public void init(){
        bans=forbiddenDAO.getBans();
        rests=forbiddenDAO.getRests();
        pres=forbiddenDAO.getPres();
    }

    public static void forbiddenChange(List<CardAll> cards){
        if(cards!=null){
            for (CardAll card:cards){
                if(bans.contains(card.getName())){
                    card.setForbidden("禁");
                }else if (rests.contains(card.getName())){
                    card.setForbidden("限");
                }else if(pres.contains(card.getName())){
                    card.setForbidden("准限");
                }
            }
        }
    }
}
