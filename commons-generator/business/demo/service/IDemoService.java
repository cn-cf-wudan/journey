package org.journey.demoapi.business.demo.service;

import org.journey.demoapi.business.demo.io.DemoIO;
import org.journey.demoapi.business.demo.vo.DemoVO;

/**
 * @ClassName: IDemoService
 * @ClassNameExplain:
 * @Description: 业务层接口类
 * @author wudan-mac
 * @date 2016-05-17 17:25:48
 */
public interface IDemoService {

    /**
    * @Title: demoApi
    * @TitleExplain:
    * @Description: 业务层接口方法定义
    * @param DemoVO
    * @return org.journey.demoapi.business.demo.vo.DemoVO
    * @version
    * @author wudan-mac
    */
    DemoVO demo(DemoIO demoIO) throws Exception;

}