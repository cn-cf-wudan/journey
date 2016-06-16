package com.sinldo.server.business.basic.loginsuccess.service;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @ClassName: ILoginSuccessService
 * @ClassNameExplain:
 * @Description: 业务层接口类
 * @author wudan-mac
 * @date 2016-05-27 17:19:10
 */
public interface ILoginSuccessService {

    /**
     * @Title: loginSuccess
     * @TitleExplain:
     * @Description: 业务层接口方法定义
     * @version
     * @author wudan-mac
     */
     ModelAndView loginSuccess(RedirectAttributes attr) throws Exception;

}