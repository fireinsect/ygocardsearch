package com.ocg.ocgcard.dao;

import com.ocg.ocgcard.dataobject.CardRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CardRecordDAO {
    CardRecord hasCord(int cardId);
    int addRecord(CardRecord cardRecord);
    int timeAdd(CardRecord cardRecord);
}
