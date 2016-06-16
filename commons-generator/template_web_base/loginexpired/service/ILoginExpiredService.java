package com.sinldo.server.business.basic.loginexpired.service;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @ClassName: ILoginExpiredService
 * @ClassNameExplain:
 * @Description: 登录失效业务接口类
 * @author wudan-mac
 * @date 16/5/11 下午6:06
 */
public interface ILoginExpiredService {

    /**
     * @Title: loginExpired
     * @TitleExplain:
     * @Description: 登录失效业务方法定义
     * @param
     * @return
     * @version 1.0.0
     * @author wudan-mac
     */
    ModelAndView loginExpired(RedirectAttributes attr) throws Exception;

}