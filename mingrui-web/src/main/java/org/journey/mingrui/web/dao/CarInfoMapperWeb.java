package org.journey.mingrui.web.dao;


import org.apache.ibatis.annotations.Param;
import org.journey.po.mingrui.mysql.CarInfo;

import java.util.List;

public interface CarInfoMapperWeb {

    List<CarInfo> selectBySearchAndPage(@Param("model") String model, @Param("engine") String engine);

}