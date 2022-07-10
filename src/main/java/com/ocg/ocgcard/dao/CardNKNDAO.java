package com.ocg.ocgcard.dao;

import com.ocg.ocgcard.dataobject.CardNKN;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CardNKNDAO {
    List<CardNKN> select0Type();

    List<CardNKN> select1Type();
}
