package org.journey.mingrui.web.business.carinfo.tolistcarinfo.controller;

import org.journey.mingrui.web.business.carinfo.tolistcarinfo.io.ToListCarInfoIO;
import org.journey.mingrui.web.business.carinfo.tolistcarinfo.service.IToListCarInfoService;
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
public class ToListCarInfoController {

    static final Logger logger = LoggerFactory.getLogger(ToListCarInfoController.class);

    @Resource
    IToListCarInfoService toListCarInfoService;

    @RequestMapping(value = "/toListCarInfo.do")
    public ModelAndView toListCarInfo(@Valid @ModelAttribute("toListCarInfoIO") ToListCarInfoIO toListCarInfoIO, RedirectAttributes attr) throws Exception {
        return toListCarInfoService.toListCarInfo(toListCarInfoIO, attr);
    }

}