package com.sinldo.server.business.basic.loginexpired.service.impl;

import com.sinldo.server.business.basic.loginexpired.service.ILoginExpiredService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @ClassName: LoginExpiredServiceImpl
 * @ClassNameExplain:
 * @Description: 登录失效业务实现方法
 * @author wudan-mac
 * @date 16/5/11 下午6:07
 */
@Service
public class LoginExpiredServiceImpl implements ILoginExpiredService {

    static final Logger logger = LoggerFactory.getLogger(LoginExpiredServiceImpl.class);

    @Override
    public ModelAndView loginExpired(RedirectAttributes attr) throws Exception {

        ModelAndView modelAndView = new ModelAndView("base/loginExpiredView");


        return modelAndView;
    }
}