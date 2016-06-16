package org.journey.mingrui.web.business.carinfo.listcarinfo.service;

import org.journey.mingrui.web.business.carinfo.listcarinfo.io.ListCarInfoIO;
import org.journey.mingrui.web.business.carinfo.listcarinfo.vo.ListCarInfoVO;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface IListCarInfoService {

    ListCarInfoVO listCarInfo(ListCarInfoIO listCarInfoIO, RedirectAttributes attr) throws Exception;

}