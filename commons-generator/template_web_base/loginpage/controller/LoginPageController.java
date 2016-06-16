package com.sinldo.server.business.basic.loginpage.controller;

import com.sinldo.server.business.basic.loginpage.service.ILoginPageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

/**
 * @ClassName: LoginPageController
 * @ClassNameExplain:
 * @Description:
 * @author wudan-mac
 * @date 2016-05-27 17:08:59
 */
@RequestMapping(value = "")
@Controller
public class LoginPageController {

    static final Logger logger = LoggerFactory.getLogger(LoginPageController.class);

    @Resource
    ILoginPageService loginPageService;

    /**
     * @Title: loginPage
     * @TitleExplain:
     * @Description: 跳转登录页面
     * @return
     * @version
     * @author wudan-mac
     */
    @RequestMapping(value = "/loginPage")
    public ModelAndView loginPage(RedirectAttributes attr) throws Exception {
        return loginPageService.loginPage(attr);
    }

}