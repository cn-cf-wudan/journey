package org.journey.demoapi.business.demo.service.impl;

import org.journey.demoapi.business.demo.io.DemoIO;
import org.journey.demoapi.business.demo.vo.DemoVO;
import org.journey.demoapi.business.demo.service.IDemoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    @Resource

    @Override
    public DemoVO demo(DemoIO demoIO) throws Exception {
        
        DemoVO demoVO = new DemoVO();


        return demoVO == null ? new DemoVO() : demoVO;
    }
}