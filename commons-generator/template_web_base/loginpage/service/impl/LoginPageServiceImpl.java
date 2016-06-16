package com.sinldo.server.business.basic.loginpage.service.impl;

import com.sinldo.server.business.basic.loginpage.service.ILoginPageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @ClassName: LoginPageServiceImpl
 * @ClassNameExplain:
 * @Description: 业务层实现类
 * @author wudan-mac
 * @date 2016-05-27 17:08:59
 */
@Service
public class LoginPageServiceImpl implements ILoginPageService {

    static final Logger logger = LoggerFactory.getLogger(LoginPageServiceImpl.class);

    @Override
    public ModelAndView loginPage(RedirectAttributes attr) throws Exception {

        ModelAndView modelAndView = new ModelAndView("base/login");


        return modelAndView;
    }
}