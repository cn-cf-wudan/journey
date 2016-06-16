package com.sinldo.server.business.basic.loginpage.service;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @ClassName: ILoginPageService
 * @ClassNameExplain:
 * @Description: 业务层接口类
 * @author wudan-mac
 * @date 2016-05-27 17:08:59
 */
public interface ILoginPageService {

    /**
     * @Title: loginPage
     * @TitleExplain:
     * @Description: 业务层接口方法定义
     * @return com.sinldo.server.business.basic.loginpage.vo.LoginPageVO
     * @version
     * @author wudan-mac
     */
     ModelAndView loginPage(RedirectAttributes attr) throws Exception;

}