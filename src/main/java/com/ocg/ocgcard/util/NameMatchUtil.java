package com.ocg.ocgcard.util;

import com.ocg.ocgcard.dao.CardNKNDAO;
import com.ocg.ocgcard.dataobject.CardNKN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class NameMatchUtil {
    @Autowired
    CardNKNDAO cardNKNDAO;

    public static List<CardNKN> type0;
    public static List<CardNKN> type1;

    @PostConstruct
    public void init(){
        type0=cardNKNDAO.select0Type();
        type1=cardNKNDAO.select1Type();
    }

    public static String nickNameMath(String name){
        name=name.toUpperCase(Locale.ROOT);
        for(CardNKN cardNKN:type0){
            if(name.equals(cardNKN.getNickName())){
                return cardNKN.getName();
            }
        }
        name = matchName(name.toUpperCase(Locale.ROOT));
        return name;
    }

    public static String matchName(String name){
        String toName=null;
        for (CardNKN cardNKN:type1){
            if(name.contains(cardNKN.getNickName())){
                name=name.replace(cardNKN.getNickName(), "▶");
                toName=cardNKN.getName();
            }
        }
        List<String> namechar = new ArrayList<>();
        for (int i = 0; i < name.length(); i++) {
            namechar.add(name.charAt(i) + "");
        }
        String nameforsearch = String.join("%", namechar);
        if(toName!=null){
            nameforsearch=nameforsearch.replace("▶",toName);
        }
        return nameforsearch;
    }
}
