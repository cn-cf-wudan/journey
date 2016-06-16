package com.sinldo.server.business.basic.loginsuccess.controller;

import com.sinldo.server.business.basic.loginsuccess.service.ILoginSuccessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

/**
 * @ClassName: LoginSuccessController
 * @ClassNameExplain:
 * @Description:
 * @author wudan-mac
 * @date 2016-05-27 17:19:10
 */
@RequestMapping(value = "")
@Controller
public class LoginSuccessController {

    static final Logger logger = LoggerFactory.getLogger(LoginSuccessController.class);

    @Resource
    ILoginSuccessService loginSuccessService;

    /**
     * @Title: loginSuccess
     * @TitleExplain:
     * @Description: 登录成功
     * @return
     * @version
     * @author wudan-mac
     */
    @RequestMapping(value = "/loginSuccess")
    public ModelAndView loginSuccess(RedirectAttributes attr) throws Exception {
        return loginSuccessService.loginSuccess(attr);
    }

}