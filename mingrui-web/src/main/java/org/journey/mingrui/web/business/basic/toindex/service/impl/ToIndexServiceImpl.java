package org.journey.mingrui.web.business.basic.toindex.service.impl;

import org.journey.mingrui.web.business.basic.toindex.io.ToIndexIO;
import org.journey.mingrui.web.business.basic.toindex.service.IToIndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
public class ToIndexServiceImpl implements IToIndexService {

    static final Logger logger = LoggerFactory.getLogger(ToIndexServiceImpl.class);

    @Override
    public ModelAndView toIndex(ToIndexIO toIndexIO, RedirectAttributes attr) throws Exception {

        ModelAndView modelAndView = new ModelAndView("index");


        return modelAndView;
    }
}