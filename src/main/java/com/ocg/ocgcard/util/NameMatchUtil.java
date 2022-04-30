package com.ocg.ocgcard.util;

public class NameMatchUtil {
    public static String matchName(String name){
        if (name.contains("CNO")){
            name=name.replace("CNO","混沌NO");
        }
        if (name.contains("HERO")){
            name=name.replace("HERO","英雄");
        }
        return name;
    }
}
