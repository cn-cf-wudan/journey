package com.sinldo.server.business.basic.loginexpired.controller;

import com.sinldo.server.business.basic.loginexpired.service.ILoginExpiredService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

/** 
 * @ClassName: LoginExpiredController 
 * @ClassNameExplain: 
 * @Description:  登录失效控制器
 * @author wudan-mac
 * @date 16/5/11 下午6:04
 */
@RequestMapping(value = "")
@Controller
public class LoginExpiredController {

    static final Logger logger = LoggerFactory.getLogger(LoginExpiredController.class);

    @Resource
    ILoginExpiredService loginExpiredService;

    /**
     * @Title: loginExpired
     * @TitleExplain:
     * @Description: 登录失效方法
     * @param
     * @return org.springframework.web.servlet.ModelAndView
     * @version
     * @author wudan-mac
     */
    @RequestMapping(value = "/loginExpired")
    public ModelAndView loginExpired(RedirectAttributes attr) throws Exception {
        return loginExpiredService.loginExpired(attr);
    }

}