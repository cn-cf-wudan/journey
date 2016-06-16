package org.journey.mingrui.web.business.carinfo.listcarinfo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.journey.mingrui.web.business.carinfo.listcarinfo.io.ListCarInfoIO;
import org.journey.mingrui.web.business.carinfo.listcarinfo.service.IListCarInfoService;
import org.journey.mingrui.web.business.carinfo.listcarinfo.vo.ListCarInfoVO;
import org.journey.mingrui.web.dao.CarInfoMapperWeb;
import org.journey.po.mingrui.mysql.CarInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ListCarInfoServiceImpl implements IListCarInfoService {

    static final Logger logger = LoggerFactory.getLogger(ListCarInfoServiceImpl.class);

    @Resource
    CarInfoMapperWeb carInfoMapperWeb;

    @Override
    public ListCarInfoVO listCarInfo(ListCarInfoIO listCarInfoIO, RedirectAttributes attr) throws Exception {

        ListCarInfoVO listCarInfoVO = new ListCarInfoVO();

        PageHelper.startPage(listCarInfoIO.getPage(), listCarInfoIO.getRows());
        List<CarInfo> carInfoList = carInfoMapperWeb.selectBySearchAndPage(listCarInfoIO.getModel(), listCarInfoIO.getEngine());
        PageInfo pageInfo = new PageInfo(carInfoList);
        listCarInfoVO.setRows(carInfoList);
        listCarInfoVO.setTotal(pageInfo.getTotal());

        return listCarInfoVO == null ? new ListCarInfoVO() : listCarInfoVO;
    }
}