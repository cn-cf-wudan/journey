package com.sinldo.server.business.basic.loginsuccess.service.impl;

import com.sinldo.server.business.basic.loginsuccess.service.ILoginSuccessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @ClassName: LoginSuccessServiceImpl
 * @ClassNameExplain:
 * @Description: 业务层实现类
 * @author wudan-mac
 * @date 2016-05-27 17:19:10
 */
@Service
public class LoginSuccessServiceImpl implements ILoginSuccessService {

    static final Logger logger = LoggerFactory.getLogger(LoginSuccessServiceImpl.class);

    @Override
    public ModelAndView loginSuccess(RedirectAttributes attr) throws Exception {

        //这里定向到系统的首页
        ModelAndView modelAndView = new ModelAndView("redirect:/manage/main");


        return modelAndView;
    }
}