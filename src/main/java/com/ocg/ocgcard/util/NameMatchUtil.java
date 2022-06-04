package com.ocg.ocgcard.util;

public class NameMatchUtil {
    public static String nickNameMath(String name){
        switch (name){
            case "不可以涩涩":
            case "不可以色色":
            case "可以色色":
            case "可以涩涩":
                name="摸鱼的G";
                break;
            case "群可爱":
                name="查卡姬";
                break;
            case "查卡鸡":
            case "查卡猪":
            case "查卡\uD83D\uDC37":
            case "查卡\uD83D\uDC14":
                name="废物";
        }
        return name;
    }

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
