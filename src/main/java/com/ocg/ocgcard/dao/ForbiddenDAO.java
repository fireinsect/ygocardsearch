package com.ocg.ocgcard.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ForbiddenDAO {
    List<String> getBans();

    List<String> getRests();

    List<String> getPres();
}
