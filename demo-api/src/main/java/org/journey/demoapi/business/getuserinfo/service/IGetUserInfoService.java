package org.journey.demoapi.business.getuserinfo.service;

import org.journey.demoapi.business.getuserinfo.io.GetUserInfoIO;
import org.journey.demoapi.business.getuserinfo.vo.GetUserInfoVO;

/**
 * @ClassName: IGetUserInfoService
 * @ClassNameExplain:
 * @Description: 业务层接口类
 * @author wudan-mac
 * @date 2016-06-29 19:55:29
 */
public interface IGetUserInfoService {

    /**
    * @Title: getUserInfo
    * @TitleExplain:
    * @Description: 业务层接口方法定义
    * @param GetUserInfoVO
    * @return org.journey.demoapi.business.getuserinfo.vo.GetUserInfoVO
    * @version
    * @author wudan-mac
    */
    GetUserInfoVO getUserInfo(GetUserInfoIO getUserInfoIO) throws Exception;

}