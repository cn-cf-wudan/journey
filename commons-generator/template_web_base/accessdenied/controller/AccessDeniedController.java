package com.sinldo.server.business.basic.accessdenied.controller;

import com.sinldo.server.business.basic.accessdenied.service.IAccessDeniedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

/**
 * @ClassName: AccessDeniedController
 * @ClassNameExplain:
 * @Description:
 * @author wudan-mac
 * @date 2016-05-27 16:14:14
 */
@RequestMapping(value = "")
@Controller
public class AccessDeniedController {

    static final Logger logger = LoggerFactory.getLogger(AccessDeniedController.class);

    @Resource
    IAccessDeniedService accessDeniedService;

    /**
     * @Title: accessDenied
     * @TitleExplain:
     * @Description: 无权限控制处理
     * @return
     * @version
     * @author wudan-mac
     */
    @RequestMapping(value = "/accessDenied")
    public ModelAndView accessDenied(RedirectAttributes attr) throws Exception {
        return accessDeniedService.accessDenied(attr);
    }

}