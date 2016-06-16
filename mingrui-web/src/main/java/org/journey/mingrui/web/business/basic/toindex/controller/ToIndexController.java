package org.journey.mingrui.web.business.basic.toindex.controller;

import org.journey.mingrui.web.business.basic.toindex.io.ToIndexIO;
import org.journey.mingrui.web.business.basic.toindex.service.IToIndexService;
import org.journey.mingrui.web.ext.MethodLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.validation.Valid;

@RequestMapping(value = "")
@Controller
public class ToIndexController {

    static final Logger logger = LoggerFactory.getLogger(ToIndexController.class);

    @Resource
    IToIndexService toIndexService;

    @RequestMapping(value = "/")
    @MethodLog(remark = "首页")
    public ModelAndView toIndex(@Valid @ModelAttribute("toIndexIO") ToIndexIO toIndexIO, RedirectAttributes attr) throws Exception {
        return toIndexService.toIndex(toIndexIO, attr);
    }

}