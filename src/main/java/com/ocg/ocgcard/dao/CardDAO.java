package com.ocg.ocgcard.dao;

import com.ocg.ocgcard.dataobject.Card;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface CardDAO {
    List<Card> searchBylike(@Param("name")String name);

    List<Card> searchByid(@Param("cardId")String cardId);

    List<Card> randomSearch();
}
