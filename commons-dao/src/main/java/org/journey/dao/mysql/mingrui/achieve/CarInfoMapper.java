package org.journey.dao.mysql.mingrui.achieve;

import org.journey.po.mingrui.mysql.CarInfo;

public interface CarInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(CarInfo record);

    CarInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarInfo record);

}