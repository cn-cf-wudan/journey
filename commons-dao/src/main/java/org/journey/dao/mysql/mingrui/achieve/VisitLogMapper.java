package org.journey.dao.mysql.mingrui.achieve;

import org.journey.po.mingrui.mysql.VisitLog;

public interface VisitLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VisitLog record);

    int insertSelective(VisitLog record);

    VisitLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VisitLog record);

    int updateByPrimaryKey(VisitLog record);
}