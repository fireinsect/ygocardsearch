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

    public static Map<String,List<String>> type0;
    public static List<CardNKN> type1;

    @PostConstruct
    public void init(){
        type0=new HashMap<>();
        List<CardNKN> type0s=cardNKNDAO.select0Type();
        for (CardNKN t:type0s){
            if (type0.containsKey(t.getNickName())){
                type0.get(t.getNickName()).add(t.getName());
            }else{
                List<String> type0T=new ArrayList<>();
                type0T.add(t.getName());
                type0.put(t.getNickName(),type0T);
            }
        }
        type1=cardNKNDAO.select1Type();
    }


    public List<String> nickNameMath(String name){
        // 大写化名称
        name=name.toUpperCase(Locale.ROOT);
        if (type0.containsKey(name)){
            return type0.get(name);
        }
        name = matchName(name.toUpperCase(Locale.ROOT));
        return Arrays.asList(name);
    }

    public String matchName(String name){
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
