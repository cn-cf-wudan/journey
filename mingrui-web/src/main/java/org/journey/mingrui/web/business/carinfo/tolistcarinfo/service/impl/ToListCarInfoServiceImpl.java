package org.journey.mingrui.web.business.carinfo.tolistcarinfo.service.impl;

import org.journey.mingrui.web.business.carinfo.tolistcarinfo.io.ToListCarInfoIO;
import org.journey.mingrui.web.business.carinfo.tolistcarinfo.service.IToListCarInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
public class ToListCarInfoServiceImpl implements IToListCarInfoService {

    static final Logger logger = LoggerFactory.getLogger(ToListCarInfoServiceImpl.class);

    @Override
    public ModelAndView toListCarInfo(ToListCarInfoIO toListCarInfoIO, RedirectAttributes attr) throws Exception {

        ModelAndView modelAndView = new ModelAndView("carinfo/carinfo");


        return modelAndView;
    }
}