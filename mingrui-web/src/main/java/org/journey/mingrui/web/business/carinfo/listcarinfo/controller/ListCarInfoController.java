package org.journey.mingrui.web.business.carinfo.listcarinfo.controller;

import org.journey.mingrui.web.business.carinfo.listcarinfo.io.ListCarInfoIO;
import org.journey.mingrui.web.business.carinfo.listcarinfo.service.IListCarInfoService;
import org.journey.mingrui.web.business.carinfo.listcarinfo.vo.ListCarInfoVO;
import org.journey.mingrui.web.ext.MethodLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.validation.Valid;

@RequestMapping(value = "")
@Controller
public class ListCarInfoController {

    static final Logger logger = LoggerFactory.getLogger(ListCarInfoController.class);

    @Resource
    IListCarInfoService listCarInfoService;

    @RequestMapping(value = "/listCarInfo")
    @MethodLog(remark = "车型查询")
    @ResponseBody
    public ListCarInfoVO listCarInfo(@Valid @ModelAttribute("listCarInfoIO") ListCarInfoIO listCarInfoIO, RedirectAttributes attr) throws Exception {
        return listCarInfoService.listCarInfo(listCarInfoIO, attr);
    }

}