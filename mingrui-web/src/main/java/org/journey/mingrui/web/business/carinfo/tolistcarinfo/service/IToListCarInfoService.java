package org.journey.mingrui.web.business.carinfo.tolistcarinfo.service;

import org.journey.mingrui.web.business.carinfo.tolistcarinfo.io.ToListCarInfoIO;
import org.journey.mingrui.web.business.carinfo.tolistcarinfo.vo.ToListCarInfoVO;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface IToListCarInfoService {

    ModelAndView toListCarInfo(ToListCarInfoIO toListCarInfoIO, RedirectAttributes attr) throws Exception;

}