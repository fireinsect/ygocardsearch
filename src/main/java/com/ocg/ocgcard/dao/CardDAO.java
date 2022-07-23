package com.ocg.ocgcard.dao;

import com.ocg.ocgcard.dataobject.Card;
import com.ocg.ocgcard.dataobject.CardAll;
import com.ocg.ocgcard.dataobject.DailyCard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface CardDAO {
    List<CardAll> searchBylike(@Param("name")String name);

    List<CardAll> searchBylikeWithType(@Param("name")String name,@Param("type")String type);

    List<CardAll> searchByid(@Param("cardId")String cardId);

    List<CardAll> randomSearch();

    List<DailyCard> searchAllDaily();

    List<CardAll> searchByEn(@Param("enName")String enName);
}
