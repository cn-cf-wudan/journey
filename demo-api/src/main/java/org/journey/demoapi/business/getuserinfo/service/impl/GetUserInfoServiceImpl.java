package org.journey.demoapi.business.getuserinfo.service.impl;

import org.journey.demoapi.business.getuserinfo.io.GetUserInfoIO;
import org.journey.demoapi.business.getuserinfo.service.IGetUserInfoService;
import org.journey.demoapi.business.getuserinfo.vo.GetUserInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @ClassName: GetUserInfoServiceImpl
 * @ClassNameExplain:
 * @Description: 业务层实现类
 * @author wudan-mac
 * @date 2016-06-29 19:55:29
 */
@Service
public class GetUserInfoServiceImpl implements IGetUserInfoService {

    static final Logger logger = LoggerFactory.getLogger(GetUserInfoServiceImpl.class);

    @Override
    public GetUserInfoVO getUserInfo(GetUserInfoIO getUserInfoIO) throws Exception {
        
        GetUserInfoVO getUserInfoVO = new GetUserInfoVO();


        return getUserInfoVO == null ? new GetUserInfoVO() : getUserInfoVO;
    }
}