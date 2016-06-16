package org.journey.mingrui.web.business.basic.toindex.service;

import org.journey.mingrui.web.business.basic.toindex.io.ToIndexIO;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface IToIndexService {

    ModelAndView toIndex(ToIndexIO toIndexIO, RedirectAttributes attr) throws Exception;

}