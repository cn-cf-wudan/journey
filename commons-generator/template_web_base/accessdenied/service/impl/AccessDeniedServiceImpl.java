package com.sinldo.server.business.basic.accessdenied.service.impl;

import com.sinldo.server.business.basic.accessdenied.service.IAccessDeniedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @ClassName: AccessDeniedServiceImpl
 * @ClassNameExplain:
 * @Description: 业务层实现类
 * @author wudan-mac
 * @date 2016-05-27 16:14:14
 */
@Service
public class AccessDeniedServiceImpl implements IAccessDeniedService {

    static final Logger logger = LoggerFactory.getLogger(AccessDeniedServiceImpl.class);

    @Override
    public ModelAndView accessDenied(RedirectAttributes attr) throws Exception {

        ModelAndView modelAndView = new ModelAndView("redirect:/error/405");


        return modelAndView;
    }
}