package com.sinldo.server.business.basic.accessdenied.service;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @ClassName: IAccessDeniedService
 * @ClassNameExplain:
 * @Description: 业务层接口类
 * @author wudan-mac
 * @date 2016-05-27 16:14:14
 */
public interface IAccessDeniedService {

    /**
     * @Title: accessDenied
     * @TitleExplain:
     * @Description: 业务层接口方法定义
     * @return
     * @version
     * @author wudan-mac
     */
     ModelAndView accessDenied(RedirectAttributes attr) throws Exception;

}