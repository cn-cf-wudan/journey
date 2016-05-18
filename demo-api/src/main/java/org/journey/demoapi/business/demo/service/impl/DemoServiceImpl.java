package org.journey.demoapi.business.demo.service.impl;

import org.journey.demoapi.business.demo.io.DemoIO;
import org.journey.demoapi.business.demo.service.IDemoService;
import org.journey.demoapi.business.demo.vo.DemoVO;
import org.journey.exception.core.RestExceptionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @ClassName: DemoServiceImpl
 * @ClassNameExplain:
 * @Description: 业务层实现类
 * @author wudan-mac
 * @date 2016-05-17 17:25:48
 */
@Service
public class DemoServiceImpl implements IDemoService {

    static final Logger logger = LoggerFactory.getLogger(DemoServiceImpl.class);

    @Override
    public DemoVO demo(DemoIO demoIO) throws Exception {
        
        DemoVO demoVO = new DemoVO();
        demoVO.setCode(RestExceptionConstants.SUCCESS_CODE);
        demoVO.setMessage(RestExceptionConstants.SUCCESS_MSG);
        demoVO.setPhotoCode("111");
        demoVO.setUserName("吴丹丹");

        return demoVO == null ? new DemoVO() : demoVO;
    }
}